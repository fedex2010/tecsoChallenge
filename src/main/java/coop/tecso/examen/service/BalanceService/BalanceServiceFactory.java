package coop.tecso.examen.service.BalanceService;

import coop.tecso.examen.model.Movement.MovementType;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceFactory {
	public static BalanceService create(MovementType movementOperation){
		if(movementOperation.equals( MovementType.DEBIT )){
			return new DebitBalanceService();
		}else{
			return new CreditBalanceService();
		}
	}
}
