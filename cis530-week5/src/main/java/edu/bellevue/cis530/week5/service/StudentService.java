package edu.bellevue.cis530.week5.service;

import java.util.List;

import edu.bellevue.cis530.week5.entity.Student;
import edu.bellevue.cis530.week5.entity.StudentProfile;

// Service interface for managing students and their profiles. //
public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    StudentProfile getProfileByStudentId(Long id);

    List<Student> getStudentsByMajor(String major);

    List<Student> getStudentsByEnrollmentYear(Integer year);
}