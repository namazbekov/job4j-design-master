package ru.job4j.user;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Date birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday.getTime();
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, children, birthday);
//    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User user = new User("Dastan", 2, new GregorianCalendar(2021, 3, 23));
        User user1 = new User("Ivan", 3, new GregorianCalendar(2022, 4, 27));
        User user2 = new User("Dania", 1, new GregorianCalendar(2024, 1, 20));
        map.put(user, "first");
        map.put(user1, "second");
        map.put(user2, "third");
        System.out.println(map);

        User user3 = new User("Dastan", 2, new GregorianCalendar(2021, 3, 23));
        map.put(user3, "first");
//        boolean result = map.containsKey(user3);
//        System.out.println("result : " + result);
        System.out.println(user.equals(user3));
        System.out.println(user.hashCode());
        System.out.println(user3.hashCode());
        System.out.println(map);
    }
}
