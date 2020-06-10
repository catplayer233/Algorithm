package org.catplayer.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.catplayer.sort.algorithms.*;

/**
 * compare sort algorithms's performance util
 * <p>
 * base on Algorithms 4th Edition
 *
 * @author catplayer
 * @since 1.0
 */
public class SortCompare {

    public static double time(String algorithm, Double[] array) {
        SorterAlgorithms sorterAlgorithm = SorterAlgorithms.get(algorithm);
        Sorter sorter;
        switch (sorterAlgorithm) {
            case INSERTION:
                sorter = new InsertionSorter();
                break;
            case SELECTION:
                sorter = new SelectionSorter();
                break;
            case HEAP:
                sorter = new HeapSorter();
                break;
            case MERGE:
//                sorter = new MergeSorter();
                sorter = new MergeSorter(false);
                break;
            case QUICK:
                sorter = new QuickSorter();
                break;
            case SHELL:
                sorter = new ShellSorter();
                break;
            default:
                throw new IllegalArgumentException("unsupported algorithms " + sorterAlgorithm);
        }
        Stopwatch stopwatch = new Stopwatch();
        sorter.sort(array);
        return stopwatch.elapsedTime();
    }

    public static double timeRandomInput(String algorithm, int size, int times) {
        double total = 0.0;
        Double[] doubles = new Double[size];
        //compute T times and get the sort times
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < size; j++) {
                doubles[j] = StdRandom.uniform();
            }
            total += time(algorithm, doubles);
        }
        return total;
    }

    public static void main(String[] args) {
        //the compared algorithms
        String algorithm1 = args[0];
        String algorithm2 = args[1];
        //sort arguments
        int size = Integer.parseInt(args[2]);
        int times = Integer.parseInt(args[3]);
        //sort spend time
        double totalTime1 = timeRandomInput(algorithm1, size, times);
        double totalTime2 = timeRandomInput(algorithm2, size, times);
        //print out result
        StdOut.printf("For %d random Doubles \n    %s is", size, algorithm1);
        StdOut.printf(" %.1f times faster than %s\n", totalTime2 / totalTime1, algorithm2);
    }
}
