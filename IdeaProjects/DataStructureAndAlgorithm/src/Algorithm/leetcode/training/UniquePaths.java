package Algorithm.leetcode.training;

public class UniquePaths {
    public static void main(String[] args) {
        int kinds = kinds(6, 6);
        System.out.println(kinds);
    }

    public static int kinds(int m, int n) {
        int[][] map = new int[m][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 || j == 0) map[i][j] = 1;
                else map[i][j] = map[i - 1][j] + map[i][j - 1];
            }
        }
        return map[m-1][n-1];
    }
}
