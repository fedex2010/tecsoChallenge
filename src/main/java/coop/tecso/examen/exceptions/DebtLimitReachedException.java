package coop.tecso.examen.exceptions;

public class DebtLimitReachedException extends RestException {
    public DebtLimitReachedException(String m){
        super(403,"Ya te endeudaste demasiado, para un poco la mano capo!!!");
    }
}
