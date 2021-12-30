package ru.job4j.myexercises;


import ru.job4j.question.User;

import java.util.HashMap;
import java.util.List;

public class MyHashMap {
    public HashMap<String, Integer> isChange(List<User> previous, List<User> current) {
        int deleteCount = 0;
        int changeCount = 0;
        int beforeSize = previous.size();

        HashMap<Integer, String> allUsers = new HashMap<>();

        for (User index : current) {
            allUsers.put(index.getId(), index.getName());
        }

        for (User index : previous) {
            if (!allUsers.containsKey(index.getId())) {
                deleteCount++;
            } else if (!allUsers.get(index.getId()).equals(index.getName())) {
                changeCount++;
            }
            allUsers.put(index.getId(), index.getName());
        }

        int newListSize = allUsers.size();
        HashMap<String, Integer> result = new HashMap<>();


        result.put("Amount new add: ", newListSize - beforeSize);
        result.put("Amount new change: ", changeCount);
        result.put("Amount new delete: ", deleteCount);

        return result;
    }
}