package coop.tecso.examen.service.BalanceService;

import coop.tecso.examen.exceptions.DebtLimitReachedException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.CurrentAccount.DolarCurrentAccount;
import coop.tecso.examen.model.CurrentAccount.EuroCurrentAccount;
import coop.tecso.examen.model.CurrentAccount.PesoCurrentAccount;
import coop.tecso.examen.model.Movement.CreditMovement;
import coop.tecso.examen.model.Movement.Movement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditBalanceService extends BalanceService{
	private CreditMovement creditMovement;

	@Override
	public void applyMovement() {
		CurrentAccount c = super.getCurrentAccount();
		c.updateCreditBalance( creditMovement.getImporte() );
	}

	@Override
	public void checkBalanceAccount() throws DebtLimitReachedException{
		CurrentAccount c = super.getCurrentAccount();
		String className = c.getClass().getSimpleName();

		if(className.equals(  PesoCurrentAccount.class.getSimpleName() )){
			checkCreditBalance( (PesoCurrentAccount)c );

		}else if( className.equals(  DolarCurrentAccount.class.getSimpleName() ) ){
			checkCreditBalance( (DolarCurrentAccount)c );

		}else if(className.equals(  EuroCurrentAccount.class.getSimpleName() )){
			checkCreditBalance( (EuroCurrentAccount)c );

		}else{
			System.out.println("Not speficic class, any check will we applied");
		}
	}

	@Override
	public void setData(CurrentAccount currentAccount, Movement movement) {
		super.setCurrentAccount(currentAccount);
		this.creditMovement = (CreditMovement)movement;
	}

	public void checkCreditBalance(PesoCurrentAccount pc) throws DebtLimitReachedException {
		BigDecimal balance = pc.getCreditBalance();

		if( balance.compareTo( new BigDecimal(1000).negate() ) < 0  ){
			throw new DebtLimitReachedException("aa");
		}
	}

	public void checkCreditBalance(DolarCurrentAccount dc) throws DebtLimitReachedException{
		BigDecimal balance = dc.getCreditBalance();

		if( balance.compareTo(  new BigDecimal(300).negate() ) < 0  ){
			throw new DebtLimitReachedException("aa");
		}
	}

	public void checkCreditBalance(EuroCurrentAccount ec) throws DebtLimitReachedException{
		BigDecimal balance = ec.getCreditBalance();

		if( balance.compareTo(  new BigDecimal(150).negate() ) < 0  ){
			throw new DebtLimitReachedException("aa");
		}
	}
}
