package org.catplayer.datastruct.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * org.catplayer.datastruct.stack implementation via linked list
 *
 * @param <T>
 * @author catplayer
 * @since 1.0
 */
public class LinkedListStack<T> implements Iterable<T> {

    private Node<T> node;

    private int nodeCount;


    public void push(T item) {
        Node<T> oldNode = node;
        Node<T> newNode = new Node<>();
        newNode.item = item;
        newNode.next = oldNode;
        node = newNode;
        nodeCount++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("the org.catplayer.datastruct.stack is empty!");
        }

        Node<T> targetNode = node;
        node = node.next;
        nodeCount--;
        return targetNode.item;

    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return node.item;
    }

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    public int size() {
        return nodeCount;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator();
    }


    /**
     * node record
     *
     * @param <N>
     */
    private static class Node<N> {

        private N item;

        private Node<N> next;

    }


    private class SimpleIterator implements Iterator<T> {

        private Node<T> currentNode = node;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return Objects.nonNull(currentNode);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            T item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.push("张三");
        stack.push("你好");
        System.out.println("iterator function");
        for (String content : stack
        ) {
            System.out.println(content);
        }
        System.out.println("org.catplayer.datastruct.stack base function");
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());

    }
}

