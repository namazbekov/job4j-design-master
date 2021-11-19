package ru.job4j.map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class SimpleMapTest {

    @Test
    public void whenPutElement() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(525, "Dastan");
        map.put(800, "Ivan");
        map.put(1015, "Oleg");
        SimpleMap<Integer, String> expected = new SimpleMap<>();
        expected.put(525, "Dastan");
        expected.put(800, "Ivan");
        expected.put(1015, "Oleg");
        assertThat(map, is(expected));
    }
}