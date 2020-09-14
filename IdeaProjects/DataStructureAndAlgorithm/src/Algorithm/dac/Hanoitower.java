package Algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
hanoitower( 3,'A','B','C' );
    }
    public static void hanoitower(int num,char a,char b,char c){
        if (num==1)
            System.out.println("第1片从"+a+"->"+c);//
        else {
            //2个及多个盘子：第一步将其分成两部分：最下面的和所有上层的；
            hanoitower( num-1,a,c,b );//第一步将上层所有的盘子放到B位置，递归直到上层为1
            System.out.println("第"+num+"片从"+a+"->"+c);//将所有相对于处于下层的盘子一个一个放到C
            hanoitower( num-1,b,a,c );//将B中的所有盘子，放到C位置上，完成
        }
    }
}
