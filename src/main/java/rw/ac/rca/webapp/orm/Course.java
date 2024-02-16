/**
 *
 */
package rw.ac.rca.webapp.orm;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;



/**
 * @author Aphrodice Rwagaju
 *
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Course(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String code;
    private int minStudent;
    private int maxStudent;
    private Date start;
    private Date end;
    private boolean isCancelled;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
//    private List<Enrol> enrols;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_teacher", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private Set<Teacher> instructors;


    public Set<Teacher> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Teacher> instructors) {
        this.instructors = instructors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMinStudent() {
        return minStudent;
    }

    public void setMinStudent(int minStudent) {
        this.minStudent = minStudent;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }




    public Course(String name, String code, int minStudent, int maxStudent, Date start, Date end,
                  boolean isCancelled) {
        this.name = name;
        this.code = code;
        this.minStudent = minStudent;
        this.maxStudent = maxStudent;
        this.start = start;
        this.end = end;
        this.isCancelled = isCancelled;
    }


}
