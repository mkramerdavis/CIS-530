package edu.bellevue.cis530.week5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.bellevue.cis530.week5.entity.Student;
import edu.bellevue.cis530.week5.entity.StudentProfile;
import edu.bellevue.cis530.week5.exception.ResourceNotFoundException;
import edu.bellevue.cis530.week5.repository.StudentRepository;


// Service implementation for managing students and their profiles. //
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {

        if (student.getProfile() != null) {
            student.getProfile().setStudent(student);
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with id " + id + " not found"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setGpa(student.getGpa());
        existingStudent.setEnrollmentYear(student.getEnrollmentYear());

        if (student.getProfile() != null) {

            if (existingStudent.getProfile() == null) {
                existingStudent.setProfile(student.getProfile());
            } else {
                StudentProfile existingProfile = existingStudent.getProfile();

                existingProfile.setPhone(student.getProfile().getPhone());
                existingProfile.setAddress(student.getProfile().getAddress());
                existingProfile.setCity(student.getProfile().getCity());
                existingProfile.setState(student.getProfile().getState());
                existingProfile.setZip(student.getProfile().getZip());
                existingProfile.setEmergencyContactName(
                        student.getProfile().getEmergencyContactName());
                existingProfile.setEmergencyContactPhone(
                        student.getProfile().getEmergencyContactPhone());
            }

            existingStudent.getProfile().setStudent(existingStudent);
        }

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }

    @Override
    public StudentProfile getProfileByStudentId(Long id) {

        Student student = getStudentById(id);

        if (student.getProfile() == null) {
            throw new ResourceNotFoundException(
                    "Profile for student with id " + id + " not found");
        }

        return student.getProfile();
    }

    @Override
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    @Override
    public List<Student> getStudentsByEnrollmentYear(Integer year) {
        return studentRepository.findByEnrollmentYear(year);
    }
}