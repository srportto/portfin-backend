package br.com.srportto.exceptions;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) //!==> intercepta o lancamento de execoes desse tipo
    public ResponseEntity<LayoutExceptionsProject> entityNotFound(ResourceNotFoundException error, HttpServletRequest req){

        LayoutExceptionsProject layoutError = new LayoutExceptionsProject();

        layoutError.setTimestamp(Instant.now());
        layoutError.setStatus(HttpStatus.NOT_FOUND.value());
        layoutError.setError("Recurso nao encontrado");
        layoutError.setMessage(error.getMessage());
        layoutError.setPath(req.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(layoutError);
    }

    @ExceptionHandler(DatabaseException.class) //!==> intercepta o lancamento de execoes desse tipo
    public ResponseEntity<LayoutExceptionsProject> database(DatabaseException error, HttpServletRequest req){

        LayoutExceptionsProject layoutError = new LayoutExceptionsProject();

        layoutError.setTimestamp(Instant.now());
        layoutError.setStatus(HttpStatus.BAD_REQUEST.value());
        layoutError.setError("database exception");
        layoutError.setMessage(error.getMessage());
        layoutError.setPath(req.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(layoutError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<LayoutExceptionsValidations> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        LayoutExceptionsValidations layoutResponseExceptionValidation = new LayoutExceptionsValidations();
        layoutResponseExceptionValidation.setTimestamp(Instant.now());
        layoutResponseExceptionValidation.setStatus(status.value());
        layoutResponseExceptionValidation.setError("Validation exception");
        layoutResponseExceptionValidation.setMessage(e.getMessage());
        layoutResponseExceptionValidation.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            layoutResponseExceptionValidation.addOccurrences(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(layoutResponseExceptionValidation);
    }
}
