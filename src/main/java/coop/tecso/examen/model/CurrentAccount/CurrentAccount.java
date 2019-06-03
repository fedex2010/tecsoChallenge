package coop.tecso.examen.model.CurrentAccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import coop.tecso.examen.model.AbstractPersistentObject;
import coop.tecso.examen.model.Movement.Movement;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CurrentAccount extends AbstractPersistentObject {

    @Column(columnDefinition = "serial")
    @Generated(GenerationTime.INSERT)
    BigInteger accountNumber;

    @Digits(integer = 10 /*precision*/, fraction = 2 /*scale*/)
    BigDecimal creditBalance;

    @Digits(integer = 10 /*precision*/, fraction = 2 /*scale*/)
    BigDecimal debitBalance;

    MoneyType moneyType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="currentAccount")
    @JsonIgnoreProperties("currentAccount")
    private Collection<Movement> operationsLists;

    public Collection<Movement> getOperationsLists() {
        return operationsLists;
    }
    public void setOperationsLists(Collection<Movement> operationsLists) {
        this.operationsLists = operationsLists;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }

    public BigDecimal getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(BigDecimal debitBalance) {
        this.debitBalance = debitBalance;
    }

    public void updateDebitBalance(BigDecimal debitBalance) {
        this.debitBalance = this.debitBalance.add(debitBalance);
    }

    //se toma como positivo el credito q c esta intentando tomar
    public void updateCreditBalance(BigDecimal usedCredit) {
        this.creditBalance = this.creditBalance.add(usedCredit.negate());
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreationTimestamp() {
        return super.getCreationTimestamp();
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public enum MoneyType {
        PESO,EURO,DOLAR
    }

}


