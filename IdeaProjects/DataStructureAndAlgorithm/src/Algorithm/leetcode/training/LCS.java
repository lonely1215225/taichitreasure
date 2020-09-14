package Algorithm.leetcode.training;

public class LCS {
    public static void main(String[] args) {
       String text1 = "abcde", text2 = "ace";
        System.out.println(lcs(text1,text2));
    }
    public static int lcs(String s1,String s2){
        int[][] dp=new int[s1.length()+1][s2.length()+1];
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (c1[i-1]==c2[j-1]) {
                    dp[i][j] =dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
