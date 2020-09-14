package Algorithm.binarysearchnorecursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr={5,4,0,2,8,99,23,78,54,21,1,5,5,6};
        Arrays.sort(arr);
        System.out.println(Arrays.toString( arr ));
//        System.out.println( binarySearch( arr, 54 ) );
        List<Integer> list=multiBinarySearch( arr, 5 );
        System.out.println( "Î»ÖÃÎª£º"+Arrays.toString( list.toArray() ) );
    }

    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        int mid;
        while (left<=right){
            mid=(left+right)/2;
            if (arr[mid]==target)
                return mid;
            else if(arr[mid]<target)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }
    public static List<Integer> multiBinarySearch(int[] arr, int target){
        int left=0;
        int right=arr.length-1;
        int mid;
        int temp;
        LinkedList<Integer> list=new LinkedList<>(  );
        while (left<=right){
            mid=(left+right)/2;
            if (arr[mid]==target) {
                temp=mid-1;
                list.addFirst( mid );
                while (arr[temp]==target) {
                    list.addFirst( temp );
                    temp--;
                }
//                temp=mid+1;
//                while (arr[temp]==target){
//                    list.add( temp );
//                    temp++;
//                }
                return list;
            }
            else if(arr[mid]<target)
                left=mid+1;
            else
                right=mid-1;
        }
        return null;
    }
}
