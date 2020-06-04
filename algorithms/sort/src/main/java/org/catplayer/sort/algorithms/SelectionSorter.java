package org.catplayer.sort.algorithms;

/**
 * Selection Sort：冒泡排序
 * 两层嵌套，每次得到一个正确位置的元素
 *
 * @author catplayer
 * @since 1.0
 */
public class SelectionSorter<T extends Comparable<T>> extends Sorter<T> {

    @Override
    protected T[] doSort(T[] original) {
        //do org.catplayer.sort
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
