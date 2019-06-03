package coop.tecso.examen.interfaces;

import coop.tecso.examen.exceptions.BadRequestException;

public interface CheckParamsMapper {
    void checkParams() throws BadRequestException;
}
