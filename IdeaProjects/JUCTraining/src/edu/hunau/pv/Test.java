package edu.hunau.pv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    /* 题目2：
给定一个数组array，如[23, 27, 33, 45, ....]；
给定一个常数y(已知： y <= sum(array))，如： 5；
求解： 将常数y按照比率 23: 27:33: 45:...（比率越接近越好）将 y 分解，获得每一个占比的值是多少。

要求数组array的每一个数（如：第一个数23）分解获得的值不能比当前值大（23）；
要求分解获得每一个值必须为整数；

举例子：
array = [20, 30, 50]
y = 10
则： result = [2, 3, 5]
*/
    public static void main(String[] args) {
        int[] arr = {23, 27, 33, 45};
        int y = 5;

        int sum=0;
        for (int i : arr) {
            sum+=i;
        }
        double count=0;
        double temp=0;
        double[] rate=new double[arr.length];
        for (int i=0 ;i<arr.length;i++) {
            double radio=(double) arr[i]/sum;
            //System.out.println(radio);
            if (count<(double) y)
            {
                //System.out.println("radio*y="+(radio*y));
                rate[i]=radio*y;
                temp+=(radio*y);
                count+=(radio*y);
                //System.out.println("count="+count);
            }
        }
        for (double i :rate) {
            System.out.println((int)((i/temp)*10));
        }
    }

}
