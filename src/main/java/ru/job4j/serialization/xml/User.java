package ru.job4j.serialization.xml;

public class User {
    final boolean online;
    final String name;
    final int age;
    final Computer computer;
    final String[] location;

    public User(boolean online, String name, int age, Computer computer, String[] location) {
        this.online = online;
        this.name = name;
        this.age = age;
        this.computer = computer;
        this.location = location;
    }
}
