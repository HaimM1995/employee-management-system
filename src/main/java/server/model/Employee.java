package server.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

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
