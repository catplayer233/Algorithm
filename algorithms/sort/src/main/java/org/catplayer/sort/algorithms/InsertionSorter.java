package org.catplayer.sort.algorithms;

/**
 * insertion org.catplayer.sort
 * <p>
 * every time keep a relative sorted sub array util insert the last element
 *
 * @author catplayer
 * @since 1.0
 */
public class InsertionSorter<T extends Comparable<T>> extends Sorter<T> {
    @Override
    protected T[] doSort(T[] original) {
        //start from the second element
        for (int i = 1; i < original.length; i++) {
            //the different between the selection org.catplayer.sort and the insertion org.catplayer.sort
            //is the insertion org.catplayer.sort can take advantage of the sub array's org.catplayer.sort
            //so this org.catplayer.sort can reduce compare times
            for (int j = i; j > 0 && smaller(original[j], original[j - 1]); j--) {
                exchange(original, j, j - 1);
            }

        }

        return original;
    }
}
