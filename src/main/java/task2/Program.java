package task2;

import models.Courses;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.Configuration;

public class Program {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Student student = new Student("Villy",54);
        Courses courses = new Courses("Разработчик JavaScript",3);
        session.beginTransaction();
        session.save(student);
        session.save(courses);
        session.getTransaction().commit();

        Student readStudent = session.get(Student.class, student.getId());
        System.out.println(readStudent);

        session.close();

    }
}
