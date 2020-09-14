package Algorithm.leetcode.training;

import java.util.Arrays;

public class OrMinBitReverse {
    public static void main(String[] args) {
        int a = 4;
        int b = 2;
        int c = 7;
        int reverse=0;
        while (a != 0 || b != 0 || c != 0) {
            int aLowBit1 = a & 1;
            int bLowBit1 = b & 1;
            int cLowBit1 = c & 1;
            a >>= 1;
            b >>= 1;
            c >>= 1;
            if ((aLowBit1|bLowBit1)==cLowBit1)
                continue;
            if (cLowBit1==1)
                reverse++;
            else
                if (aLowBit1==1)
                    reverse++;
                if (bLowBit1==1)
                    reverse++;
        }
    }
}
