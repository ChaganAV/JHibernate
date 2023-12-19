package task1;

import models.Courses;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SchoolApp {
    private String url = "jdbc:mysql://localhost:3307/";
    private String user = "root";
    private String password = "password";
    private Connection connection;

    public SchoolApp(){

    }

    // region CRUD

    /**
     * Добавление данных в таблицу students
     * @param connection Соединение с БД
     * @param student таблица
     */
    public void insertStudent(Connection connection,Student student){
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(insertDataSQL)){
            statement.setString(1, student.getName());
            statement.setInt(2,student.getAge());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Добавление данных в таблицу courses
     * @param connection Соединение с БД
     * @param courses таблица courses
     */
    public void insertCourses(Connection connection,Courses courses){
        String insertDataSQL = "INSERT INTO courses (title, duration) VALUES (?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(insertDataSQL)){
            statement.setString(1, courses.getTitle());
            statement.setInt(2,courses.getDuration());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateStudents(Connection connection,Student student){
        String updateDataSQL = "UPDATE students SET name=?, age=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(updateDataSQL)){
            statement.setString(1,student.getName());
            statement.setInt(2,student.getAge());
            statement.setInt(3,student.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateCourses(Connection connection,Courses courses){
        String updateDataSQL = "UPDATE students SET title=?, duration=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(updateDataSQL)){
            statement.setString(1, courses.getTitle());
            statement.setInt(2,courses.getDuration());
            statement.setInt(3,courses.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Удаление данных из таблицы Courses
     * @param connection
     * @param id
     */
    public void deleteCoursesById(Connection connection, int id){
        String deleteCourses = "DELETE FROM courses WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(deleteCourses)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudentsById(Connection connection, int id){
        String deleteData = "DELETE FROM students WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(deleteData)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * Чтение данных из таблицы students
     * @param connection
     * @return
     */
    public Collection<Student> readStudent(Connection connection){
        List<Student> list = new ArrayList<>();
        String readDataSQL = "SELECT * FROM students;";
        try(PreparedStatement statement = connection.prepareStatement(readDataSQL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                list.add(new Student(id,name,age));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Чтение данных из таблицы Courses
     * @param connection
     * @return
     */
    public Collection<Courses> readCourses(Connection connection){
        List<Courses> list = new ArrayList<>();
        String readDataSQL = "SELECT * FROM courses;";
        try(PreparedStatement statement = connection.prepareStatement(readDataSQL)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                list.add(new Courses(id,title,duration));
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    // endregion

    // region вспомогательные методы
    public void createDatabase(Connection connection){
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)){
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void useDatabase(Connection connection){
        String useDatabaseSQL = "USE schoolDB";
        try(PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)){
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void createTables(Connection connection){
        useDatabase(connection);
        String createTableCourses = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT);";
        try(PreparedStatement statement = connection.prepareStatement(createTableCourses)){
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

        String createTableStudents = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT);";
        try(PreparedStatement statement = connection.prepareStatement(createTableStudents)){
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // endregion

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
