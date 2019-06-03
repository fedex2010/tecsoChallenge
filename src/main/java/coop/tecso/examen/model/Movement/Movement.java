package coop.tecso.examen.model.Movement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coop.tecso.examen.model.AbstractPersistentObject;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@SQLDelete(sql="UPDATE movement m SET m.status = 'DELETED' where id = ?")
@Where(clause = "status <> 'DELETED'")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movement extends AbstractPersistentObject {

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    @Column(columnDefinition = "serial")
    @Generated(GenerationTime.INSERT)
    BigInteger number;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Date operationDateTime;

    @Column(length = 200)
    String description;

    @Digits(integer = 10 /*precision*/, fraction = 2 /*scale*/)
    BigDecimal importe;

    MovementType movementType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("operationsLists")
    private CurrentAccount currentAccount;

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Date getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(Date operationDateTime) {
        this.operationDateTime = operationDateTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }



    /*SOFT DELETE*/
    public void markAsActive() {
        this.status = Status.ACTIVE;
    }

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private enum Status {
        ACTIVE,INACTIVE,DELETED
    }

    @PreRemove
    public void deleteUser(){
        this.status = Status.DELETED;
    }

}
