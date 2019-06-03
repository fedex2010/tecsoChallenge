package coop.tecso.examen.model.dtos;

import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class CurrentAccountDto implements Serializable {
    BigInteger accountNumber;

    BigDecimal creditBalance;

    BigDecimal debitBalance;

    CurrentAccount.MoneyType moneyType;

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

    public CurrentAccount.MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(CurrentAccount.MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public BigInteger getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CurrentAccountDto(CurrentAccount aAccount) {
        this.creditBalance = aAccount.getCreditBalance();
        this.debitBalance = aAccount.getDebitBalance();
        this.accountNumber = aAccount.getAccountNumber();
        this.moneyType = aAccount.getMoneyType();
    }
}
