package edu.bellevue.cis530.week2.service;

import edu.bellevue.cis530.week2.exception.StudentNotFoundException;
import edu.bellevue.cis530.week2.model.Student;
import edu.bellevue.cis530.week2.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Service class for managing Student entities and business logic.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }

        return student;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        findById(id);
        return studentRepository.update(id, student);
    }

    public void delete(Long id) {
        findById(id);
        studentRepository.delete(id);
    }
    
}
