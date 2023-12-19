package task1;

import models.Courses;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Program {
    private static Random random = new Random();

    public static void main(String[] args) {

        SchoolApp schoolApp = new SchoolApp();
        try {
            Connection connection = DriverManager.getConnection(schoolApp.getUrl(),schoolApp.getUser(), schoolApp.getPassword());
            // Создадим базу данных
            schoolApp.createDatabase(connection);
            System.out.println("Создали базу данных");

            // Используем базу данных
            schoolApp.useDatabase(connection);
            System.out.println("Используем базу данных");

            // Создадим таблицы
            schoolApp.createTables(connection);
            System.out.println("Создали таблицы");

//            int count = random.nextInt(5,11);
//            for (int i = 0; i < count; i++) {
//                schoolApp.insertStudent(connection,Student.create());
//            }
//            System.out.println("Создали студентов");

//            List<Courses> courses = Courses.create();
//            for (Courses course: courses) {
//                schoolApp.insertCourses(connection,course);
//            }
//            System.out.println("Создали курсы");


            Collection<Student> students = schoolApp.readStudent(connection);
            for(Student student: students){
                System.out.println(student);
            }
            System.out.println("Вывели студентов");

            // Удаление студентов
//            for(Student student: students){
//                schoolApp.deleteStudentsById(connection,student.getId());
//            }
//            System.out.println("Удалим студентов");

            Collection<Courses> coursesRead = schoolApp.readCourses(connection);
            for (Courses cours: coursesRead) {
                System.out.println(cours);
            }
            System.out.println("Вывели курсы");

            // Удаление курсов
//            for (Courses cours: coursesRead) {
//                schoolApp.deleteCoursesById(connection, cours.getId());
//            }
//            System.out.println("Удалили курсы");


            // Закрытие соединения
            connection.close();
            System.out.println("Закрыли соединение");



        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
