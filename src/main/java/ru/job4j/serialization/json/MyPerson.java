package ru.job4j.serialization.json;

import java.util.Arrays;

public class MyPerson {
    final String name;
    final int age;
    final boolean sex;
    final Relative relative;
    final String[] whereHeLives;

    public MyPerson(String name, int age, boolean sex, Relative relative, String[] whereHeLives) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.relative = relative;
        this.whereHeLives = whereHeLives;
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
}
