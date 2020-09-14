package Algorithm.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String s1="BBC ABCDAB ABCDABCDABDE";
        String s="ABCDABD";
        int[] ints=kmpNext( s );
        int index=kmpSearch( s1, s, ints );
        System.out.println( Arrays.toString( ints ) );
        System.out.println( index );
    }

    public static int kmpSearch(String s1, String s2, int[] next) {
        char[] c1=s1.toCharArray();
        char[] c2=s2.toCharArray();
        for (int i=0, j=0; i < c1.length; i++) {
            //ºËÐÄ
            while (j > 0 && c1[i] != c2[j])
                j=next[j - 1];
            if (c1[i] == c2[j]) {
                j++;
            }
            if (j == c2.length)
                return i - j + 1;
        }
        return -1;
    }

    public static int[] kmpNext(String s) {
        char[] array=s.toCharArray();
        int[] next=new int[array.length];
        next[0]=0;
        for (int i=1, j=0; i < array.length; i++) {
            while (j > 0 && array[i] != array[j]) {
                j=next[j - 1];
            }
            if (array[i] == array[j]) {
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
