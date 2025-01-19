package by.tms.workouthub.controller;

import by.tms.workouthub.exceptions.AccountAlreadyExistsException;
import by.tms.workouthub.exceptions.NotFoundEntityException;
import org.apache.commons.lang3.exception.UncheckedIllegalAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<?> handleEntityNotFoundException(NotFoundEntityException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UncheckedIllegalAccessException.class)
    public ResponseEntity<?> handleIllegalErrorException(UncheckedIllegalAccessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<?> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
