package com.wx.demo.lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangxing
 * @date 2021/4/15 16:34
 */
public class LRUDemo {

    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<>(3);

        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        lru.put(2, "B");
        System.out.println(lru.keySet());
        lru.put(4, "D");
        System.out.println(lru.keySet());

        System.out.println("========================");

        LRU2 lru2 = new LRU2(3);
        lru2.put(1, "A");
        lru2.put(2, "B");
        lru2.put(3, "C");
        lru2.put(2, "B");
        System.out.println(lru2.map.keySet());
        lru2.put(4, "D");
        System.out.println(lru2.map.keySet());

    }
}

/**
 * 继承 LinkedHashMap 版本
 *
 * @param <K>
 * @param <V>
 */
class LRU<K, V> extends LinkedHashMap<K, V> {

    // 缓存坑位
    private final int capacity;

    public LRU(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }
}

/**
 * 双向链表 + HashMap 版本
 */
class LRU2 {

    private final int capacity;
    protected Map<Integer, Node<Integer, String>> map;
    protected DoubleLinkedList<Integer, String> doubleLinkedList;

    public LRU2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(16);
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public String get(int key) {
        if (!map.containsKey(key)) {
            return "hello world";
        }
        Node<Integer, String> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    public void put(int key, String value) {
        if (map.containsKey(key)) {
            Node<Integer, String> node = map.get(key);
            node.value = value;
            map.put(key, node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            if (map.size() == capacity) {
                // 容量满了先移除尾节点
                Node<Integer, String> lastNode = doubleLinkedList.getLastNode();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            // 新增
            Node<Integer, String> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    /**
     * Node节点作为数据载体
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    /**
     * 构建一个虚拟的双线链表，里面存放 Node节点
     *
     * @param <K>
     * @param <V>
     */
    static class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 添加到头
         *
         * @param node 节点
         */
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        /**
         * 删除节点
         *
         * @param node 节点
         */
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        /**
         * 获取尾节点
         *
         * @return 尾节点
         */
        public Node<K, V> getLastNode() {
            return tail.prev;
        }

    }

}