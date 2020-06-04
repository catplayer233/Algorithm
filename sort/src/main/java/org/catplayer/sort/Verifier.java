package org.catplayer.sort;

import org.catplayer.sort.algorithms.InsertionSorter;
import org.catplayer.sort.algorithms.SelectionSorter;
import org.catplayer.sort.algorithms.Sorter;

import java.util.Arrays;
import java.util.Scanner;

public class Verifier {

    public static void main(String[] args) {

        Sorter<Integer> sorter;
        //selection org.catplayer.sort
        sorter = new SelectionSorter<>();
        //insertion org.catplayer.sort
        sorter = new InsertionSorter<>();
        //
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            if (message.equals("exit")) {
                System.exit(0);
            } else {
                int[] ints = Arrays.stream(message.split(",")).mapToInt(Integer::parseInt).toArray();
                Integer[] integers = new Integer[ints.length];
                for (int i = 0; i < integers.length; i++) {
                    integers[i] = ints[i];
                }
                Integer[] sortedArray = sorter.sort(integers);

                System.out.println("algorithms: " + sorter.getClass().getSimpleName() + "get the sorted array: " + Arrays.toString(sortedArray));
            }
        }
    }
}
