package edu.bellevue.cis530.studentprofileservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Represents a student's profile information stored in the
 * student_profile_db database.
 * This entity maps to the student_profiles table.
 */
@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {

    // Primary key generated automatically by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stores the ID of the related student in Student Service.
    // This is a logical reference, not a foreign key.
    @NotNull
    @Column(name = "student_id", nullable = false, unique = true)
    private Long studentId;

    // Student's major is required.
    @NotBlank
    private String major;

    // Student's GPA is required.
    @NotNull
    private BigDecimal gpa;

    // Optional field containing additional student information.
    private String bio;
}