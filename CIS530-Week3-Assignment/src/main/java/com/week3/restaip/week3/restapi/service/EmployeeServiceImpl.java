package com.week3.restaip.week3.restapi.service;

import com.week3.restaip.week3.restapi.dao.EmployeeDAO;
import com.week3.restaip.week3.restapi.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeServiceInter {

    private final EmployeeDAO employeeDAO;
    ;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee update(int id, Employee employee) {
        Employee existing = findById(id);
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());

        return employeeDAO.save(existing);

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Employee employee = findById(id);
        employeeDAO.deleteById(employee.getId());
    }
}
