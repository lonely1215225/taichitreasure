package edu.hunau.pv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    /* ��Ŀ2��
����һ������array����[23, 27, 33, 45, ....]��
����һ������y(��֪�� y <= sum(array))���磺 5��
��⣺ ������y���ձ��� 23: 27:33: 45:...������Խ�ӽ�Խ�ã��� y �ֽ⣬���ÿһ��ռ�ȵ�ֵ�Ƕ��١�

Ҫ������array��ÿһ�������磺��һ����23���ֽ��õ�ֵ���ܱȵ�ǰֵ��23����
Ҫ��ֽ���ÿһ��ֵ����Ϊ������

�����ӣ�
array = [20, 30, 50]
y = 10
�� result = [2, 3, 5]
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
