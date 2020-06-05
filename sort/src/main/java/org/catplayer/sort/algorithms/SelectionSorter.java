package org.catplayer.sort.algorithms;

/**
 * Selection Sort：冒泡排序
 * 两层嵌套，每次得到一个正确位置的元素
 * <p>
 * o(n^2)
 *
 * @author catplayer
 * @since 1.0
 */
public class SelectionSorter extends Sorter {

    @Override
    protected Comparable[] doSort(Comparable[] original) {
        //do sort
        for (int i = 0; i < original.length; i++) {
            for (int j = i + 1; j < original.length; j++) {
                if (smaller(original[j], original[i])) {
                    exchange(original, i, j);
                }
            }
        }
        return original;
    }

}
