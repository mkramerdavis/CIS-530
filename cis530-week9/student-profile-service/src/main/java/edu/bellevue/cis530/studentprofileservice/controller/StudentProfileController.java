package edu.bellevue.cis530.studentprofileservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import edu.bellevue.cis530.studentprofileservice.entity.StudentProfile;
import edu.bellevue.cis530.studentprofileservice.service.StudentProfileService;

/*
 * Handles HTTP requests for Student Profile records.
 * The controller receives requests and delegates the work
 * to StudentProfileService.
 */
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class StudentProfileController {

    // Handles profile operations through the service layer.
    private final StudentProfileService studentProfileService;

    @PostMapping
    public ResponseEntity<StudentProfile> createProfile(
            @Valid @RequestBody StudentProfile profile) {

        // Validate the request, save the profile, and return HTTP 201.
        StudentProfile savedProfile =
                studentProfileService.createProfile(profile);

        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllProfiles() {

        // Retrieve all profiles and return HTTP 200.
        return ResponseEntity.ok(studentProfileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getProfileById(
            @PathVariable Long id) {

        // Retrieve the requested profile using its profile ID.
        return ResponseEntity.ok(studentProfileService.getProfileById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentProfile> getProfileByStudentId(
            @PathVariable Long studentId) {

        // Retrieve the profile associated with the specified student.
        return ResponseEntity.ok(
                studentProfileService.getProfileByStudentId(studentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(
            @PathVariable Long id) {

        // Delete the requested profile record.
        studentProfileService.deleteProfile(id);

        // Return a confirmation after successful deletion.
        return ResponseEntity.ok("Profile deleted successfully");
    }
}