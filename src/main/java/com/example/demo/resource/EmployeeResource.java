package com.example.demo.resource;


import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.resource.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    private EmployeeMapper employeeMapper;

    public EmployeeResource(EmployeeMapper employeeMapper){
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/all")
    public List<Employee> getAll(){
        return employeeMapper.findAll();
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        employeeMapper.insert(employee);
        Long id = employeeMapper.getCurrentId();
        return employeeMapper.getEmployee(id);

    }

    @GetMapping("/find/{id}")
    public Employee getAllEmployeeById(@PathVariable("id") Long id){
        return employeeMapper.getEmployee(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeMapper.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee){
        Long id = employee.getId();

        employeeMapper.updateEmployee(employee);
        return employeeMapper.getEmployee(id);
    }
}
