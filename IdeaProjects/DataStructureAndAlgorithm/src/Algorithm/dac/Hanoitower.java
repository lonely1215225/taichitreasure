package Algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
hanoitower( 3,'A','B','C' );
    }
    public static void hanoitower(int num,char a,char b,char c){
        if (num==1)
            System.out.println("��1Ƭ��"+a+"->"+c);//
        else {
            //2����������ӣ���һ������ֳ������֣�������ĺ������ϲ�ģ�
            hanoitower( num-1,a,c,b );//��һ�����ϲ����е����ӷŵ�Bλ�ã��ݹ�ֱ���ϲ�Ϊ1
            System.out.println("��"+num+"Ƭ��"+a+"->"+c);//����������ڴ����²������һ��һ���ŵ�C
            hanoitower( num-1,b,a,c );//��B�е��������ӣ��ŵ�Cλ���ϣ����
        }
    }
}
