package coop.tecso.examen.service;

import coop.tecso.examen.exceptions.CurrentAccountNotFoundException;
import coop.tecso.examen.exceptions.MovementNotFoundException;
import coop.tecso.examen.exceptions.TryDeleteAccountWithMovementsException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.repository.CurrentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrentAccountService {

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    public CurrentAccount create(CurrentAccount aAccount) {

        currentAccountRepository.save(aAccount);

        return aAccount;
    }

    public List<CurrentAccount> findAll() {
        return currentAccountRepository.findAll();
    }

    public void deleteByAccountId(Long accountId) throws TryDeleteAccountWithMovementsException {
        try{
            currentAccountRepository.deleteById(accountId);
        }catch (Exception e){

            if( e.getClass().getSimpleName().equals("DataIntegrityViolationException") ){
                throw new TryDeleteAccountWithMovementsException(accountId);
            }
        }

    }

    public CurrentAccount findById(Long accountId) throws CurrentAccountNotFoundException {
        Optional<CurrentAccount> optionalCurrentAccount = currentAccountRepository.findById(accountId);

        if( !optionalCurrentAccount.isPresent() ){
            throw new CurrentAccountNotFoundException(accountId);
        }

        return optionalCurrentAccount.get();
    }

    public void save(CurrentAccount currentAccount) {
        currentAccountRepository.save(currentAccount);
    }
}
