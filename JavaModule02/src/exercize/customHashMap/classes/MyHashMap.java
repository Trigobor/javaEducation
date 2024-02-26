package exercize.customHashMap.classes;

import java.io.Serializable;
import java.util.*;

public class MyHashMap <K,V> implements Map<K,V> {

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K, V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }
    static final float LOAD_FACTOR = 0.75f;
    final float loadFactor;
    private int size;
    private int capacity;
    private int threshold;

    private Node[] arr;

    public MyHashMap(int initialCapacity, float lFactor) {
        if ((initialCapacity) < 0 || (initialCapacity > 16))
            initialCapacity = 16;
        if (lFactor <= 0 || Float.isNaN(lFactor))
            lFactor = LOAD_FACTOR;
        this.loadFactor = lFactor;
        this.capacity = initialCapacity;
        this.threshold = trashHoldLimit(initialCapacity);
        this.arr = new Node[initialCapacity];
    }

    public MyHashMap(int initialCapacity) {
        if ((initialCapacity) < 0 || (initialCapacity > 16))
            initialCapacity = 16;
        this.loadFactor = LOAD_FACTOR;
        this.capacity = initialCapacity;
        this.threshold = trashHoldLimit(initialCapacity);
        this.arr = new Node[initialCapacity];
    }

    public MyHashMap(float lFactor) {
        if (lFactor <= 0 || Float.isNaN(lFactor))
            lFactor = LOAD_FACTOR;
        this.loadFactor = lFactor;
        this.capacity = 16;
        this.threshold = trashHoldLimit(16);
        this.arr = new Node[16];
    }

    public MyHashMap() {
        this.loadFactor = LOAD_FACTOR;
        this.threshold = trashHoldLimit(16);
        this.arr = new Node[16];
        this.capacity = 16;
    }

    private int trashHoldLimit(int cap) {
        Float rez = (cap * this.loadFactor);
        return rez.intValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null)
            return false;
        int hash = key.hashCode() % this.capacity;
        Node<K, V> kvNode = this.arr[hash];
        if(kvNode == null)
            return false;
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null)
            return false;
        for (int i = 0; i < capacity; i++) {
            Node<K, V> kvNode = this.arr[i];
            if (kvNode == null)
                return false;
            while (kvNode.next != null) {
                kvNode = kvNode.next;
                if (kvNode.getValue() == value)
                    return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null)
            return null;
        int hash = key.hashCode() % this.capacity;
        Node<K, V> kvNode = this.arr[hash];
        if(kvNode == null)
            return null;
        while (kvNode != null) {
            if(kvNode.key == key) {
                return kvNode.getValue();
            }
            kvNode = kvNode.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if(this.size == this.threshold)
        {
            Node[] arr = reCap();
            this.capacity = this.capacity * 2;
            this.threshold = trashHoldLimit(this.capacity);
            this.arr = arr;
        }
        return putToTheArr(this.arr, this.capacity, key, value);
    }

    private V putToTheArr(Node[] arr, int cap, K key, V value){
        if (key == null)
            return null;
        int hash = key.hashCode() % cap;
        Node<K, V> kvNode = arr[hash];
        size++;
        if(kvNode == null) {
            arr[hash] = new Node<>(hash, key, value, null);
            return null;
        }
        while (kvNode != null){
            if(kvNode.key == key) {
                size--;
                return kvNode.setValue(value);
            }
            if (kvNode.next == null) {
                kvNode.next = new Node<>(hash, key, value, null);
                return kvNode.value;
            }
            kvNode = kvNode.next;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        if (key == null)
            return null;
        int hash = key.hashCode() % this.capacity;
        Node<K, V> kvNode = this.arr[hash];
        Node<K, V> prNode = this.arr[hash];
        if(kvNode == null || kvNode.key != key && kvNode.next == null) {
            return null;
        }else if(kvNode.key == key){
            size--;
            this.arr[hash] = kvNode.next;
            return prNode.getValue();
        }
        while (kvNode != null){
            if(kvNode.next != null && kvNode.next.key == key) {
                size--;
                kvNode = kvNode.next;
                prNode.next = kvNode.next;
                return kvNode.getValue();
            }
            kvNode = kvNode.next;
            prNode = prNode.next;
        }
        return null;
    }

    private Node[] reCap(){
        int tableIter = 0;
        int sizeIter = 0;
        int newCap = this.capacity * 2;
        Node[] rez = new Node[newCap];
        while (tableIter < this.arr.length && sizeIter < this.size){
            Node<K, V> node;
            if(this.arr[tableIter] != null) {
                node = this.arr[tableIter];
                while (node != null){
                    this.putToTheArr(rez, newCap, node.key, node.value);
                    node = node.next;
                    sizeIter++;
                }
            }
            tableIter++;
        }
        return rez;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
