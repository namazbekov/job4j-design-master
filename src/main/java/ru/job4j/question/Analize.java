package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleteCount = 0;
        int changeCount = 0;
        int addCount = 0;

        HashMap<Integer, String> prev = new HashMap();
        for (User index : previous) {
            prev.put(index.getId(), index.getName());
        }

        for (User index : current) {
            if (!prev.containsKey(index.getId())) {
                addCount++;
            } else if (!prev.get(index.getId()).equals(index.getName())) {
                changeCount++;
            }
        }

        deleteCount = addCount + prev.size() - current.size();

        Info info = new Info(addCount, changeCount, deleteCount);
        return info;
    }
}