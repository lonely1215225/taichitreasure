package Algorithm.leetcode.training;

public class ReverseInt {
    public static void main(String[] args) {
        int x=1534236469;
        System.out.println(reverse(x));
    }
    public static int reverse(int x){
        int ret=0;
        int neg=x>0?1:-1;
        x*=neg;
        if(x>Integer.MAX_VALUE||x<Integer.MIN_VALUE) return 0;
        while(x!=0){
            if ((ret*10l)>Integer.MAX_VALUE){
                return 0;
            }
            ret = x % 10 + ret * 10;
            x/=10;
        }
        return (ret>Integer.MAX_VALUE||ret<Integer.MIN_VALUE)?0:ret*neg;
    }
}
