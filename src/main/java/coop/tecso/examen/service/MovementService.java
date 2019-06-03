package coop.tecso.examen.service;

import coop.tecso.examen.exceptions.MovementNotFoundException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.Movement.Movement;
import coop.tecso.examen.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Service
public class MovementService {

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    CurrentAccountService currentAccountService;

    public Movement create(Long accountId, Movement movement) {

        CurrentAccount c = new CurrentAccount();
        c.setId(accountId);

        movement.setCurrentAccount(c);
        movementRepository.save(movement);

        return movement;
    }


    public Collection<Movement> findAllByAccountId(Long accountId) {
        //return movementRepository.findAllByAccountId(accountId,(Pageable) PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "operationDate")));
        //Pageable aPage = (Pageable) PageRequest.of(0, 2);
        return movementRepository.findAllByAccountId(accountId);
    }

    public List<Movement> findAll() {
        return movementRepository.findAll();
    }

    public void deleteById(Long movementId) throws MovementNotFoundException {
        try{
            movementRepository.deleteById(movementId);
        }catch (Exception e){
            if( e.getClass().getSimpleName().equals("EmptyResultDataAccessException") ){
                throw new MovementNotFoundException(movementId);
            }
        }
    }
}
