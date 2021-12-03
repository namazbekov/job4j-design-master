package ru.job4j.myexercises;

public interface Stack<T> {
    void push(T item); // добавить элемент в вершину стека
    T pop();  // взять элемент с вершины стека
    boolean isEmpty();
}
