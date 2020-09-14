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
        //800w随机数据
//        before:2020/03/03 14:17:42:578
//        after:2020/03/03 14:17:45:568
        //时间大概在3，4秒  时间O(nlogn)
    }
    public static void heapSort(int[] arr){
        int temp=0;
        //调整成一个基本大顶堆
        for (int i=arr.length/2-1; i >=0 ; i--) {
            adjustHeap( arr,i,arr.length );
        }
        //堆顶与堆尾交换
        for (int j=arr.length-1;j>0;j--){
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap( arr,0,j);//这里的j是指堆底已排好的就不用再排了
        }
       // System.out.println( Arrays.toString(arr));
    }
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];//保存最后一个非叶子节点的数
        for (int k=(i<<1)+1;k<length;k=(k<<1)+1){//遍历非叶子节点的左右子
            if (k+1<length&&arr[k]<arr[k+1])//比较非叶子节点的左右孩子
                k++;//指向大的叶子
            if (arr[k]>temp) {//大的叶子与父节点（非叶子节点）比较
                arr[i]=arr[k];//
                i=k;
            }else
                break;
        }
        arr[i]=temp;
    }
}
