package org.catplayer.sort.algorithms;

/**
 * Base class for sorting
 *
 * @author catplayer
 * @since 1.0
 */
public abstract class Sorter {

    //for different algorithms implement
    protected abstract Comparable[] doSort(Comparable[] original);

    /**
     * public api for client call
     *
     * @param original the original array
     * @return sorted array
     */
    public Comparable[] sort(Comparable[] original) {
        //todo add some base check
        Comparable[] sorted = doSort(original);
        assertSorted(sorted);
//        System.out.println(Arrays.toString(sorted));
        return sorted;
    }

    private void assertSorted(Comparable[] sorted) {
        for (int i = 0; i < sorted.length - 1; i++) {
            if (smaller(sorted[i + 1], sorted[i])) {
                throw new RuntimeException("the sort result is not correct, check your sort algorithm");
            }
        }
    }

    /**
     * compare two element
     *
     * @param element        element
     * @param anotherElement another element
     * @return compare result, true: element < another element
     */
    public boolean smaller(Comparable element, Comparable anotherElement) {
        return element.compareTo(anotherElement) < 0;
    }

    /**
     * exchange 2 elements from an array
     *
     * @param original     original array
     * @param index        index
     * @param anotherIndex another index
     */
    public void exchange(Comparable[] original, int index, int anotherIndex) {
        Comparable temp = original[index];
        original[index] = original[anotherIndex];
        original[anotherIndex] = temp;
    }
}
