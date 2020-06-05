package org.catplayer.sort.algorithms;

/**
 * shell sort
 * based on insertion sort and improve it
 * split to n sub arrays to use insertion sort and then decrement the n's value
 * and keep this procedure util the n become 1. So that we get the final order
 * <p>
 * o(N^3/2)
 *
 * @author catplayer
 * @since 1.0
 */
public class ShellSorter extends Sorter {
    @Override
    protected Comparable[] doSort(Comparable[] original) {

        int length = original.length;
        int h = 1;
        //todo not must use the number 3
        // this is the difficult of shell sort algorithm to specific the argument of n
        while (h < length / 3)
            h = 3 * h + 1;
        //do sort util the h = 1 sorted over
        //then we get the final sorted array
        while (h >= 1) {
            //sort the n sub-arrays, every sub-array has the right sort order
            //when n become 1, the whole array has been sorted
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && smaller(original[j], original[j - h]); j -= h) {
                    exchange(original, j, j - h);
                }

            }
            h = h / 3;

        }
        return original;
    }
}
