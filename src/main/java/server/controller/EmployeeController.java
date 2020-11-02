package server.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.exception.ResourceNotFoundException;
import server.model.Employee;
import server.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/employee")
    public List<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/employee/{id}"
    )
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Optional<Employee> employee = this.employeeService.findEmployeeById(id);
        if(employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/employee"
    )
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeService.saveEmployee(employee);
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            value = {"/employee/{id}"}
    )
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) throws ResourceNotFoundException {
        Optional<Employee> updatedEmployee = this.employeeService.updateEmployeeById(id, employee);
        if (updatedEmployee.isPresent()) {
            return ResponseEntity.ok(updatedEmployee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/employee/{id}"
    )
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        if(this.employeeService.deleteEmployee(id)) {
            return ResponseEntity.ok( id + " " + "Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
