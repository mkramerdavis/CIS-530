package com.week3.restaip.week3.restapi.service;

import com.week3.restaip.week3.restapi.entity.Employee;

import java.util.List;


public interface EmployeeServiceInter {

    List<Employee> findAll();

    Employee findById(int id);

    Employee create(Employee employee);

    Employee update(int id, Employee employee);

    void deleteById(int id);
}
