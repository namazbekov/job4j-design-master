package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class MySerialization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private final int age;
    File tempFile = Files.createTempFile(null, null).toFile();

    public MySerialization(String name, int age) throws IOException {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "name='"
                + name
                + '\''
                + ", age="
                + age;
    }
    public void serialization() {
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
           oos.writeObject(new MySerialization(name, age));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deserialization() {
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
           final MySerialization deserialization = (MySerialization) ois.readObject();
            System.out.println(deserialization);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        MySerialization mySerialization = new MySerialization("Pavel", 21);
        mySerialization.serialization();
        mySerialization.deserialization();
    }
}
