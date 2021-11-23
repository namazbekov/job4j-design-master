package ru.job4j.hashtable;

import java.util.*;

public class HashTableDemo<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int size = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public boolean put(K key, V value) {
        int realLoadFactor = size / capacity;
        if (realLoadFactor >= LOAD_FACTOR) {
            expand();
        }
            int index = indexFor(hash(key.hashCode()));
            MapEntry<K, V> newEntry = new MapEntry(key, value, null);
            if (table[index] == null) {
                table[index] = newEntry;
                size++;
                modCount++;
                return true;
            }
            return false;

    }

    private int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[2 * capacity];
        List<MapEntry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            findEntryByNext(table[i], list);
            if (list.size() > 0) {
                size = 0;
                capacity = 2 * capacity;
                table = newTable;
                for (MapEntry<K, V> oldEntry : newTable) {
                    if (oldEntry != null) {
                        oldEntry.next = null;
                    }
                    put(oldEntry.getKey(), oldEntry.getValue());
                }
            }
        }
    }
    private void findEntryByNext(MapEntry<K, V> entry, List<MapEntry<K, V>> list) {
        if (entry != null && entry.next != null) {
            list.add(entry);
            findEntryByNext(entry.next, list);
        } else {
            list.add(entry);
        }
    }


    public V get(K key) {
        V value = null;
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> entry = table[index];
        if (entry.getKey().equals(key)) {
            value = entry.getValue();
        }
        return value;
    }


    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> current = table[index];
        if (current.key.equals(key)) {
            table[index] = null;

            return true;
        }
        return false;
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                for (MapEntry<K, V> elements : table) {
                    if (elements != null) {
                        point++;
                        break;
                    }
                }
                return point < size;
            }

            @Override
            public K next() {
               if (!hasNext()) {
                   throw new NoSuchElementException();
               }
               if (currentModCount != modCount) {
                   throw new ConcurrentModificationException();
               }
               return (K) table[point++];
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;
        MapEntry<K, V> next;

        public MapEntry(K key, V value, MapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
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

        public MapEntry<K, V> getNext() {
            return next;
        }

        public void setNext(MapEntry<K, V> next) {
            this.next = next;
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