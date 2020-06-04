package org.catplayer.datastruct;

import java.util.Scanner;

public class SliceString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String checkString = scanner.next();
            assert checkString.length() <= 50 && checkString.length() > 0;
            int preIndex = 0;
            int postIndex = 1;
            int repeatCount = 0;
            while (postIndex <= checkString.length() - 1) {
                if (checkString.charAt(preIndex) != checkString.charAt(postIndex)) {
                    repeatCount++;
                    preIndex = postIndex;
                }
                postIndex++;
            }
            System.out.println(checkString.length() / repeatCount);
        }
    }
}
