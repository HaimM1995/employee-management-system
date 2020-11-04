package server.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import server.repository.dto.EmployeeDTO;
import server.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    ModelMapper modelMapper = new ModelMapper();

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/employee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees().stream()
                .map(e-> modelMapper.map(e,EmployeeDTO.class))
                .collect(Collectors.toList()));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/employee/{id}"
    )
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable int id) {
        Optional<Employee> employee = this.employeeService.findEmployeeById(id);
        if(employee.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(employee.get(),EmployeeDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/employee"
    )
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Optional<Employee> e = this.employeeService.saveEmployee(employee);
        if(e.isPresent()) {
            return ResponseEntity.ok(e.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            value = {"/employee/{id}"}
    )
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody Employee employee) throws ResourceNotFoundException {
        Optional<Employee> updatedEmployee = this.employeeService.updateEmployeeById(id, employee);
        if (updatedEmployee.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(updatedEmployee.get(),EmployeeDTO.class));
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
