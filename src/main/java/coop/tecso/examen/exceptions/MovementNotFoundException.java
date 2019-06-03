package coop.tecso.examen.exceptions;

public class MovementNotFoundException extends RestException {
    private Long movementId;

    public MovementNotFoundException(Long movementId){
        super(404,"Movimiento con id: " + movementId + " no existente");
        this.movementId = movementId;
    }

    public Long getMovementId() {
        return movementId;
    }
}
