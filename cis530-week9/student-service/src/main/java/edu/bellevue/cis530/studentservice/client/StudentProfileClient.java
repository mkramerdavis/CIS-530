package edu.bellevue.cis530.studentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.bellevue.cis530.studentservice.dto.StudentProfileDto;

/*
 * Feign client used by Student Service to communicate
 * with Student Profile Service.
 *
 * Eureka discovers the service using its registered name,
 * so the URL does not need to be hard-coded here.
 */
@FeignClient(name = "student-profile-service")
public interface StudentProfileClient {

    // Retrieves a student's profile using the student ID.
    @GetMapping("/api/profiles/student/{studentId}")
    StudentProfileDto getProfileByStudentId(
            @PathVariable("studentId") Long studentId);
}