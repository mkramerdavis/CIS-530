package edu.bellevue.cis530.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bellevue.cis530.studentservice.entity.Student;

/*
 * Provides database operations for Student records.
 * JpaRepository supplies common CRUD methods without requiring
 * us to write the SQL queries manually.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Standard methods include save(), findAll(), findById(),
    // existsById(), and deleteById().
}