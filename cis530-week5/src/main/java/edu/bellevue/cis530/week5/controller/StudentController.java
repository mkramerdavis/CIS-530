package edu.bellevue.cis530.week5.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bellevue.cis530.week5.entity.Student;
import edu.bellevue.cis530.week5.entity.StudentProfile;
import edu.bellevue.cis530.week5.service.StudentService;

import jakarta.validation.Valid;


/* 
 * This controller class handles HTTP requests related to Student entities. 
 * It provides endpoints for creating, retrieving, updating, and deleting students, 
 * as well as retrieving student profiles and filtering students by major or enrollment year. 
 * The class uses the StudentService to perform the necessary business logic and data access operations.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<StudentProfile> getProfileByStudentId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getProfileByStudentId(id));
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(
            @PathVariable String major) {

        return ResponseEntity.ok(
                studentService.getStudentsByMajor(major));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getStudentsByEnrollmentYear(
            @PathVariable Integer year) {

        return ResponseEntity.ok(
                studentService.getStudentsByEnrollmentYear(year));
    }
}