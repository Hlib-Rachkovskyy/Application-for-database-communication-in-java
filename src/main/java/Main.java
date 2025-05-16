
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
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();

            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


        } catch (Exception e) {
            e.printStackTrace();
        }



        Scanner sc = new Scanner(System.in);
        int commandNumber = -1;
        System.out.println("SELECT * FROM: 1 Classname (Write)");
        System.out.println("INSERT NEW : 2");
        System.out.println("DELETE FROM: 3");
        System.out.println("DUMP DATA : 4");
        System.out.println("SELECT Student by school id: 5");
        System.out.println("Exit: 0");

        while (commandNumber != 0) {
            String argument = sc.nextLine();
            var arguments = argument.split(" ");
            commandNumber = Integer.parseInt(arguments[0]);
            argument = (arguments.length > 1 ? arguments[1] : null);
            switch (commandNumber) {
                case 0:
                    break;
                case 1:
                    selectFrom(argument);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    dumpData();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }
    }
    public static void dumpData() throws ClassNotFoundException {
        try (Session session = sessionFactory.openSession()){

            session.beginTransaction();
            System.out.println(Class.forName("Person"));
            session.persist(new Person("Someguy" + (int) (Math.random() * 10) ));
            List<Person> persons = session.createQuery("select u from Person u", Person.class).list();
            School school = new School("Some school");
            Student student = new Student("Someguy", "3a", school);
            session.persist(school);

            session.persist(student);
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
