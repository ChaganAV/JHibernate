package task2;

import models.CourserRepo;
import models.Courses;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.Configuration;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        System.out.println("=== Выведем список ===");
        CourserRepo courserRepo = new CourserRepo();
        int count = 0;
        List<Courses> list = (List<Courses>) courserRepo.getAll();
        for (Courses cours: list ) {
            System.out.println(cours);
            count++;
        }
        System.out.println("=== Добавим курс ===");
        Courses courses = new Courses("Разработчик Python",3);
        courserRepo.add(courses);
        System.out.println("Добавили "+ courses);

        System.out.println("=== Изменим курс ===");
        courses = courserRepo.getById(2);
        System.out.println(courses);
        String coursesName = courses.getTitle();
        courses.setTitle(String.format("%s %s",coursesName,"для начинающих"));
        courses.setDuration(5);
        courserRepo.update(courses);
        courses = courserRepo.getById(2);
        System.out.println(courses);
        System.out.println("=== Удалим последний курс ===");
        courses = courserRepo.getById(count);
        courserRepo.delete(courses);
        System.out.println("=== Удалили ===");
        list = (List<Courses>) courserRepo.getAll();
        for (Courses cours: list ) {
            System.out.println(cours);
        }
    }
}
