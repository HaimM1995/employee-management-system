package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.EmployeeCar;
import server.repository.EmployeeCarRepository;

import java.util.List;

@Service
public class EmployeeCarService {
    @Autowired
    EmployeeCarRepository employeeCarRepository;

    public List<EmployeeCar> getAll() {
        return this.employeeCarRepository.findAll();
    }
}
