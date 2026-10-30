package edu.bellevue.cis530.week8.controller;

// JUnit assertions and Mockito methods
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// MockMvc request and response testing methods
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.bellevue.cis530.week8.entity.Student;
import edu.bellevue.cis530.week8.service.StudentService;


// Loads only the StudentController for testing
@WebMvcTest(StudentController.class)

// Disables security filters so tests can focus on controller endpoints
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    // Provides a way to send HTTP requests and examine responses
    @Autowired
    private MockMvc mockMvc;

    // Creates a mock StudentService instead of using the actual service
    @MockitoBean
    private StudentService studentService;

    // Sample student used by the controller tests
    private Student student;


    // Creates sample student data before each test
    @BeforeEach
    void setUp() {
        student = new Student(
                1L,
                "John",
                "Smith",
                "john.smith@example.com",
                "Computer Science",
                new BigDecimal("3.75"),
                2025,
                null
        );
    }


    // Tests the POST endpoint for creating a student
    @Test
    void createStudent() throws Exception {

        // Configures the mock service to return the sample student
        when(studentService.createStudent(any(Student.class)))
                .thenReturn(student);

        // Sends a POST request with student information
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "firstName": "John",
                            "lastName": "Smith",
                            "email": "john.smith@example.com",
                            "major": "Computer Science",
                            "gpa": 3.75,
                            "enrollmentYear": 2025
                        }
                        """))

                // Verifies that the response status is HTTP 200 OK
                .andExpect(status().isOk())

                // Verifies that the response contains the expected student data
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.major").value("Computer Science"));

        // Verifies that the service createStudent method was called once
        verify(studentService, times(1)).createStudent(any(Student.class));
    }


    // Tests the GET endpoint for retrieving all students
    @Test
    void getAllStudents() throws Exception {

        // Creates a second sample student
        Student secondStudent = new Student(
                2L,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "Information Technology",
                new BigDecimal("3.90"),
                2024,
                null
        );

        // Creates a list containing both sample students
        List<Student> students = Arrays.asList(student, secondStudent);

        // Configures the mock service to return the student list
        when(studentService.getAllStudents()).thenReturn(students);

        // Sends a GET request to retrieve all students
        mockMvc.perform(get("/api/students"))

                // Verifies that the response status is HTTP 200 OK
                .andExpect(status().isOk())

                // Verifies the number of students returned
                .andExpect(jsonPath("$.length()").value(2))

                // Verifies information about the first student
                .andExpect(jsonPath("$[0].firstName").value("John"))

                // Verifies information about the second student
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        // Verifies that the service getAllStudents method was called once
        verify(studentService, times(1)).getAllStudents();
    }


    // Tests the GET endpoint for retrieving a student by ID
    @Test
    void getStudentById() throws Exception {

        // Configures the mock service to return the student with ID 1
        when(studentService.getStudentById(1L)).thenReturn(student);

        // Sends a GET request using the student's ID
        mockMvc.perform(get("/api/students/1"))

                // Verifies that the response status is HTTP 200 OK
                .andExpect(status().isOk())

                // Verifies that the correct student information is returned
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.email")
                        .value("john.smith@example.com"));

        // Verifies that the service getStudentById method was called once
        verify(studentService, times(1)).getStudentById(1L);
    }


    // Tests the DELETE endpoint for removing a student by ID
    @Test
    void deleteStudent() throws Exception {

        // Sends a DELETE request using the student's ID
        mockMvc.perform(delete("/api/students/1"))

                // Verifies that the response status is HTTP 204 No Content
                .andExpect(status().isNoContent());

        // Verifies that the service deleteStudent method was called once
        verify(studentService, times(1)).deleteStudent(1L);
    }
}
