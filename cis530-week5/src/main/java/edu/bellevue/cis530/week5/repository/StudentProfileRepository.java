package edu.bellevue.cis530.week5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.bellevue.cis530.week5.entity.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
