package org.catplayer.sort.algorithms;

/**
 * Base class for sorting
 *
 * @param <T> compared object type which must implement {@link Comparable}
 * @author catplayer
 * @since 1.0
 */
public abstract class Sorter<T extends Comparable<T>> {

    //for different algorithms implement
    protected abstract T[] doSort(T[] original);

    /**
     * public api for client call
     *
     * @param original the original array
     * @return sorted array
     */
    public T[] sort(T[] original) {
        //todo add some base check
        return doSort(original);
    }

    /**
     * compare two element
     *
     * @param element        element
     * @param anotherElement another element
     * @return compare result, true: element < another element
     */
    public boolean smaller(T element, T anotherElement) {
        return element.compareTo(anotherElement) < 0;
    }

    /**
     * exchange 2 elements from an array
     *
     * @param original     original array
     * @param index        index
     * @param anotherIndex another index
     */
    public void exchange(T[] original, int index, int anotherIndex) {
        T temp = original[index];
        original[index] = original[anotherIndex];
        original[anotherIndex] = temp;
    }
}
