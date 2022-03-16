package ru.job4j.serialization.json;

import com.google.gson.Gson;

public class MyMain {
    public static void main(String[] args) {
       final MyPerson myPerson = new MyPerson("Piter", 18, false, new Relative("Tom", "Katty"),
                new String[] {"Moscow", "Paris", "Bishkek"});
       final Gson gson = new Gson();
       System.out.println(gson.toJson(myPerson));

        String jsonText = "{"
                + "\"name\":Piter,"
                + "\"age\":15,"
                + "\"sex\":false,"
                + "\"relative\":"
                + "{"
                + "\"brothers\":\"Tom\","
                + "\"sisters\":\"Katty\""
                + "},"
                + "\"whereHeLives\":"
                + "[\"Moscow\",\"Paris\",\"Bishkek\"]}";
        final MyPerson myPerson1 = gson.fromJson(jsonText, MyPerson.class);
        System.out.println(myPerson1);

    }
}
