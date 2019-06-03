package coop.tecso.examen.exceptions;

public class CurrentAccountNotFoundException extends RestException {
    private Long accountIdNotFound;

    public CurrentAccountNotFoundException(Long accountId){
        super(404,"Cuenta con nro id: " + accountId + " no existente");
        accountIdNotFound = accountId;
    }

    public Long getAccountIdNotFound() {
        return accountIdNotFound;
    }
}
