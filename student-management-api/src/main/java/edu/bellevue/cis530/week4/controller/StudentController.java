package edu.bellevue.cis530.week4.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bellevue.cis530.week4.entity.Student;
import edu.bellevue.cis530.week4.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudentById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getByMajor(
            @PathVariable String major) {

        return ResponseEntity.ok(studentService.getByMajor(major));
    }

    @GetMapping("/gpa/{gpa}")
    public ResponseEntity<List<Student>> getByGpa(
            @PathVariable Double gpa) {

        return ResponseEntity.ok(
                studentService.getByGpaGreaterThan(gpa));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getByEnrollmentYear(
            @PathVariable Integer year) {

        return ResponseEntity.ok(
                studentService.getByEnrollmentYearGreaterThan(year));
    }

    @GetMapping("/top3")
    public ResponseEntity<List<Student>> getTop3() {

        return ResponseEntity.ok(
                studentService.getTop3ByGpa());
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Student>> getSortedStudents(
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                studentService.getAllSortedByLastName(direction));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getPagedStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                studentService.getStudentsPaged(page, size));
    }

    @GetMapping("/major-jpql/{major}")
    public ResponseEntity<List<Student>> getByMajorJpql(
            @PathVariable String major) {

        return ResponseEntity.ok(
                studentService.getByMajorJpql(major));
    }

    @DeleteMapping("/year/{year}/jpql")
    public ResponseEntity<String> deleteByEnrollmentYearJpql(
            @PathVariable Integer year) {

        int deleted = studentService.deleteByEnrollmentYearJpql(year);

        return ResponseEntity.ok(
                deleted + " student(s) deleted.");
    }

}