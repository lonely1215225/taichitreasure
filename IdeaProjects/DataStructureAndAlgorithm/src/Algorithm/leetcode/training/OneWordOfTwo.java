package Algorithm.leetcode.training;

import java.rmi.dgc.VMID;
import java.util.HashSet;

public class OneWordOfTwo {
    public static void main(String[] args) {
        char[] words = {'a', 'a', 'x', 'b', 'b'};
        System.out.println(oneOfTwo(words));
    }

    public static char oneOfTwo(char[] words) {
        HashSet<Character> set = new HashSet<>();
        int sum = 0;
        int length = words.length;
        int mid = length >> 1;
        if ((length & 1) == 0) {
            for (int i = 0, j = mid; i < mid; i++, j++) {
                set.add(words[i]);
                set.add(words[j]);
                sum+=words[i];
                sum+=words[j];
            }
        } else {
            for (int i = 0,j=mid; j < length; j++) {
                if (i<mid){
                    set.add(words[i]);
                    sum+=words[i];
                    i++;
                }
                set.add(words[j]);
                sum+=words[j];
            }
        }
        int sum1 = 0;
        for (int once : set) {
            sum1 += once;
        }
        return (char)((sum1 << 1) - sum);
    }

}
