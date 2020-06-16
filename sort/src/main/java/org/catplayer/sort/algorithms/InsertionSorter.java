package org.catplayer.sort.algorithms;

/**
 * insertion sort
 * <p>
 * every time keep a relative sorted sub array util insert the last element
 * <p>
 * o(n^2)
 *
 * @author catplayer
 * @since 1.0
 */
public class InsertionSorter extends Sorter {
    @Override
    protected Comparable[] doSort(Comparable[] original) {
        //start from the second element
        for (int i = 1; i < original.length; i++) {
            //the different between the selection sort and the insertion sort
            //is the insertion sort can take advantage of the sub array's sort
            //so this sort can reduce compare times
            for (int j = i; j > 0 && smaller(original[j], original[j - 1]); j--) {
                exchange(original, j, j - 1);
            }

        }

        return original;
    }
}
