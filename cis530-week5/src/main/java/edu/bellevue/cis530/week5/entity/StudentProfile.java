package edu.bellevue.cis530.week5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;



// StudentProfile entity representing a profile associated with a student in the system //
@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone must be in the format 402-293-2000")
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @NotBlank
    @Size (min = 2, max = 2, message = "State must be a valid two-letter state code")
    @Column(name = "state", length = 2)
    private String state;

    @NotBlank
    @Pattern(regexp = "\\d{5}", message = "Zip code must be a valid five-digit zip code")
    @Column(name = "zip", length = 5)
    private String zip;

    @NotBlank
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @NotBlank
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone must be in the format 402-293-2000")
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @JsonBackReference 
    @OneToOne(mappedBy = "profile")
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(Long id, String phone, String address, String city,
                          String state, String zip, String emergencyContactName,
                          String emergencyContactPhone) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    // Getters and Setters //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
