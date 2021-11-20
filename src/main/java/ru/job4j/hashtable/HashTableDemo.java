package ru.job4j.hashtable;

import java.util.*;

public class HashTableDemo<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int size = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public void put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> newEntry = new MapEntry(key, value, null);
        if (table[index] == null) {
            table[index] = newEntry;
            size++;
            modCount++;
        } else {
            MapEntry<K, V> previousNode = null;
            MapEntry<K, V> currentNode = table[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null) {
                previousNode.setNext(newEntry);
            }
        }
    }

    private int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[2 * capacity];
        rehash(newTable);
    }
    private void rehash(MapEntry<K, V>[] newTable) {
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
                for (MapEntry<K, V> oldEntry : table) {
                    if (oldEntry != null) {
                        oldEntry.next = null;
                    }
                    assert oldEntry != null;
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
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }


    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            return false;
        } else {
            MapEntry<K, V> previous = null;
            MapEntry<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        table[index] = table[index].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
    }


    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int currentModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < table.length;
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