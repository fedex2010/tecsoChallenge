package coop.tecso.examen.service.BalanceService;

import coop.tecso.examen.exceptions.DebitLimitReachedException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.Movement.CreditMovement;
import coop.tecso.examen.model.Movement.DebitMovement;
import coop.tecso.examen.model.Movement.Movement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DebitBalanceService extends BalanceService{
	private DebitMovement debitMovement;

	@Override
	public void applyMovement() {
        currentAccount.updateDebitBalance( debitMovement.getImporte() );
	}

	@Override
	public void checkBalanceAccount() throws DebitLimitReachedException {
        BigDecimal balance = currentAccount.getDebitBalance();

        if( balance.compareTo(BigDecimal.ZERO) < 0  ){
            throw new DebitLimitReachedException();
        }
    }

    @Override
    public void setData(CurrentAccount currentAccount, Movement movement) {
        super.setCurrentAccount(currentAccount);
        this.debitMovement = (DebitMovement)movement;
    }

}
