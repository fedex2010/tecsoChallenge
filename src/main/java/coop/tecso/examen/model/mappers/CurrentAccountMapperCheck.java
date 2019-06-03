package coop.tecso.examen.model.mappers;

import coop.tecso.examen.exceptions.BadRequestException;
import coop.tecso.examen.model.CurrentAccount.CurrentAccount;
import coop.tecso.examen.model.CurrentAccount.DolarCurrentAccount;
import coop.tecso.examen.model.CurrentAccount.EuroCurrentAccount;
import coop.tecso.examen.model.CurrentAccount.PesoCurrentAccount;
import coop.tecso.examen.interfaces.CheckParamsMapper;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrentAccountMapperCheck extends CurrentAccount implements CheckParamsMapper {
    final static Set<String> accountMoneyAllowed = Stream.of(MoneyType.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    BigDecimal initialMoney;
    String accountMoneyType;

    public String getAccountMoneyType() {
        return accountMoneyType;
    }

    public void setAccountMoneyType(String accountMoneyType) {
        this.accountMoneyType = accountMoneyType;
    }

    public BigDecimal getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(BigDecimal initialMoney) {
        this.initialMoney = initialMoney;
    }


    public CurrentAccount toEntity() throws BadRequestException {

        CurrentAccount c = null;

        MoneyType m = MoneyType.valueOf(this.accountMoneyType.toUpperCase());

        if(m.equals(MoneyType.PESO)){
            c = new PesoCurrentAccount();
        }else if(m.equals(MoneyType.DOLAR)){
            c = new DolarCurrentAccount();
        }else{
            c = new EuroCurrentAccount();
        }

        c.setMoneyType( m );
        c.setCreditBalance(new BigDecimal(10000));

        if(!Optional.ofNullable(this.initialMoney).isPresent()) {
            c.setDebitBalance(new BigDecimal(0));
        }else{
            c.setDebitBalance( initialMoney );
        }

        return c;
    }

    @Override
    public void checkParams() throws BadRequestException {
        if(!Optional.ofNullable(this.accountMoneyType).isPresent()){
            throw new BadRequestException("accountMoneyType is required",400);
        }
        if( !accountMoneyAllowed.contains( this.accountMoneyType ) ){
            throw new BadRequestException("accountMoneyType unknowed, values allowed " + accountMoneyAllowed.toString(),400);
        }
    }
}
