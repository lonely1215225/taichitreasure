package Algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1="i z z zzyw";
        String s2="z zzy";
        int i=violenceMatch( s1, s2 );
        System.out.println( i );
    }

    public static int violenceMatch(String s1, String s2) {
        char[] c1=s1.toCharArray();
        char[] c2=s2.toCharArray();
        int i=0;
        int j=0;
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i=i-(j-1);//如果匹配了部分字符串，但是接下来一个字符匹配失败
                // 假如:z z匹配到了，但是接下来的空格和z不匹配，那么索引i就要从i退回j-1个单位来继续进行匹配
                j=0;
            }
        }
        if (j == c2.length) {//如果匹配到了，说明i<s1.length的，
            // i的索引对应的字符一定是匹配字符串的最后一个
            return i - j;//所以i-j就是匹配字符串在原字符串中的头索引
        }
        return -1;
    }
}
