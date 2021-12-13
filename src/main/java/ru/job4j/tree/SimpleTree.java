package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;


public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> element = findBy(parent);
        if (findBy(child).isEmpty() && element.isPresent()) {
            element.get().children.add(new Node<>(child));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(final E value) {
       return findByPredicateQueue(x -> x.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        boolean result = false;
        Optional<Node<E>> element = findByPredicateQueue(x -> x.children.size() > 2);
         if (element.isEmpty()) {
            result = true;
        }
        return result;
    }
    private Optional<Node<E>> findByPredicateQueue(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
