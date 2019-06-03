package coop.tecso.examen.service.BalanceService;

import coop.tecso.examen.exceptions.DebitLimitReachedException;
import coop.tecso.examen.exceptions.DebtLimitReachedException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.CurrentAccount.DolarCurrentAccount;
import coop.tecso.examen.model.CurrentAccount.EuroCurrentAccount;
import coop.tecso.examen.model.CurrentAccount.PesoCurrentAccount;
import coop.tecso.examen.model.Movement.Movement;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public abstract class BalanceService {
	protected CurrentAccount currentAccount;

	protected void setCurrentAccount(CurrentAccount currentAccount){
		this.currentAccount = currentAccount;
	}

	protected CurrentAccount getCurrentAccount(){
		return currentAccount;
	}

	public abstract void applyMovement();
	public abstract void checkBalanceAccount() throws DebitLimitReachedException, DebtLimitReachedException;

	public abstract void setData(CurrentAccount currentAccount, Movement movement);
}
