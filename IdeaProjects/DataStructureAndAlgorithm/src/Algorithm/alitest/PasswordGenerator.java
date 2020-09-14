package Algorithm.alitest;

import java.util.Arrays;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        String password = scanner.next();
        System.out.println(Arrays.toString(verify(password)));

    }
//    注册网站时，需要使用包含不同类型（数字、符号、大写字母、小写字母）的字符，和特定长度。
//    检查一个密码内容同时包含以上 4 种类型的字符，并且长度在8-120 个字符之间。
//    符合要求，返回 0；
//    长度不符合返回 1；
//    类型不符合要求返还 2。
//    可以一次输入多组密码，以空格符间隔，空格符不作为密码。
    public static int[] verify(String pass){
        String[] split = pass.split(" ");
        int[] flags=new int[split.length];
        //48-57   A-Z :65-90   a-z:97-122
        boolean markInt=false;
        boolean markLow=false;
        boolean markUp=false;
        for (int i = 0; i < split.length; i++) {
            int length = split[i].length();
            if (length<8||length>120){ flags[i]=1; continue;}
            for (int j = 0; j < split[i].length(); j++) {
                byte[] bytes = split[i].getBytes();
                for (int k = 0; k < bytes.length; k++) {
                    if (bytes[k]>=48&&bytes[k]<=57) markInt=true;
                    if (bytes[k]>=65&&bytes[k]<=90) markUp=true;
                    if (bytes[k]>=97&&bytes[k]<=122) markLow=true;
                }
            }
            if (markInt&&markLow&&markUp) flags[i]=0;
            else flags[i]=2;
        }
        return flags;
    }
}
