package org.catplayer.search;

import java.util.Arrays;
import java.util.Scanner;

/**
 * binary org.catplayer.search
 *
 * @author catplayer
 * @since 1.0
 */
public class BinarySearch {

    public static int search(int[] sortedArray, int searchKey) {
        assert sortedArray != null && sortedArray.length > 0;
        int lowIndex = 0;
        int highIndex = sortedArray.length - 1;
        while (lowIndex <= highIndex) {
            int middleIndex = (lowIndex + highIndex) >>> 1;
            int middleValue = sortedArray[middleIndex];
            if (middleValue == searchKey) {
                return middleIndex + 1;
            } else if (middleValue < searchKey) {
                //less than search key, search at from middleIndex(exclude) to highIndex
                lowIndex = middleIndex + 1;
            } else {
                //bigger than search key, search at lowIndex to middleIndex(exclude)
                highIndex = middleIndex - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 8, 80};
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int searchedKey = Integer.parseInt(scanner.next());
            System.out.println("sorted array is " + Arrays.toString(array));
            System.out.println(String.format("searched key is %d", searchedKey));
            int searchResult = search(array, searchedKey);
            System.out.println(String.format("the key is %s and the position is %d", (searchResult > 0 ? "exists" : "not exists"), (searchResult > 0 ? ++searchResult : searchResult)));
        }

    }
}
