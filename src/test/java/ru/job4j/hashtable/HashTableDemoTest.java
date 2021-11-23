package ru.job4j.hashtable;

import org.junit.Test;
import ru.job4j.map.Map;
import ru.job4j.map.SimpleMap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

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
    @Test
    public void whenAddElementsAndIterator() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put("Sasha", 600);
        Iterator<String> iterator = table.iterator();
        assertThat(iterator.hasNext(), is(true));
    }
    @Test
    public void whenAddAndRunIterator() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 15);
        Iterator<String> iterator = table.iterator();
        iterator.next();
        iterator.hasNext();
    }

    @Test
    public void whenSimpleAdd() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put("Sasha", 600);
        Iterator<String> it = table.iterator();
        it.hasNext();
        it.next();
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        Iterator<String> it = map.iterator();
        map.put("two", 2);
        it.next();
    }
}