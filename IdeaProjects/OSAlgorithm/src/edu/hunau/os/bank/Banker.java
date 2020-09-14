package edu.hunau.os.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banker {
    private int[] total = {10, 5, 7};
    private String[] process = {"p0", "p1", "p2", "p3", "p4"};
    private int[][] max = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 3},
            {4, 3, 3}};
    private int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}};
    private int[][] need = new int[5][3];
    private int[] available = new int[3];
    private int[] request = new int[3];

    public static void main(String[] args) {
        Banker banker = new Banker();
        //     System.out.println("最多还需要:");
//        for (int[] ne : banker.setNeed()) {
//            System.out.println(Arrays.toString(ne));
//        }
        int[] ints = banker.setAvailable();
//        System.out.println(Arrays.toString(ints));
        //banker.tryAlloc(banker.setNeed(), ints);

        banker.request(0, new int[]{2, 1, 1}, banker.setNeed(), ints);
    }

    public void request(int index, int[] request, int[][] need, int[] available) {
        int[][] temp = need;
        int[] temp1 = available;
        int compare = compare(request, available);
        int compare1 = compare(request, need[index]);
        if (compare == -1 && compare1 == -1) {
            for (int i = 0; i < request.length; i++) {
                available[i] -= request[i];
                allocation[index][i] += request[i];
                need[index][i] -= request[i];
            }
        } else {
            System.out.println("可用资源不足");
            return;
        }

        boolean b = tryAlloc(temp, temp1);

        if (!b){
            System.out.println("资源分配失败");
            int[][] ints = new int[process.length][max[0].length * max[0].length];
        }
    }

    public boolean tryAlloc(int[][] temp, int[] temp1) {

        boolean[] flag = new boolean[process.length];
        List<String> list = new ArrayList<>();
        for (int i = 0; i < process.length; ) {
            if (!flag[i]) {
                int compare = compare(temp[i], temp1);
                if (compare == -1 && !flag[i]) {
                    for (int j = 0; j < allocation[i].length; j++) {
                        temp1[j] += allocation[i][j];
                    }
                    list.add(process[i]);
                    flag[i] = true;
                    System.out.println(Arrays.toString(temp1));
                    i = 0;
                    continue;
                }
            }
            i++;
        }
        System.out.println(list);
        if (list.size() < 5) {
            System.out.println("不安全状态，有可能发生死锁");
            return false;
        }
        return true;
    }

    public int compare(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i])
                return 1;
        }
        return -1;
    }

    public int[] setAvailable() {
        for (int j = 0; j < total.length; j++) {
            int temp = total[j];
            for (int i = 0; i < allocation.length; i++) {
                temp -= allocation[i][j];
            }
            available[j] = temp;
        }
        return available;
    }

    public int[][] setNeed() {
        for (int i = 0; i < max.length; i++) {
            for (int j = 0; j < max[i].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        return need;
    }
}
