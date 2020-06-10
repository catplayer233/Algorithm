package org.catplayer.sort.algorithms;

import org.catplayer.sort.HeapMaxPriorityQueue;

import java.util.Arrays;

/**
 * heap sort based on a binary-heap priority queue
 *
 * @author catplayer
 * @since 1.0
 */
public class HeapSorter extends Sorter {

    @Override
    protected Comparable[] doSort(Comparable[] original) {

        HeapMaxPriorityQueue heapMaxPriorityQueue = new HeapMaxPriorityQueue(original);
        return heapMaxPriorityQueue.doSort();
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{
                10, 30, 52, 4, 6, 85, 7, 96, 50
        };
        HeapSorter heapSorter = new HeapSorter();
        Comparable[] sort = heapSorter.sort(array);
        System.out.println(Arrays.toString(sort));

    }
}
