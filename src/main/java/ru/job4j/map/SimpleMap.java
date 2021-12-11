package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int size = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public boolean put(K key, V value) {
        boolean result = false;
        if (size >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> newEntry = new MapEntry(key, value, null);
        if (table[index] == null) {
            table[index] = newEntry;
            size++;
            modCount++;
            result = true;
        }
        return result;

    }

    private int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity = capacity * 2;
        size = 0;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> elements : oldTable) {
            if (elements != null) {
                put(elements.key, elements.value);
                size++;
            }
        }
    }


    public V get(K key) {
        V value = null;
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> entry = table[index];
        if (table[index] != null) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
            }
        }
        return value;
    }


    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> current = table[index];
        if (table[index] != null) {
            if (current.key.equals(key)) {
                table[index] = null;
                result = true;
            }
        }
        return result;
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length) {
                    if (table[point] != null) {
                        result = true;
                        break;
                    }
                    point++;
                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[point++];
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value, MapEntry<K, V> next) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "HashTableDemo{"
                +
                "capacity=" + capacity
                +
                ", size=" + size
                +
                ", modCount=" + modCount
                +
                ", table=" + Arrays.toString(table)
                +
                '}';
    }
}