package coop.tecso.examen.exceptions;

public class TryDeleteAccountWithMovementsException extends RestException {
    private Long accountId;

    public TryDeleteAccountWithMovementsException(Long accountId){
        super(403,"Cuenta con nro id: " + accountId + " no puede ser borrada ya que tiene movimientos asociados");
        this.accountId = accountId;
    }

    public Long getAccount() {
        return accountId;
    }
}
