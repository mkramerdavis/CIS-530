package edu.bellevue.cis530.studentprofileservice.service;

import java.util.List;

import edu.bellevue.cis530.studentprofileservice.entity.StudentProfile;

/*
 * Defines the operations available for managing student profiles.
 * The controller uses this interface to access the service layer.
 */
public interface StudentProfileService {

    // Creates and saves a new student profile.
    StudentProfile createProfile(StudentProfile profile);

    // Retrieves all student profiles.
    List<StudentProfile> getAllProfiles();

    // Retrieves one profile using its profile ID.
    StudentProfile getProfileById(Long id);

    // Retrieves a profile using the associated student ID.
    StudentProfile getProfileByStudentId(Long studentId);

    // Deletes a profile using its profile ID.
    void deleteProfile(Long id);
}