package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.model.EmployeeCar;
import server.service.EmployeeCarService;

import java.util.List;

@CrossOrigin
@RestController
public class EmployeeCarController {

    @Autowired
    EmployeeCarService employeeCarService;

    @RequestMapping(method = RequestMethod.GET, value = "/employeeCar")
    public ResponseEntity<List<EmployeeCar>> fetchAll() {
        return ResponseEntity.ok(this.employeeCarService.getAll());
    }
}
