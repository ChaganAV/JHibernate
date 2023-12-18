import models.Courses;
import models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolApp {
    private String url = "jdbc:mysql://localhost:3307/";
    private String user = "root";
    private String password = "password";
    private Connection connection;

    public SchoolApp(){

    }

    // region CRUD
    public void insertStudent(Connection connection,Student student){
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
        try(PreparedStatement statement = this.connection.prepareStatement(insertDataSQL)){
            statement.setString(1, student.getName());
            statement.setInt(2,student.getAge());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insertCourses(Connection connection,Courses courses){
        String insertDataSQL = "INSERT INTO courses (title, duration) VALUES (?, ?);";
        try(PreparedStatement statement = this.connection.prepareStatement(insertDataSQL)){
            statement.setString(1, courses.getTitle());
            statement.setInt(2,courses.getDuration());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
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
