import models.Courses;
import models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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

            int count = random.nextInt(5,11);
            for (int i = 0; i < count; i++) {
                schoolApp.insertStudent(connection,Student.create());
            }
            System.out.println("Создали студентов");

            List<Courses> courses = Courses.create();
            for (Courses course: courses) {
                schoolApp.insertCourses(connection,course);
            }
            System.out.println("Создали курсы");

            // Закрытие соединения
            connection.close();
            System.out.println("Закрыли соединение");
        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
