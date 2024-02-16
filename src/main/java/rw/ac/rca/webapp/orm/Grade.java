package rw.ac.rca.webapp.orm;

public class Grade {
    private GradeLevel grade;

    public GradeLevel getGrade() {
        return grade;
    }

    public void setGrade(GradeLevel grade) {
        this.grade = grade;
    }

    public Grade(GradeLevel grade) {
        this.grade = grade;
    }
}
