package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Employee;
import server.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee getEmployee(int id) {
        return this.employeeRepository.findById(id).orElseThrow();
    }

    public Employee createEmployee(Employee employee){
        return this.employeeRepository.save(employee);
    }

}
