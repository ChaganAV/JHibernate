package models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int duration;
    private static final Random random = new Random();
    private static String[] titles = new String[]{"Информатика","Психология","Английский язык","Разработчик С#","Разработчик Java"};

    // region Constructors
    public Courses(){

    }

    public Courses(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Courses(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
    // endregion

    // region вспомогательные методы
    public static List<Courses> create(){
        List<Courses> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(new Courses(titles[i], random.nextInt(1,3)));
        }
        return list;
    }
    // endregion
    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    // region getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    // endregion
}
