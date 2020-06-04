package search;

import java.util.Objects;

public class SimpleQueue<T> {


    private Node<T> first;
    private Node<T> end;

    public void add(T item) {
        Node<T> node = new Node<>(item);
        if (Objects.isNull(first)) {
            first = node;
            end = node;
        } else {
            end.next = node;
        }

    }

    public T get() {
        if (Objects.isNull(first)) {
            System.out.println("no item");
            return null;
        }
        T item = first.item;
        if (Objects.isNull(first.next)) {
            first = null;
            end = null;
        } else {
            first = first.next;
        }
        return item;

    }


    /**
     * node item keep the node self item, next keep the next in node
     *
     * @param <T>
     */
    private class Node<T> {
        private final T item;
        private Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.add(1);
        queue.add(5);
        System.out.println(queue.get());
        queue.add(7);
        System.out.println(queue.get());
    }

}
