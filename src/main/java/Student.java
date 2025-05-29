import jakarta.persistence.*;

import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "Person_id")
class Student extends Person {
    @Column(name = "Klasa", length = 10)
    private String classGroup;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student() {

    }

    public Student(String name, String classGroup, School school) {
        super(name);
        this.classGroup = classGroup;
        this.school = school;
        school.addStudentToSchool(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        if (getId() == 0 || other.getId() == 0) return false;
        return getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
                "classGroup='" + classGroup + '\'' +
                ", school=" + school.getId() +
                '}' + super.toString();
    }
}
