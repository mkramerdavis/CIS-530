package edu.bellevue.cis530.week4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.bellevue.cis530.week4.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByMajor(String major);

    List<Student> findByGpaGreaterThan(Double gpa);

    List<Student> findByEnrollmentYearGreaterThan(Integer year);

    List<Student> findTop3ByOrderByGpaDesc();

    List<Student> findAllByOrderByLastNameAsc();

    List<Student> findAllByOrderByLastNameDesc();

    @Query("SELECT s FROM Student s WHERE s.major = :major ORDER BY s.gpa DESC")
    List<Student> findByMajorUsingJpql(@Param("major") String major);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.enrollmentYear = :year")
    int deleteByEnrollmentYearUsingJpql(@Param("year") Integer year);

}