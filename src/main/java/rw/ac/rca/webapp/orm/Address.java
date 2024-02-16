package rw.ac.rca.webapp.orm;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    public  Address(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String city;
    private String streetAddress;
    private String postalCode;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "address")
    private Set<Student> students;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "address")
    private Set<Teacher> teachers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Address(String country, String city, String streetAddress, String postalCode) {
        this.country = country;
        this.city = city;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
    }
}
