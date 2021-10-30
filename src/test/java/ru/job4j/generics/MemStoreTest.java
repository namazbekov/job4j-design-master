package ru.job4j.generics;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MemStoreTest {

    @Test
    public void whenFindById() {
        Map<String, Integer> mem = new HashMap<>();
        mem.put("one", 1);
        mem.put("two", 2);
        mem.put("three", 3);
        assertThat(mem.get("one"), is(1));

    }

    @Test
    public void whenDeleteById() {
        Map<String, Integer> mem = new HashMap<>();
        mem.put("one", 1);
        mem.put("two", 2);
        mem.put("three", 3);
        mem.remove("one");
        Map<String, Integer> expected = new HashMap<>();
        expected.put("two", 2);
        expected.put("three", 3);
        assertThat(mem, is(expected));
    }

    @Test
    public void whenReplaceById() {
        Map<String, Integer> mem = new HashMap<>();
        mem.put("one", 1);
        mem.put("two", 2);
        mem.put("three", 3);
        mem.replace("three", 4);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("one", 1);
        expected.put("two", 2);
        expected.put("three", 4);
        assertThat(mem, is(expected));

    }

    @Test
    public void whenAdd() {
        Map<String, Integer> mem = new HashMap<>();
        mem.put("one", 1);
        mem.put("two", 2);
        mem.put("three", 3);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("one", 1);
        expected.put("two", 2);
        expected.put("three", 3);
        assertThat(mem, is(expected));
    }
}