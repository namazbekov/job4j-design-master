package ru.job4j.serialization.json;

import org.json.JSONObject;

import java.util.Arrays;

public class MyPerson {
    final String name;
    final int age;
    final boolean sex;
    final Relative relative;
    final String[] whereHeLives;

    public MyPerson(String name, int age, boolean sex, Relative relative, String... whereHeLives) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.relative = relative;
        this.whereHeLives = whereHeLives;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }

    public Relative getRelative() {
        return relative;
    }

    public String[] getWhereHeLives() {
        return whereHeLives;
    }

    @Override
    public String toString() {
        return "MyPerson{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + ", relative=" + relative
                + ", whereHeLives=" + Arrays.toString(whereHeLives)
                + '}';
    }

    public static void main(String[] args) {
        final MyPerson person =
                new MyPerson("Nikita", 32, true,
                        new Relative("Vitalik", "Nastia"), "Moscow", "Piter");
        JSONObject json = new JSONObject();
        json.put("name", person.name);
        json.put("age", person.age);
        json.put("sex", person.isSex());
        json.put("relative", person.relative);
        json.put("whereHeLives", person.whereHeLives);
        System.out.println(json);

        System.out.println(new JSONObject(person));
    }
}
