package ru.job4j.myexercises;

public interface  Queue<T> {
    void add(T item); // добавить элемент в конец очереди
    T remove();       // удалить элемент из начала очереди
    boolean isEmpty();
}
