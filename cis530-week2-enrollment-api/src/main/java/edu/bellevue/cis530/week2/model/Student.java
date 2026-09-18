package edu.bellevue.cis530.week2.model;

/*
 * This class represents a Student entity with attributes such as 
 * id, firstName, lastName, email, courseCode, and semester.
 */
public class Student {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String courseCode;
    private String semester;

    public Student() {
    }

    // Constructor with parameters //
    public Student(Long id, String firstName, String lastName, String email,
                   String courseCode, String semester) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courseCode = courseCode;
        this.semester = semester;
    }

    // Getters and Setters //
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSemester() {
        return semester;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    // Override toString method for Student object //
    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", semester='" + semester + '\'' +
               '}';
    }
    
}
