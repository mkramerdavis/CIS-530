package com.week3.restaip.week3.restapi.dao;

import com.week3.restaip.week3.restapi.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    Employee save(Employee employee); // create or update

    void delete(Employee employee);

    void deleteById(int id);

}
