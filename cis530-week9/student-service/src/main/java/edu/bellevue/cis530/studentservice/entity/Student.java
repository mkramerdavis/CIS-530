package edu.bellevue.cis530.studentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Represents a student record stored in the student_db database.
 * This entity maps to the students table.
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    // Primary key generated automatically by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student's first name is required.
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    // Student's last name is required.
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    // Student email is required, must be valid, and must be unique.
    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;
}