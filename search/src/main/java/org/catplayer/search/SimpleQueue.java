package org.catplayer.search;

import java.util.Objects;

/**
 * a simple queue based on linked list
 * <p>
 * just used for FIFO
 *
 * @param <T>
 * @author catplayer
 * @since 1.0
 */
public class SimpleQueue<T> {


    private Node<T> head;
    private Node<T> tail;

    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if (Objects.isNull(head)) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

    }

    public T dequeue() {
        if (Objects.isNull(head)) {
//            System.out.println("no item");
            return null;
        }
        T item = head.item;
        //only one node
        if (Objects.isNull(head.next)) {
            head = tail = null;
        } else {
            head = head.next;
        }
        return item;

    }

    public boolean isEmpty() {
        return head == null;
    }


    /**
     * node item keep the node self item, next keep the next in node
     *
     * @param <K>
     */
    private static class Node<K> {
        private final K item;
        private Node<K> next;

        public Node(K item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.enqueue(1);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        queue.enqueue(7);
        System.out.println(queue.dequeue());
    }

}
