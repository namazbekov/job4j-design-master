package ru.job4j.it;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return data[point++];
    }

    @Override
    public String toString() {
        return "Array : " + data[point];
    }


}