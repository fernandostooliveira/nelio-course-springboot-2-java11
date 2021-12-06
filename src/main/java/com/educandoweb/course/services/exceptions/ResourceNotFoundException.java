package com.educandoweb.course.services.exceptions;

////exceção personalizada de id

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException (Object id) {
    super("Resource not found. id" + id);
  }
}
