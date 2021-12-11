package ru.job4j.myexercises;

public interface  Queue<T> {
    void add(T item);
    T remove();
    boolean isEmpty();
}
