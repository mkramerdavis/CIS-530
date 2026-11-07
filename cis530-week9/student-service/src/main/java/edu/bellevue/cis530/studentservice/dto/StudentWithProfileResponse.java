package edu.bellevue.cis530.studentservice.dto;

import edu.bellevue.cis530.studentservice.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Combines Student information with Student Profile information.
 * This allows the API to return both records in one response
 * when the client requests a student's full details.
 */
@Data
@AllArgsConstructor
public class StudentWithProfileResponse {

    // Student information retrieved from Student Service.
    private Student student;

    // Profile information retrieved from Student Profile Service.
    private StudentProfileDto profile;
}