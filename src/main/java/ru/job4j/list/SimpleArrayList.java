package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public SimpleArrayList() {

    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }
    public void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        T result = get(index);
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        modCount++;
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}