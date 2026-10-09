package edu.bellevue.cis530.week5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bellevue.cis530.week5.entity.Student;


// This interface extends JpaRepository to provide CRUD operations for Student entities. //
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByMajor(String major);

    List<Student> findByEnrollmentYear(Integer enrollmentYear);
}