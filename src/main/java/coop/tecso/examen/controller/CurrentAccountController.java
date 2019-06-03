package coop.tecso.examen.controller;

import coop.tecso.examen.exceptions.BadRequestException;
import coop.tecso.examen.exceptions.CurrentAccountNotFoundException;
import coop.tecso.examen.exceptions.MovementNotFoundException;
import coop.tecso.examen.exceptions.TryDeleteAccountWithMovementsException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.dtos.CurrentAccountDto;
import coop.tecso.examen.model.mappers.CurrentAccountMapperCheck;
import coop.tecso.examen.service.CurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/currentAccounts")
public class CurrentAccountController {
    @Autowired
    CurrentAccountService currentAccountService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String createCurrentAccount() {
        return "HoLa CoMo ESTaS, TODO BIEN??";
    }


    @RequestMapping(value = "/",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrentAccountDto> createCurrentAccount(@RequestBody CurrentAccountMapperCheck accountMapped) throws BadRequestException {
        accountMapped.checkParams();
        CurrentAccount aAccount = currentAccountService.create( accountMapped.toEntity() );

        CurrentAccountDto currentAccountDto = new CurrentAccountDto(aAccount);
        return new ResponseEntity<CurrentAccountDto>(currentAccountDto,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{accountId}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCurrentAccount(@PathVariable("accountId") Long accountId) throws TryDeleteAccountWithMovementsException {

        currentAccountService.deleteByAccountId(accountId);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<CurrentAccountDto>> listCurrentAccounts() {

        List<CurrentAccountDto> currentAccountDtoList = currentAccountService.findAll()
                                                            .stream()
                                                            .map( account -> new CurrentAccountDto(account)  )
                                                            .collect(Collectors.toList());

        return new ResponseEntity<List<CurrentAccountDto>>( currentAccountDtoList , HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<CurrentAccountDto> getCurrentAccount(@PathVariable("accountId") Long accountId) throws CurrentAccountNotFoundException {
        CurrentAccount c = currentAccountService.findById(accountId);
        CurrentAccountDto currentAccountDto = new CurrentAccountDto(c);

        return new ResponseEntity<CurrentAccountDto>( currentAccountDto , HttpStatus.OK);
    }
}
