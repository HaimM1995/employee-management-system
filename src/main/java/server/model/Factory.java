package server.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "factory")
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "factoryName")
    String factoryName;

    @Column(name = "factoryAddress")
    String factoryAddress;

    @OneToMany(mappedBy = "factory", fetch = FetchType.LAZY)
    List<Employee> employeeList;

    public Factory() {
    }

    public Factory(Integer id, String factoryName, String factoryAddress, List<Employee> employeeList) {
        this.id = id;
        this.factoryName = factoryName;
        this.factoryAddress = factoryAddress;
        this.employeeList = employeeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryAddress() {
        return factoryAddress;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }
}
