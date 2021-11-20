package ru.job4j.hashtable;

import org.junit.Test;

import static org.hamcrest.Matchers.describedAs;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class HashTableDemoTest {

    @Test
    public <K, V> void whenAdd() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12345);
        assertThat(table.get("Dastan"), is(12345));
    }
    @Test
    public void whenAdd2() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12345);
        table.put("Ivan", 15151);
        assertThat(table.get("Dastan"), is(12345));
        assertThat(table.get("Ivan"), is(15151));
    }
    @Test
    public void whenAdd2AndRemove() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12345);
        table.put("Ivan", 15151);
        assertThat(table.remove("Ivan"), is(true));
    }
}