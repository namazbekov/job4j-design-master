package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, s -> s == 0);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 2, 2, 2));
        ListUtils.replaceIf(input, s -> s % 2 == 0, 0);
        assertThat(input, is(Arrays.asList(0, 0, 0, 0)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> input1 = new ArrayList<>(Arrays.asList(0, 1));
        ListUtils.removeAll(input, input1);
        assertThat(input, is(Arrays.asList(2, 3, 4)));
    }



}