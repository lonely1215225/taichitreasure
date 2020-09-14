package Algorithm.alitest;

import java.util.ArrayList;
import java.util.List;

public class BILIBILI {
//    一个由小写字母组成的字符串可以看成一些同一字母的最大碎片组
//    成的。例如,"aaabbaaac"是由下面碎片组成的:'aaa', 'bb';'c'。现在给
//            定一个字符串,请你帮助计算这个字符串的所有碎片的平均长度是多
//    少，结果直接取整，无需保留小数

    public static void main(String[] args) {
        int[] arr={0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k=3;
        int ones = getMaxConsecutiveOnes(arr, k);
        System.out.println(ones);
//        String s="aaabbaaaac";
//        int i = GetFragment(s);
//        System.out.println(i);

    }
    public static int getMaxConsecutiveOnes (int[] arr, int k) {
        int left=0,right=0,max=0,zero=0;
        while ((right!=arr.length)){
            if (arr[right++]==0) zero++;
            while (zero>k){
                if (arr[left++]==0){
                    --zero;
                }
            }
            int count=right-left;
            max=max>count?max:count;
        }
        return max;
    }
    public int[] SpiralMatrix (int[][] matrix) {
        // write code here
        int[] ints=new int[0];
        List<Integer> res=new ArrayList<>();

        if (matrix==null||matrix.length<1||matrix[0].length<1) return ints;
        int left=0;
        int right=matrix[0].length-1;
        int top=0;
        int bottom=matrix.length-1;
        int num=(right+1)*(bottom+1);
        while (num>0){
            for (int i=left;i<=right;i++){
                res.add(matrix[top][i]);
                num--;
            }
            if (num<=0)break;
            top++;
            for (int i = top; i <=bottom ; i++) {
                res.add(matrix[i][right]);
                num--;
            }
            if (num<=0) break;
            right--;
            for (int i=right;i>=left;i--){
                res.add(matrix[bottom][i]);
                num--;
            }
            if (num<=0)break;
            bottom--;
            for (int i = bottom; i >=top ; i--) {
                res.add(matrix[i][left]);
                num--;
            }
            if (num<=0) break;
            left++;
        }
        ints=new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ints[i]=res.get(i);
        }
        return ints;
    }
//    public static int GetFragment (String str) {
//        char[] chars = str.toCharArray();
//        List<List<Character>> lists=new ArrayList<>();
//        int count=0;
//        boolean flag=false;
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i]==chars[i+1]){
//                count++;
//            }else {
//                flag=true;
//            }
//            if (flag){
//                List<Character> list=new ArrayList<>();
//                int j=i-count;
//                while (count>=0){//String s="aaabbaaaac";
//                    list.add(chars[j]);
//                    j++;
//                    count--;
//                }
//                count=0;
//                flag=false;
//                lists.add(list);
//            }
//        }
//    }
}
