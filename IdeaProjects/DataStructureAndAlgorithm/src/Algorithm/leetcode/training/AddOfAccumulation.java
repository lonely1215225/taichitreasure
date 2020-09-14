package Algorithm.leetcode.training;

public class AddOfAccumulation {
    public static void main(String[] args) {
        System.out.println(add(9));
    }
    public static int add(int n){
        boolean x=n>0 && (n+=add(--n))!=0;
        return n;
    }
}
