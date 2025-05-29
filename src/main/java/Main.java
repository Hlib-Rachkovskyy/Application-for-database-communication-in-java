
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;
import java.util.Scanner;

public class Main {
    static StandardServiceRegistry registry = null;
    static SessionFactory sessionFactory = null;
    public static void main(String[] args) throws ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int state = -1;
        while (state != 0) {
            String line = sc.nextLine();
            var arguments = line.split(" ");
            state = Integer.parseInt(arguments[0]);
            line = (arguments.length > 1 ? arguments[1] : null);
            if (state == 1) {
                selectFrom(line);
            } else if (state == 2) {
                dumpData();
            }
        }


        System.out.println("SELECT * FROM: \"1 Classname\"");
        System.out.println("DUMP DATA : 2");
        System.out.println("Exit: 0");
    }

    public static void dumpData() {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();

            String name = "Student" + (int) (Math.random() * 10);
            Person person = new Person(name);
            session.persist(person);

            List<Person> persons = session.createQuery("select u from Person u", Person.class).list();

            School school = new School("SchoolA");
            session.persist(school);

            Student student = new Student("John", "3a", school);
            session.persist(student);

            Student student1 = new Student("Not John", "3a", school);
            session.persist(student1);


            System.out.println(school);
            persons.forEach(System.out::println);
            session.getTransaction().commit();
        };

    }
        public static void selectFrom(String className){
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();
            Class<?> clazz = Class.forName(className);
            List<?> entities = session.createQuery("from " + clazz.getSimpleName(), clazz).list();
            entities.forEach(System.out::println);

            session.getTransaction().commit();
        } catch (ClassNotFoundException e) {
            System.err.println("No such class " + className);;
        }
    }
}
