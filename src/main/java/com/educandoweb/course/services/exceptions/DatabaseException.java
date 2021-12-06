package com.educandoweb.course.services.exceptions;

//exceção personalizada
public class DatabaseException extends RuntimeException{

  
  private static final long serialVersionUID = 1L;

  public DatabaseException(String msg) {
    super(msg);
  }
  
}
