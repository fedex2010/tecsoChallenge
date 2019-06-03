package coop.tecso.examen.exceptions;

public class DebitLimitReachedException extends RestException {
    public DebitLimitReachedException(){
        super(400,"No hay tanta plata para gastar!!!");
    }
}
