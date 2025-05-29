import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "School")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name", length = 100)
    private String name;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Student> students = new ArrayList<>();

    public School() {

    }

    public School(String name) {
        this.name = name;
    }

    public void addStudentToSchool(Student student){
        if (students.contains(student)){
            throw new IllegalArgumentException("This school already have this student");
        } else {
            students.add(student);
        }
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public String getId() {
    return String.valueOf(id);
    }
}
