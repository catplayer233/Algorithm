package org.catplayer.sort.algorithms;

import java.util.Arrays;

/**
 * quick sort
 * <p>
 * the point is
 * <li>every time confirm an element's absolute position</li>
 * <li>left part are all less than the element</li>
 * <li>right part are all greater than the element</li>
 * and continue sort the left and right part util all elements positions are confirmed
 * <p>
 * o(N log N)
 *
 * @author catplayer
 * @since 1.0
 */
public class QuickSorter extends Sorter {

    @Override
    protected Comparable[] doSort(Comparable[] original) {
        int length = original.length;
        quickSort(original, 0, length - 1);
        return original;
    }

    private void quickSort(Comparable[] original, int low, int high) {
        if (high <= low) {
            return;//just one item, no need to sort
        }
        int absolutePosition = absolutePosition(original, low, high);
        quickSort(original, low, absolutePosition - 1);
        quickSort(original, absolutePosition + 1, high);

    }

//    /**
//     * the Algorithms fourth edition's version
//     *
//     * @param original original
//     * @param low      low
//     * @param high     high
//     * @return the low index's element's absolute position
//     */
//    private int absolutePosition(Comparable[] original, int low, int high) {
//        //the target for confirming its correct position
//        Comparable comparedTarget = original[low];
//        int lessIndex = low;
//        int greaterIndex = high + 1;
//        while (true) {
//            while (smaller(original[++lessIndex], comparedTarget)) {
//                if (lessIndex == high) {
//                    break;
//                }
//            }
//            while (smaller(comparedTarget, original[--greaterIndex])) {
//                if (greaterIndex == low) {
//                    break;
//                }
//            }
//            if (lessIndex >= greaterIndex) {
//                break;
//            }
//            exchange(original, lessIndex, greaterIndex);
//        }
//        exchange(original, greaterIndex, low);
//
//        return greaterIndex;
//
//    }

    /**
     * catplayer's version
     *
     * @param original original
     * @param low      low
     * @param high     high
     * @return the low index's element's absolute position
     */
    private int absolutePosition(Comparable[] original, int low, int high) {

        Comparable target = original[low];
        int less = low + 1;
        int greater = high;
        //do exchange
        while (less < greater) {
            //find the less part has a greater one
            if (smaller(target, original[less])) {
                //check the greater and find the less one
                while (less < greater) {
                    //find the less one, and move forward less, move backward grater
                    if (smaller(original[greater], target)) {
                        //exchange them
                        exchange(original, less, greater);
                        //move index
                        less++;
                        greater--;
                        break;
                    }
                    //move backward
                    greater--;
                }
            } else {
                //move forward
                less++;
            }
        }

        //compare and swap
        if (smaller(target, original[less])) {
            exchange(original, low, less - 1);
            return less - 1;
        } else {
            exchange(original, low, less);
            return less;
        }

    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{
                10, 30, 52, 4, 6, 85, 7, 96, 50
        };
        QuickSorter quickSorter = new QuickSorter();
        quickSorter.sort(array);
        System.out.println(Arrays.toString(array));

    }
}
