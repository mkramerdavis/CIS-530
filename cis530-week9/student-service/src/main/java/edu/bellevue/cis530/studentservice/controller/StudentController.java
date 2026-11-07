package edu.bellevue.cis530.studentservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import edu.bellevue.cis530.studentservice.client.StudentProfileClient;
import edu.bellevue.cis530.studentservice.dto.StudentProfileDto;
import edu.bellevue.cis530.studentservice.dto.StudentWithProfileResponse;
import edu.bellevue.cis530.studentservice.entity.Student;
import edu.bellevue.cis530.studentservice.service.StudentService;

/*
 * Handles HTTP requests for Student records.
 * The controller receives requests and delegates the work
 * to StudentService.
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    // Handles Student database operations through the service layer.
    private final StudentService studentService;

    // Retrieves profile information from Student Profile Service.
    private final StudentProfileClient studentProfileClient;

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {

        // Validate the request, save the student, and return HTTP 201.
        Student savedStudent = studentService.createStudent(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        // Retrieve all student records and return HTTP 200.
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        // Retrieve the requested student by ID.
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        // Delete the requested student record.
        studentService.deleteStudent(id);

        // Return a confirmation after successful deletion.
        return ResponseEntity.ok("Student deleted successfully");
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<StudentWithProfileResponse> getStudentWithProfile(
            @PathVariable Long id) {

        // Retrieve the student record from Student Service.
        Student student = studentService.getStudentById(id);

        // Use Feign to retrieve the matching profile from
        // Student Profile Service.
        StudentProfileDto profile =
                studentProfileClient.getProfileByStudentId(id);

        // Combine both records into one response object.
        StudentWithProfileResponse response =
                new StudentWithProfileResponse(student, profile);

        // Return the combined student and profile information.
        return ResponseEntity.ok(response);
    }
}