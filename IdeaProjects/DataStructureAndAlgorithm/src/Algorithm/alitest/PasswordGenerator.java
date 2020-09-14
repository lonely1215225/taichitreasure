package Algorithm.alitest;

import java.util.Arrays;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        String password = scanner.next();
        System.out.println(Arrays.toString(verify(password)));

    }
//    ע����վʱ����Ҫʹ�ð�����ͬ���ͣ����֡����š���д��ĸ��Сд��ĸ�����ַ������ض����ȡ�
//    ���һ����������ͬʱ�������� 4 �����͵��ַ������ҳ�����8-120 ���ַ�֮�䡣
//    ����Ҫ�󣬷��� 0��
//    ���Ȳ����Ϸ��� 1��
//    ���Ͳ�����Ҫ�󷵻� 2��
//    ����һ������������룬�Կո��������ո������Ϊ���롣
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
