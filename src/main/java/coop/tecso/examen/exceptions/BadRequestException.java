package coop.tecso.examen.exceptions;

public class BadRequestException extends RestException {
    public BadRequestException(String message, Integer code) {
        super(code,message);
    }
}
