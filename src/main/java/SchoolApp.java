import models.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SchoolApp {
    private String url = "jdbc:mysql://localhost:3307/";
    private String user = "root";
    private String password = "password";
    private Connection connection;
    public SchoolApp(){

    }

    // region CRUD
    public void insertStudent(Student student){
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
        try(PreparedStatement statement = this.connection.prepareStatement(insertDataSQL)){
            statement.setString(1, student.getName());
            statement.setInt(2,student.getAge());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    // endregion

    // region вспомогательные методы
    public void createDatabase(){
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB;";
        this.connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)){
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void useDatabase(){
        this.connection = getConnection();
        String useDatabaseSQL = "USE schoolDB";
        try(PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)){
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void createTables(){
        useDatabase();
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

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
