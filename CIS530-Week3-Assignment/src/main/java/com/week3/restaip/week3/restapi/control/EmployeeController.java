package com.week3.restaip.week3.restapi.control;

import com.week3.restaip.week3.restapi.entity.Employee;
import com.week3.restaip.week3.restapi.service.EmployeeServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceInter employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceInter employeeServiceInter) {
        employeeService = employeeServiceInter;
    }

    //GET /emplyees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    //GET /employee/{id}

    @GetMapping("{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    //POST /employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.create(employee);
        return ResponseEntity
                .created(URI.create("/employee/" + saved.getId()))
                .body(saved);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
