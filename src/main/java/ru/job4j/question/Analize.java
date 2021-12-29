package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleteCount = 0;
        int changeCount = 0;
        int addCount = 0;
        Set<User> allUsers = new HashSet<>(current);
        allUsers.removeAll(previous);
        for (User curr : current) {
            for (User prev: previous) {
                if (curr.getId() == prev.getId() && !Objects.equals(curr.getName(), prev.getName())) {
                    changeCount++;
                }
            }
        }
        if (!previous.containsAll(allUsers) && current.containsAll(allUsers)) {
            addCount++;
        }
        if (previous.size() > current.size()) {
            deleteCount++;
        }
        Info info = new Info(addCount, changeCount, deleteCount);
        return info;
    }
}