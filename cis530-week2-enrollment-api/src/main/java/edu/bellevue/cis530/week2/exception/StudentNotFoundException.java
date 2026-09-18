package edu.bellevue.cis530.week2.exception;

/*
 * Custom exception class for handling cases where a student is not found.
 */
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
    
}
