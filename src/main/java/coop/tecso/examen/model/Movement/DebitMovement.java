package coop.tecso.examen.model.Movement;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class DebitMovement extends Movement {
    MovementOperationType movementOperationType;

    public MovementOperationType getMovementOperationType() {
        return movementOperationType;
    }

    public void setMovementOperationType(MovementOperationType movementOperationType) {
        this.movementOperationType = movementOperationType;
    }


    public BigDecimal getImporte(){
        if( movementOperationType.equals(MovementOperationType.DEPOSIT) ){
            return super.getImporte();
        }else{
            return super.getImporte().negate();
        }
    }
}
