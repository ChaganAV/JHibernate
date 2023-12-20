package models;

import models.Courses;
import models.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CourserRepo implements Repository<Courses,Integer> {
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;
    public CourserRepo(){
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
    @Override
    public void add(Courses courses){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(courses);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Courses courses) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(courses);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Courses courses) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(courses);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Courses getById(Integer id) {
        Session session = sessionFactory.openSession();
        Courses courses = session.get(Courses.class, id);
        return courses;
    }

    @Override
    public Collection getAll() {
        List<Courses> list;
        String readDataSQL = "FROM Courses";
        Session session = sessionFactory.openSession();

        list = session.createQuery(readDataSQL).list();
        return list;
    }
}
