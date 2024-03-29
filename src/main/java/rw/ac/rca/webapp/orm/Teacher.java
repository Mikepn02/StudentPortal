/**
 *
 */
package rw.ac.rca.webapp.orm;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;



/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
//@DiscriminatorValue("Instuctor")
public class Teacher extends Person {

    /**
     *
     */
    private static final long serialVersionUID = 6073878228230771199L;
    private double salary;
    private Date remunerationDate;

    @ManyToMany(cascade= CascadeType.ALL, mappedBy = "instructors")
    private Set<Course> courses;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Date getRemunerationDate() {
        return remunerationDate;
    }
    public Set<Course> getCourses() {
        return courses;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    public void setRemunerationDate(Date remunerationDate) {
        this.remunerationDate = remunerationDate;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public Teacher() {
    }

    public Teacher(double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
        //if(course.getInstructors()!=null)
        course.getInstructors().add(this);
    }
    public void removeCourse(Course course) {
        this.courses.remove(course);
        course.getInstructors().remove(this);
    }

}
