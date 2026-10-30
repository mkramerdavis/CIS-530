package edu.bellevue.cis530.week8.service;

// JUnit assertions used to verify test results
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Mockito methods used to configure and verify mock behavior
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.bellevue.cis530.week8.entity.Student;
import edu.bellevue.cis530.week8.repository.StudentRepository;
import edu.bellevue.cis530.week8.service.impl.StudentServiceImpl;


// Enables Mockito support for JUnit 5 tests
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    // Creates a mock repository so tests do not connect to the database
    @Mock
    private StudentRepository studentRepository;

    // Injects the mock repository into the service being tested
    @InjectMocks
    private StudentServiceImpl studentService;

    // Student object used by the test methods
    private Student student;


    // Runs before each test to create sample student data
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


    // Tests that a new student is saved successfully
    @Test
    void createStudent() {

        // Configures the mock repository to return the student when saved
        when(studentRepository.save(student)).thenReturn(student);

        // Calls the service method to create the student
        Student result = studentService.createStudent(student);

        // Verifies that the returned student contains the expected information
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Smith", result.getLastName());

        // Verifies that the repository save method was called once
        verify(studentRepository, times(1)).save(student);
    }


    // Tests that all students are retrieved successfully
    @Test
    void getAllStudents() {

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

        // Creates a sample list containing both students
        List<Student> students = Arrays.asList(student, secondStudent);

        // Configures the mock repository to return the sample list
        when(studentRepository.findAll()).thenReturn(students);

        // Calls the service method to retrieve all students
        List<Student> result = studentService.getAllStudents();

        // Verifies the list size and the student names
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());

        // Verifies that the repository findAll method was called once
        verify(studentRepository, times(1)).findAll();
    }


    // Tests that a student can be retrieved using their ID
    @Test
    void getStudentById() {

        // Configures the mock repository to return the student with ID 1
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Calls the service method to retrieve the student
        Student result = studentService.getStudentById(1L);

        // Verifies that the correct student was returned
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());

        // Verifies that the repository findById method was called once
        verify(studentRepository, times(1)).findById(1L);
    }


    // Tests that a student can be deleted using their ID
    @Test
    void deleteStudent() {

        // Configures the mock repository to find the student with ID 1
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Calls the service method to delete the student
        studentService.deleteStudent(1L);

        // Verifies that the repository searches for the student
        verify(studentRepository, times(1)).findById(1L);

        // Verifies that the repository deletes the correct student
        verify(studentRepository, times(1)).delete(student);
    }
}
