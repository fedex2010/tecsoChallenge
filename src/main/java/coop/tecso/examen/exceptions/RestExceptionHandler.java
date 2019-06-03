package coop.tecso.examen.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(DebitLimitReachedException.class)
    public ResponseEntity<Map<?, ?>> debitLimitReachedException(HttpServletRequest req,DebitLimitReachedException ex) {

        logger.error("debitLimitReachedException", req.getMethod(), req.getRequestURI());

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(DebtLimitReachedException.class)
    public ResponseEntity<Map<?, ?>> debtLimitReachedException(HttpServletRequest req,DebtLimitReachedException ex) {

        logger.error("debtLimitReachedException", req.getMethod(), req.getRequestURI());

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<?, ?>> badRequestException(HttpServletRequest req,BadRequestException ex) {

        logger.error("badRequestException", req.getMethod(), req.getRequestURI());

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(CurrentAccountNotFoundException.class)
    public ResponseEntity<Map<?, ?>> currentAccountNotFoundException(HttpServletRequest req,CurrentAccountNotFoundException ex) {

        logger.error("Account id not found --> id: " + ex.getAccountIdNotFound(), req.getMethod(), req.getRequestURI());

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(MovementNotFoundException.class)
    public ResponseEntity<Map<?, ?>> movementNotFoundException(HttpServletRequest req,MovementNotFoundException ex) {

        logger.error("Movement id not found --> id: " + ex.getMovementId(), req.getMethod(), req.getRequestURI());

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(TryDeleteAccountWithMovementsException.class)
    public ResponseEntity<Map<?, ?>> movementNotFoundException(HttpServletRequest req,TryDeleteAccountWithMovementsException ex) {

        logger.error("Account id not could be deleted --> id: " + ex.getAccount(), req.getMethod(), req.getRequestURI());

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.valueOf(ex.getStatusCode()));
    }



}