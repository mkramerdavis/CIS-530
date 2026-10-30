
package edu.bellevue.cis530.week8.controller;

// Importing necessary classes for handling HTTP requests and responses.
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

// Importing Student and StudentProfile entities and StudentService.
import edu.bellevue.cis530.week8.entity.Student;
import edu.bellevue.cis530.week8.entity.StudentProfile;
import edu.bellevue.cis530.week8.service.StudentService;

// Importing Jakarta Validation API.
import jakarta.validation.Valid;

// Swagger / OpenAPI annotations for API documentation.
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

// Identifies this controller as the Student API in Swagger UI.
@Tag(
    name = "Student API",
    description = "API endpoints for managing student information and profiles"
)

/*
 * This controller class handles HTTP requests related to Student entities.
 * It provides endpoints for creating, retrieving, updating, and deleting students,
 * as well as retrieving student profiles and filtering students by major or enrollment year.
 * The class uses the StudentService to perform business logic and data access operations.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Creates a new student record.
    @Operation(
        summary = "Create a student",
        description = "Creates a new student record using the supplied student information."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid student information"
        )
    })
    @PostMapping
    public ResponseEntity<Student> createStudent(
        @Valid 
        @RequestBody Student student) {

        return ResponseEntity.ok(studentService.createStudent(student));
    }

    // Retrieves all student records.
    @Operation(
        summary = "Get all students",
        description = "Retrieves a list of all students stored in the database."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Students retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(
                    schema = @Schema(implementation = Student.class)
                )
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Retrieves a student using the student ID.
    @Operation(
        summary = "Get student by ID",
        description = "Retrieves an individual student record using its unique ID."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @Parameter(description = "Unique ID of the student", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // Updates an existing student record.
    @Operation(
        summary = "Update a student",
        description = "Updates an existing student record using the student ID and supplied student information."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student updated successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid student information"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    // Deletes a student using the student ID.
    @Operation(
        summary = "Delete a student",
        description = "Deletes an existing student record using its unique ID."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Student deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "Unique ID of the student", example = "1")
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // Retrieves a student's profile.
    @Operation(
        summary = "Get student profile",
        description = "Retrieves the profile information associated with a specific student."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student profile retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = StudentProfile.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student or profile not found"
        )
    })
    @GetMapping("/{id}/profile")
    public ResponseEntity<StudentProfile> getProfileByStudentId(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getProfileByStudentId(id));
    }

    // Retrieves students filtered by major.
    @Operation(
        summary = "Get students by major",
        description = "Retrieves a list of students filtered by their major."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Students retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(
                    schema = @Schema(implementation = Student.class)
                )
            )
        )
    })
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(
            @PathVariable String major) {

        return ResponseEntity.ok(studentService.getStudentsByMajor(major));
    }

    // Retrieves students filtered by enrollment year.
    @Operation(
        summary = "Get students by enrollment year",
        description = "Retrieves a list of students filtered by their enrollment year."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Students retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(
                    schema = @Schema(implementation = Student.class)
                )
            )
        )
    })
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getStudentsByEnrollmentYear(
            @PathVariable Integer year) {

        return ResponseEntity.ok(studentService.getStudentsByEnrollmentYear(year));
    }
}
