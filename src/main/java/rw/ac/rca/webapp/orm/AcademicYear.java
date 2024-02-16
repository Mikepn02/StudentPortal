package rw.ac.rca.webapp.orm;


import jakarta.persistence.*;

@Entity
@Table(name = "academic_year")
public class AcademicYear {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;
    private  String code;
    private String name;

    public AcademicYear(){}

    public AcademicYear(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
