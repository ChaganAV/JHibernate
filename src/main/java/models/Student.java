package models;

import java.util.Random;

public class Student {
    private int id;
    private String name;
    private int age;
    private static final String[] names = new String[] {"Федор", "Григорий", "Мария", "Боб", "Борис", "Енокентий", "Вальдемар", "Елена"};
    private static final Random random = new Random();

    // region constructors
    public Student(){

    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    // endregion


    public static Student create(){
        return new Student(names[random.nextInt(names.length)], random.nextInt(20,35));
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // region getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    // endregion
}
