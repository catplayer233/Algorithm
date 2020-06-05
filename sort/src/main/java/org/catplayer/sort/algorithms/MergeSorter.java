package org.catplayer.sort.algorithms;

/**
 * merge sort
 * <p>
 * used the divide-and-conquer paradigm
 * based on recursive
 * <p>
 * o(N log N)
 */
public class MergeSorter extends Sorter {

    private Comparable[] extra;

    private boolean top_down_flag = true;

    public MergeSorter(boolean top_down_flag) {
        this.top_down_flag = top_down_flag;
    }

    public MergeSorter() {
    }

    @Override
    protected Comparable[] doSort(Comparable[] original) {
        //allocate space to extra array
        extra = new Comparable[original.length];
        //do sort
        if (top_down_flag) {
            topDownSort(original, 0, original.length - 1);
        } else {
            bottomUpSort(original);
        }
        return original;
    }

    /**
     * top to down sort
     */
    private void topDownSort(Comparable[] original, int low, int high) {
        if (low == high) {//equals means there only one element, no need to compare
            return;
        }
        int middle = (low + high) / 2;
        topDownSort(original, low, middle);
        topDownSort(original, middle + 1, high);
        merge(original, low, middle, high);
    }

    /**
     * bottom to up sort
     */
    private void bottomUpSort(Comparable[] original) {
        //every time merge a small length and util the length to array's length
        int length = original.length;
        for (int size = 1; size < length; size += size) {//split multi sized merge
            for (int low = 0; low < length - size; low += size + size) {//split (length / 2size) merge
                merge(original, low, low + size - 1, Math.min(low + size + size - 1, length - 1));
            }

        }


    }


    /**
     * merge 2 sub arrays, low to middle as an array and middle+1 to high as another array
     * <p>
     * TODO you also can use insertion sort to implement the ordered sub array
     *
     * @param original original array
     * @param low      low index
     * @param middle   middle index
     * @param high     high index
     */
    private void merge(Comparable[] original, int low, int middle, int high) {
        //left array index, initial is low value
        //the left array is (low to middle)
        int leftIndex = low;
        //right array index, initial is middle+1
        //the right array is (middle+1 to high)
        int rightIndex = middle + 1;

        //copy low to high elements to extra array
        System.arraycopy(original, low, extra, low, high - low + 1);
        //compare and merge
        //assign the value form one of the 2 arrays, the array current index need move backward
        for (int mergeIndex = low; mergeIndex <= high; mergeIndex++) {

            if (leftIndex > middle) {//left array is exhausted
                original[mergeIndex] = extra[rightIndex++];//keep the right index move
            } else if (rightIndex > high) {//right array is exhausted
                original[mergeIndex] = extra[leftIndex++];//keep the left index move
            } else if (smaller(extra[leftIndex], extra[rightIndex])) {//compare the 2 arrays current index's element
                original[mergeIndex] = extra[leftIndex++];
            } else {
                original[mergeIndex] = extra[rightIndex++];
            }
        }
    }
}
