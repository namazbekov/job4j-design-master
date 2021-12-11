package ru.job4j.myexercises;

public interface Stack<T> {
    void push(T item);
    T pop();
    boolean isEmpty();
}
