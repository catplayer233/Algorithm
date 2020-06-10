package org.catplayer.sort;

import org.catplayer.datastruct.queue.MaxPriorityQueue;

import java.util.Comparator;
import java.util.Objects;

/**
 * max priority queue implemented by binary-heap data struct
 *
 * @param <Key> stored key type
 * @author catplayer
 * @since 1.0
 */
public class HeapMaxPriorityQueue<Key extends Comparable<Key>> implements MaxPriorityQueue<Key> {

    private Key[] heap;

    private int size;

    private Comparator<Key> comparator;

    private static final int DEFAULT_SIZE = 2 << 8;

    public HeapMaxPriorityQueue() {
        this(DEFAULT_SIZE, null);
    }

    public HeapMaxPriorityQueue(Comparator<Key> comparator) {
        this(DEFAULT_SIZE, comparator);
    }


    public HeapMaxPriorityQueue(int initialSize) {
        this(initialSize, null);

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HeapMaxPriorityQueue(Comparable[] heap) {
        this.size = heap.length;
        this.heap = ((Key[]) new Comparable[heap.length + 1]);
        for (int i = 0; i < heap.length; i++) {
            this.heap[i + 1] = ((Key) heap[i]);
        }
        //get the max priority heap
        for (int i = size / 2; i >= 1; i--) {
            sink(i);
        }

    }

    @SuppressWarnings("unchecked")
    public HeapMaxPriorityQueue(int initialSize, Comparator<Key> comparator) {
        this.comparator = comparator;
        //the heap[0] not used
        this.heap = ((Key[]) new Comparable[initialSize + 1]);
    }

    /**
     * sort the queue
     */
    @SuppressWarnings("rawtypes")
    public Comparable[] doSort() {
        Comparable[] comparable = new Comparable[size];

        while (size >= 1) {
            comparable[size - 1] = delMax();
        }

        return comparable;


    }

    /**
     * compare 2 keys via specific heap array index
     *
     * @param heapIndex        fist index
     * @param anotherHeapIndex second index
     * @return first less return true otherwise return false
     */
    private boolean less(int heapIndex, int anotherHeapIndex) {

        assertIndex(heapIndex, anotherHeapIndex);
        Key key = heap[heapIndex];
        Key anotherKey = heap[anotherHeapIndex];
        if (Objects.nonNull(comparator)) {
            return comparator.compare(key, anotherKey) < 0;
        } else {
            //natural order
            return key.compareTo(anotherKey) < 0;
        }
    }

    /**
     * exchange two keys
     *
     * @param heapIndex    index
     * @param anotherIndex another index
     */
    private void exchange(int heapIndex, int anotherIndex) {
        assertIndex(heapIndex, anotherIndex);
        Key key = heap[heapIndex];
        heap[heapIndex] = heap[anotherIndex];
        heap[anotherIndex] = key;
    }

    /**
     * swim the index key util the key's parent greater or equal the key or become the root
     *
     * @param heapIndex target index
     */
    private void swim(int heapIndex) {
        assertIndex(heapIndex);
        while (heapIndex > 1 && less(heapIndex / 2, heapIndex)) {
            exchange(heapIndex, heapIndex / 2);
            //then we will swim in position of index/2
            heapIndex = heapIndex / 2;
        }

    }

    /**
     * sink the index key util the key's parent greater or equal it or become the end of the heap
     *
     * @param heapIndex target index
     */
    private void sink(int heapIndex) {
        assertIndex(heapIndex);

        while (heapIndex * 2 <= size) {
            //left child
            int childIndex = heapIndex * 2;
            //has 2 children nodes, compare them and get the greater one
            if (childIndex < size && less(childIndex, childIndex + 1)) {
                childIndex++;
            }
            //compare with the target key
            if (!less(heapIndex, childIndex)) {
                break;
            } else {
                exchange(heapIndex, childIndex);
                heapIndex = childIndex;
            }
        }
    }


    private void assertIndex(int... indexes) {
        for (int index : indexes) {
            if (index >= heap.length) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }

    /**
     * insert a key in the queue
     *
     * @param key key
     */
    @Override
    public void insert(Key key) {
        assertIndex(++size);
        //add the key to the tail
        heap[size] = key;
        //then swim the key
        swim(size);
    }

    /**
     * get the max priority key
     *
     * @return max priority key
     */
    @Override
    public Key max() {
        //the top of the heap
        return heap[1];
    }

    /**
     * remove the max priority key
     *
     * @return the removed key
     */
    @Override
    public Key delMax() {
        //get the max node
        Key max = max();
        exchange(1, size);
        //remove the end
        heap[size--] = null;
        //sink the end node
        sink(1);
        return max;
    }

    /**
     * check the queue whether is empty
     *
     * @return empty return true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * get the queue current size
     *
     * @return queue size
     */
    @Override
    public int size() {
        return size;
    }
}
