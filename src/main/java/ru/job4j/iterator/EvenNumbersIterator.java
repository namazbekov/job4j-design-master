package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }



    @Override
    public boolean hasNext() {
        boolean result = false;
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    public static void main(String[] args) {

        EvenNumbersIterator it = new  EvenNumbersIterator(new int[] {4, 2, 1, 1});
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));

    }

}