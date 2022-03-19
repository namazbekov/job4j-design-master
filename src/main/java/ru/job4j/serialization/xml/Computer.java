package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "computer")
public class Computer {
    @XmlAttribute
    private String name;

    public Computer() {
    }

    public Computer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{"
                + "name='" + name + '\''
                + '}';
    }
}
