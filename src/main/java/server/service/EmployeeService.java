package server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.exception.ResourceNotFoundException;
import server.model.Employee;
import server.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService() {
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById (int id) {
        return this.employeeRepository.findById(id);
    }

//    public ResponseEntity<Employee> findEmployeeById(int id) throws ResourceNotFoundException {
//        Employee employee = (Employee)this.employeeRepository.findById(id).orElseThrow(() -> {
//            return new ResourceNotFoundException("employee not exist with id:" + id);
//        });
//        return ResponseEntity.ok(employee);
//    }

    public Employee saveEmployee(Employee employee) {
        return (Employee)this.employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployeeById(int id, Employee employee) {
        Optional<Employee> employeeToUpdate = this.employeeRepository.findById(id);
        if(employeeToUpdate.isPresent()) {
            employeeToUpdate.get().
                    setFirstName(employee.getFirstName()).
                    setLastName(employee.getLastName()).
                    setEmail(employee.getEmail());
            return Optional.of(this.saveEmployee(employeeToUpdate.get()));
        } else {
            return Optional.empty();
        }
    }

    public Boolean deleteEmployee(int id) {
        Boolean isDeleted;
        Optional<Employee> employeeToDelete = this.employeeRepository.findById(id);

        if(employeeToDelete.isPresent()) {
            this.employeeRepository.deleteById(id);
            isDeleted = Boolean.TRUE;
        } else {
            isDeleted = Boolean.FALSE;
        }

        return isDeleted;
        //        Map<String, Boolean> response = new HashMap();
        //            response.put("deleted", Boolean.TRUE);
        //            response.put("Notdeleted", Boolean.FALSE);
    }

    //    public ResponseEntity<Employee> updateEmployeeById(int id, Employee employee) throws ResourceNotFoundException {
//        Employee employeeToUpdate = (Employee)this.findEmployeeById(id).getBody();
//        employeeToUpdate.setFirstName(employee.getFirstName());
//        employeeToUpdate.setLastName(employee.getLastName());
//        employeeToUpdate.setEmail(employee.getEmail());
//        return ResponseEntity.ok(this.saveEmployee(employeeToUpdate));
//    }

}
