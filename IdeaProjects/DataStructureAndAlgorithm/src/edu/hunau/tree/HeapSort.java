package edu.hunau.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr={4,6,8,5,9};
        heapSort( arr );
//        int[] nums = new int[8000000];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = (int) (Math.random() * 10000000);
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
//        String beforetime = simpleDateFormat.format(new Date());
//        System.out.println("before:" + beforetime);
//        heapSort(nums);
//        Date date = new Date();
//        String afterTime = simpleDateFormat.format(date);
//        System.out.println("after:" + afterTime);
        //800w�������
//        before:2020/03/03 14:17:42:578
//        after:2020/03/03 14:17:45:568
        //ʱ������3��4��  ʱ��O(nlogn)
    }
    public static void heapSort(int[] arr){
        int temp=0;
        //������һ�������󶥶�
        for (int i=arr.length/2-1; i >=0 ; i--) {
            adjustHeap( arr,i,arr.length );
        }
        //�Ѷ����β����
        for (int j=arr.length-1;j>0;j--){
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap( arr,0,j);//�����j��ָ�ѵ����źõľͲ���������
        }
       // System.out.println( Arrays.toString(arr));
    }
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];//�������һ����Ҷ�ӽڵ����
        for (int k=(i<<1)+1;k<length;k=(k<<1)+1){//������Ҷ�ӽڵ��������
            if (k+1<length&&arr[k]<arr[k+1])//�ȽϷ�Ҷ�ӽڵ�����Һ���
                k++;//ָ����Ҷ��
            if (arr[k]>temp) {//���Ҷ���븸�ڵ㣨��Ҷ�ӽڵ㣩�Ƚ�
                arr[i]=arr[k];//
                i=k;
            }else
                break;
        }
        arr[i]=temp;
    }
}
