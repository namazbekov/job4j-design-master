package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (mem.containsKey(id)) {
            mem.replace(id, model);
            result = true;
        }
       return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (mem.containsKey(id)) {
            mem.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public User findById(String id) {
        return (User) mem.get(id);
    }

    @Override
    public String toString() {
        return "MemStore{"
                +
                "mem=" + mem
                +
                '}';
    }
}