package edu.bellevue.cis530.studentservice.dto;

import java.math.BigDecimal;

import lombok.Data;

/*
 * Represents profile information received from the
 * Student Profile Service.
 *
 * This DTO allows the Student Service to receive profile data
 * without directly using the other service's entity class.
 */
@Data
public class StudentProfileDto {

    // ID of the student profile record.
    private Long id;

    // ID of the student associated with this profile.
    private Long studentId;

    // Student's declared major.
    private String major;

    // Student's grade point average.
    private BigDecimal gpa;

    // Additional information about the student.
    private String bio;
}