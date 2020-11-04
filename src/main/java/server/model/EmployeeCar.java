package server.model;

import javax.persistence.*;

@Entity
@Table(name = "employeeCar")
public class EmployeeCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "type")
    String type;

    @Column(name = "color")
    String color;

    @Column(name = "year")
    int year;

    @OneToOne(mappedBy = "employeeCar")
    Employee carOwner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
