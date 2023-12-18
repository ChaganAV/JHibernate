import java.sql.SQLException;

public class Program {
    private static final String[] titles = new String[]{"Информатика","Физика","Английский язык","Русский язык","Литература"};
    public static void main(String[] args) {

        SchoolApp schoolApp = new SchoolApp();

        // Создадим базу данных
        schoolApp.createDatabase();
        System.out.println("Создали базу данных");

        // Используем базу данных
        schoolApp.useDatabase();
        System.out.println("Используем базу данных");

        // Создадим таблицы
        schoolApp.createTables();
        System.out.println("Создали таблицы");
    }
}
