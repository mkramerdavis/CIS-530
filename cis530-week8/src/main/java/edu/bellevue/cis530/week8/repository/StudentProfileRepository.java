package edu.bellevue.cis530.week8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bellevue.cis530.week8.entity.StudentProfile;

// Repository interface for managing StudentProfile entities //
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
