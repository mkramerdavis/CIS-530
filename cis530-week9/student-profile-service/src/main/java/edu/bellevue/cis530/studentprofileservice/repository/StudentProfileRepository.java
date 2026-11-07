package edu.bellevue.cis530.studentprofileservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bellevue.cis530.studentprofileservice.entity.StudentProfile;

/*
 * Provides database operations for StudentProfile records.
 * JpaRepository supplies standard CRUD methods.
 */
public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

    // Finds a profile using the ID of the associated student.
    Optional<StudentProfile> findByStudentId(Long studentId);
}