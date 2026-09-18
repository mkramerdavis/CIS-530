package edu.bellevue.cis530.week2.repository;

import edu.bellevue.cis530.week2.model.Student;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Repository class for managing Student entities in memory.
 */
@Repository
public class StudentRepository {

    private final Map<Long, Student> students = new HashMap<>();
    
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Student findById(Long id) {
        return students.get(id);
    }

    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student update(Long id, Student student) {
        students.put(id, student);
        return student;
    }

    public void delete(Long id) {
        students.remove(id);
    }
    
}
