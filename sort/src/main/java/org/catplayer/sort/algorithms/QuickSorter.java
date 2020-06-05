package org.catplayer.sort.algorithms;

import java.util.Arrays;

/**
 * quick sort
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

    private int absolutePosition(Comparable[] original, int low, int high) {
        //the target for confirming its correct position
        Comparable comparedTarget = original[low];
        int lessIndex = low;
        int greaterIndex = high + 1;
        while (true) {
            while (smaller(original[++lessIndex], comparedTarget)) {
                if (lessIndex == high) {
                    break;
                }
            }
            while (smaller(comparedTarget, original[--greaterIndex])) {
                if (greaterIndex == low) {
                    break;
                }
            }
            if (lessIndex >= greaterIndex) {
                break;
            }
            exchange(original, lessIndex, greaterIndex);
        }
        exchange(original, greaterIndex, low);

        return greaterIndex;

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
