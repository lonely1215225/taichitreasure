package Algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w={3, 4, 2, 1};//每个物品的重量weight
        int[] val={1500, 3000, 500, 2000};//每个物品的价值
        int capacity=4;//背包容量为4
        int num=val.length;//物品个数（假设每个物品价值不同）

        //v[i][j]表示前i个物品中能装入容量为j的背包中的最大价值
        int[][] v=new int[num + 1][capacity + 1];

//        for (int i=1; i < v.length; i++) {
//            for (int j=1; j < v[i].length; j++) {
//                if (w[i - 1] > j)//如果第i-1个物品的重量大于容量j
//                    //为什么要将初始行和列都为0，关键就在下面这行代码
//                    v[i][j]=v[i - 1][j];//就将同一列的上一行最大价值赋给当前价
//                else//否则第i-1个物品的重量小于背包i容量j
//                    //比较上一个最大价值和当前价值val[i-1]加上
//                    //除去当前物品占的容量外，剩余的背包容量j-w[i-1]能容纳的最大值v[i-1][j-w[i-1]]
//                    v[i][j]=Math.max( v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]] );
//            }
//        }
        int[][] trace=new int[num+1][capacity+1];
        for (int i=1; i < v.length; i++) {
            for (int j=1; j < v[i].length; j++) {
                if (w[i - 1] > j)//如果第i-1个物品的重量大于容量j
                    //为什么要将初始行和列都为0，关键就在下面这行代码
                    v[i][j]=v[i - 1][j];//就将同一列的上一行最大价值赋给当前价
                else {
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j]=val[i - 1] + v[i - 1][j - w[i - 1]];
                        trace[i][j]=1;
                    }
                    else
                        v[i][j]=v[i-1][j];
                }
            }
        }

        int i1=trace.length-1;
        int j1=trace[0].length-1;
        while (i1>0&&j1>0){
            if (trace[i1][j1]==1){
                System.out.println("第"+i1+"个物品放到背包");
                j1-=w[i1-1];//背包容量减为背包容量j减去当前物品的重量的值
            }
            i1--;
        }

        //输出v
        for (int i=0; i < v.length; i++) {
            for (int j=0; j < v[i].length; j++) {
                System.out.print( v[i][j] + " " );
            }
            System.out.println();
        }
    }
}
