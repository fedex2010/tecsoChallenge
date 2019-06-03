package coop.tecso.examen.model.mappers;

import coop.tecso.examen.Utils.TimeUtils;
import coop.tecso.examen.exceptions.BadRequestException;
import coop.tecso.examen.model.Movement.*;
import coop.tecso.examen.interfaces.CheckParamsMapper;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovementMapperCheck extends Movement implements CheckParamsMapper {
    final static Set<String> movementTypeAllowed = Stream.of(MovementType.values())
                                                            .map(Enum::name)
                                                            .collect(Collectors.toSet());

    final static Set<String> operationTypeAllowed = Stream.of(MovementOperationType.values())
                                                            .map(Enum::name)
                                                            .collect(Collectors.toSet());

    String type;
    String operationType;

    MovementOperationType movementOperationType;

    public MovementOperationType getMovementOperationType() {
        return MovementOperationType.valueOf(operationType);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperationType() {
        return operationType;
    }

    public MovementType getMovementType(){
        return MovementType.valueOf(type);
    }

    public Movement toEntity() throws BadRequestException {

        Movement m = null;

        MovementType movementType = this.getMovementType();

        if(movementType.equals(MovementType.DEBIT)){
            m = new DebitMovement();
           ((DebitMovement) m).setMovementOperationType(this.getMovementOperationType());

        }else if(movementType.equals(MovementType.CREDIT)){
            m = new CreditMovement();
        }

        m.setMovementType(movementType);

        m.setDescription(this.getDescription());
        m.setImporte(this.getImporte());
        m.setOperationDateTime( TimeUtils.getNowAsUtc() );
        m.markAsActive();

        return m;
    }

    @Override
    public void checkParams() throws BadRequestException {
        if(!Optional.ofNullable( this.type ).isPresent()){
            throw new BadRequestException("type is required, values " + movementTypeAllowed.toString(),400);
        }

        if( !movementTypeAllowed.contains( this.type ) ){
            throw new BadRequestException(
                    "type unknown, values allowed " + movementTypeAllowed.toString(),400);
        }


        if(type.equals(MovementType.DEBIT.name())){

            if(!Optional.ofNullable( this.operationType ).isPresent()){
                throw new BadRequestException("if it is a debit operation then must tu specify operationType, values " + operationTypeAllowed.toString(),400);
            }

            if( !operationTypeAllowed.contains( this.operationType ) ){
                throw new BadRequestException(
                        "if it is a debit operation then must tu specify operationType, operationType unknowed, values allowed " + operationTypeAllowed.toString(),400);
            }

        }

        if(!Optional.ofNullable( this.getImporte() ).isPresent()){
            throw new BadRequestException("importe is required",400);
        }
        if(!Optional.ofNullable( this.getDescription() ).isPresent()){
            throw new BadRequestException("description is required",400);
        }
    }
}
