package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private boolean online;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    private Computer computer;
    @XmlElementWrapper(name = "locations")
    @XmlElement(name = "location")
    private String[] location;

    public User() {
    }

    public User(boolean online, String name, int age, Computer computer, String... location) {
        this.online = online;
        this.name = name;
        this.age = age;
        this.computer = computer;
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{"
                + "online=" + online
                + ", name='" + name + '\''
                + ", age=" + age
                + ", computer=" + computer
                + ", location=" + Arrays.toString(location)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final User user =
                new User(false, "Dany", 21,
                        new Computer("lenova-17365"), "Bishkek", "Moscow");
        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String txt = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            txt = writer.getBuffer().toString();
            System.out.println(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(txt)) {
            User user1 = (User) unmarshaller.unmarshal(reader);
            System.out.println(user1);
        }
    }
}
