package ru.job4j.myexercises;

import java.util.ArrayList;

public class SimpleStack<T> implements Stack<T> {
    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void push(T item) {
        list.add(0, item);
    }

    @Override
    public T pop() {
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        SimpleStack<Integer> stack = new SimpleStack<>();
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            stack.push(i);
        }
        System.out.println("\n");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
