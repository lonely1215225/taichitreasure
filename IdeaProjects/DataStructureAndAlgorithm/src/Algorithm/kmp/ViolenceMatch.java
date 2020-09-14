package Algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1="i z z zzyw";
        String s2="z zzy";
        int i=violenceMatch( s1, s2 );
        System.out.println( i );
    }

    public static int violenceMatch(String s1, String s2) {
        char[] c1=s1.toCharArray();
        char[] c2=s2.toCharArray();
        int i=0;
        int j=0;
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i=i-(j-1);//���ƥ���˲����ַ��������ǽ�����һ���ַ�ƥ��ʧ��
                // ����:z zƥ�䵽�ˣ����ǽ������Ŀո��z��ƥ�䣬��ô����i��Ҫ��i�˻�j-1����λ����������ƥ��
                j=0;
            }
        }
        if (j == c2.length) {//���ƥ�䵽�ˣ�˵��i<s1.length�ģ�
            // i��������Ӧ���ַ�һ����ƥ���ַ��������һ��
            return i - j;//����i-j����ƥ���ַ�����ԭ�ַ����е�ͷ����
        }
        return -1;
    }
}
