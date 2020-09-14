package Algorithm.leetcode.training;

import java.util.*;

public class MaxNoRepeatString {
    public static void main(String[] args) {
        String str = "abcarcebb";
        int result = getResult(str);
        System.out.println(result);
    }

    public static int getResult(String s) {
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>(s.length());
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = list.indexOf(chars[i]);
            if (index != -1) {
                while (index >= 0) {
                    list.remove(index);
                    index--;
                }
            }
            list.add(chars[i]);
            if (list.size() > max)
                max = list.size();
        }
        return max;
    }
}
