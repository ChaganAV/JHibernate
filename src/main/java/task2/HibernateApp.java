package task2;

import models.Courses;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateApp {
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;
    public HibernateApp(){
        initialisation();
    }
    private void initialisation(){
        this.registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        this.sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }
    public void add(Courses courses){
        Session session = sessionFactory.openSession();

        //Student student = new Student("Villy",54);
        //Courses courses = new Courses("Разработчик JavaScript",3);
        session.beginTransaction();
        //session.save(student);
        session.save(courses);
        session.getTransaction().commit();
    }
}
