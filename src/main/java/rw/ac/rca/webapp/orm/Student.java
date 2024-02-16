package rw.ac.rca.webapp.orm;


;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Student extends Person {
    private static final long serialVersionUID = -8680703317249517930L;
    private boolean isInternational;
    private boolean isPartTime;
    private boolean isRepeating;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Student(){}

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }

    public boolean isPartTime() {
        return isPartTime;
    }

    public void setPartTime(boolean partTime) {
        isPartTime = partTime;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public void setRepeating(boolean repeating) {
        isRepeating = repeating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Student(String firstName, String lastName, String email, Date dateOfBirth, String studentCode, boolean isInternational, boolean isPartTime, boolean isRepeating) {
        super(firstName, lastName, email, dateOfBirth, studentCode);
        this.isInternational = isInternational;
        this.isPartTime = isPartTime;
        this.isRepeating = isRepeating;
    }
}
