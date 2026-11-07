package edu.bellevue.cis530.studentservice.service;

import java.util.List;

import edu.bellevue.cis530.studentservice.entity.Student;

/*
 * Defines the operations available for managing Student records.
 * The controller uses this interface instead of accessing
 * the repository directly.
 */
public interface StudentService {

    // Creates and saves a new student record.
    Student createStudent(Student student);

    // Retrieves all student records.
    List<Student> getAllStudents();

    // Retrieves one student using the student's ID.
    Student getStudentById(Long id);

    // Deletes a student using the student's ID.
    void deleteStudent(Long id);
}