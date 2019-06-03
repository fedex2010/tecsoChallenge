package coop.tecso.examen.controller;

import coop.tecso.examen.exceptions.*;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.Movement.Movement;
import coop.tecso.examen.model.dtos.CurrentAccountDto;
import coop.tecso.examen.model.dtos.MovementDto;
import coop.tecso.examen.model.mappers.MovementMapperCheck;
import coop.tecso.examen.service.BalanceService.BalanceService;
import coop.tecso.examen.service.BalanceService.BalanceServiceFactory;
import coop.tecso.examen.service.CurrentAccountService;
import coop.tecso.examen.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/")
public class MovementController {
    @Autowired
    MovementService movementService;

    @Autowired
    CurrentAccountService currentAccountService;

    @RequestMapping(value = "movements",method = RequestMethod.GET)
    public ResponseEntity <List<MovementDto>> listMovements() {

        List<MovementDto> movementDtoList = movementService.findAll()
                                                        .stream()
                                                        .map( m -> new MovementDto(m)  )
                                                        .collect(Collectors.toList());

        return new ResponseEntity<List<MovementDto>>( movementDtoList , HttpStatus.OK);
    }

    @RequestMapping(value = "movements/{movementId}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMovement(@PathVariable (value = "movementId") Long movementId) throws MovementNotFoundException {

        movementService.deleteById(movementId);

        return new ResponseEntity<Void>(  HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "account/{accountId}/movements",method = RequestMethod.GET)
    public ResponseEntity<Collection<MovementDto>> listMovementsByAccount(@PathVariable (value = "accountId") Long accountId){

        Collection<MovementDto> movementDtoCollection = movementService.findAllByAccountId(accountId)
                                                                        .stream()
                                                                        .map( m -> new MovementDto(m)  )
                                                                        .collect(Collectors.toList());

        return new ResponseEntity<Collection<MovementDto>>( movementDtoCollection , HttpStatus.OK);
    }


    @RequestMapping(value = "account/{accountId}/movements",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> movementAccount(@PathVariable (value = "accountId") Long accountId,
                                                    @RequestBody MovementMapperCheck movementMapper) throws CurrentAccountNotFoundException, DebtLimitReachedException, DebitLimitReachedException, BadRequestException {

        CurrentAccount currentAccount = currentAccountService.findById(accountId);

        movementMapper.checkParams();
        Movement m = movementMapper.toEntity();

        BalanceService balanceService = BalanceServiceFactory.create( movementMapper.getMovementType() );
        balanceService.setData(currentAccount,m);

        balanceService.applyMovement();
        balanceService.checkBalanceAccount();

        //actualizo saldo en la cuenta
        currentAccountService.save(currentAccount);
        //guardo movimiento
        movementService.create(accountId,m);

        return new ResponseEntity<>(new MovementDto(m),HttpStatus.CREATED);
    }
}
