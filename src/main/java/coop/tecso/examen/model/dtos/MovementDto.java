package coop.tecso.examen.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.Movement.Movement;
import coop.tecso.examen.model.Movement.MovementType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class MovementDto implements Serializable {
    BigInteger number;

    Date operationDateTime;

    String description;

    BigDecimal importe;

    MovementType movementType;

    public MovementDto(Movement m) {
        this.number = m.getNumber();
        this.operationDateTime = m.getOperationDateTime();
        this.description = m.getDescription();
        this.importe = m.getImporte();
        this.movementType = m.getMovementType();
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public Date getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(Date operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }




}
