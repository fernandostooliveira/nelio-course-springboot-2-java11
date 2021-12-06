package com.educandoweb.course.resources.exceptions;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

// Classe com métodos que controlam a execução dos erros específicos

@ControllerAdvice
public class ResourceExceptionHandler {

  /*
   * (Classe de erro) ResponseEntity<classe modelo de erro > nome do método (nome da classe de erro
   * personalizada e, HttpServletRequest request){
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFoud(ResourceNotFoundException e,
      HttpServletRequest request) {

    String error = "Resource not found";
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(status).body(err);

  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

    String error = "Database error";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(status).body(err);

  }



}
