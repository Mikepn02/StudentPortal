package rw.ac.rca.webapp.orm;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee extends Person{
    public Employee(String firstName, String lastName,String email , Date dateOfBirth, String phoneNumber) {
        super(firstName, lastName, email ,dateOfBirth, phoneNumber);
    }
    public Employee() {}
}
