package Algorithm.alitest;

public class CoinSelectMin {
    public static void main(String[] args) {
        int[] coins = {2, 5, 7};
        int min = getMin(coins, 27);
        System.out.println(min);
    }

    public static int getMin(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        f[0] = 0;
        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && f[i - coins[j]] != Integer.MAX_VALUE)
                    f[i] = Math.min(f[i - coins[j]] + 1, f[i]);//加一可能导致f[i]溢出
            }
        }
        if (f[amount] == Integer.MAX_VALUE) return -1;
        return f[amount];
    }
}
