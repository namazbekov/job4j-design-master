package ru.job4j.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import ru.job4j.myexercises.HashTableDemo;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleMapTest {

    @Test
    public <V> void whenAdd() {
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
        iterator.next();
        iterator.next();
        iterator.next();
    }
    @Test
    public void whenAddAndRunIterator() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 15);
        Iterator<String> iterator = table.iterator();
        System.out.println(iterator.hasNext());
    }

    @Test
    public void whenSimpleAdd() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put("Sasha", 600);
        Iterator<String> it = table.iterator();
        System.out.println(it.hasNext());
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        Iterator<String> it = map.iterator();
        map.put("two", 2);
        it.next();
    }
    @Test
    public void whenSimpleAddEightNine() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put("Sasha", 600);
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put("Sasha", 600);
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put("Sasha", 600);

    }
    @Test
    public void whenToIteratorEmpty() {
        HashTableDemo<String, Integer> table = new HashTableDemo<>();
        table.put("Dastan", 12);
        table.put("Ivan", 3);
        table.put(" ", 5);
        Iterator<String> it = table.iterator();
        it.next();
        System.out.println(it.hasNext());
    }
}