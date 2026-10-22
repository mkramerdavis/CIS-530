package edu.bellevue.cis530.week7.exception;


// Exception thrown when a requested resource is not found. //
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}