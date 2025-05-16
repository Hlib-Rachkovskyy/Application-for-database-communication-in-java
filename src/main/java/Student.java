import jakarta.persistence.*;

import java.util.List;

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
    }

    @Override
    public String toString() {
        return "Student{" +
                "classGroup='" + classGroup + '\'' +
                ", school=" + school.getId() +
                '}' + super.toString();
    }
}
