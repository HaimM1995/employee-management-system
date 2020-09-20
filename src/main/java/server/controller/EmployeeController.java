package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.Employee;
import server.service.EmployeeService;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
