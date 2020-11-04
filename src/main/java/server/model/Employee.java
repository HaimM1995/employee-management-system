package server.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "age")
    Integer age;

    @Column(name = "birthDate")
    LocalDateTime birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    //@DateTimeFormat(pattern = "DD/MM/YYYY")
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    EmployeeCar employeeCar;

    @ManyToOne(cascade = CascadeType.ALL)
    Factory factory;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)// Many to Many defults fetch = FetchType.LAZY
    @JoinTable(name = "company_employee",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "comapny_id", referencedColumnName = "id")})
    Set<Company> companyList = new HashSet<Company>();
    //List<Company> companyList;

    public Set<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(Set<Company> companyList) {
        this.companyList = companyList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Employee() {
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Integer getId() {
        return id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public EmployeeCar getEmployeeCar() {
        return employeeCar;
    }

    public void setEmployeeCar(EmployeeCar employeeCar) {
        this.employeeCar = employeeCar;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    @PrePersist
    public void preSave() {
        if (this.createdAt == null) {
            setCreatedAt(LocalDateTime.now());
        }
        if (this.updatedAt == null)
            setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }
//    @GeneratedValue(generator = "employee_generator")
//    @SequenceGenerator(
//            name = "employee_generator",
//            sequenceName = "employee_generator",
//            initialValue = 1
//    )
//    public Integer getId() {
//        return id;
//    }
}
