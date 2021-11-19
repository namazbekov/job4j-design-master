package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;
    float threshold = capacity * LOAD_FACTOR;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
      if (key == null) {
          putForNullKey(value);
          count++;
          modCount++;
          return true;
      } else {
          int hash = hash(key.hashCode());
          int index = indexFor(hash);
          MapEntry<K, V> newEntry = new MapEntry<>(key, value, null);
          if (table[index] == null) {
              table[index] = newEntry;
              count++;
              modCount++;
          } else {
              MapEntry<K, V> previous = null;
              MapEntry<K, V> current = table[index];
              while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        newEntry.next = current.next;
                        table[index] = newEntry;
                        return true;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
              }
              previous.next = newEntry;
          }
      }
      return false;
    }

    private int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }
    private int hashIndex(K key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        return index;
    }
    private boolean putForNullKey(V value) {
       MapEntry<K, V> element;
       element = table[0];
       if (element != null && element.getKey() == null) {
                element.getValue();
                element.setValue(value);
                return true;
           } else {
               element.setKey(null);
               element.setValue(value);
               table[0] = element;
               return true;
           }
    }

    private void transfer() {
        MapEntry<K, V>[] oldEntry = table;
        int count = 0;
        int newCapacity = capacity * 2;
        table = new MapEntry[newCapacity];
        for (MapEntry<K, V> entry : oldEntry) {
            if (entry != null) {
                put(entry.key, entry.value);
                count++;
                modCount++;
            }
        }
    }

    private void expand(int newCapacity) {
        if (table.length == capacity) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        transfer();
        table = newTable;
        threshold = newCapacity * LOAD_FACTOR;
    }

    @Override
    public V get(K key) {
        int index = hashIndex(key);
        if (table[index] == null) {
            return null;
        } else {
            MapEntry<K, V> find = table[index];
            while (find != null) {
                if (find.key.equals(key)) {
                    return find.value;
                }
                find = find.next;
            }
            return null;
        }
    }

    @Override
    public boolean remove(K key) {
        int index = hashIndex(key);
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
        }
        return false;
    }

    @Override
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

        public void setKey(K key) {
            this.key = key;
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
        return "SimpleMap{"
                + "capacity=" + capacity
                + ", count=" + count
                + ", modCount=" + modCount
                + ", table=" + Arrays.toString(table)
                + '}';
    }
}