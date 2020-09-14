package Algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w={3, 4, 2, 1};//ÿ����Ʒ������weight
        int[] val={1500, 3000, 500, 2000};//ÿ����Ʒ�ļ�ֵ
        int capacity=4;//��������Ϊ4
        int num=val.length;//��Ʒ����������ÿ����Ʒ��ֵ��ͬ��

        //v[i][j]��ʾǰi����Ʒ����װ������Ϊj�ı����е�����ֵ
        int[][] v=new int[num + 1][capacity + 1];

//        for (int i=1; i < v.length; i++) {
//            for (int j=1; j < v[i].length; j++) {
//                if (w[i - 1] > j)//�����i-1����Ʒ��������������j
//                    //ΪʲôҪ����ʼ�к��ж�Ϊ0���ؼ������������д���
//                    v[i][j]=v[i - 1][j];//�ͽ�ͬһ�е���һ������ֵ������ǰ��
//                else//�����i-1����Ʒ������С�ڱ���i����j
//                    //�Ƚ���һ������ֵ�͵�ǰ��ֵval[i-1]����
//                    //��ȥ��ǰ��Ʒռ�������⣬ʣ��ı�������j-w[i-1]�����ɵ����ֵv[i-1][j-w[i-1]]
//                    v[i][j]=Math.max( v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]] );
//            }
//        }
        int[][] trace=new int[num+1][capacity+1];
        for (int i=1; i < v.length; i++) {
            for (int j=1; j < v[i].length; j++) {
                if (w[i - 1] > j)//�����i-1����Ʒ��������������j
                    //ΪʲôҪ����ʼ�к��ж�Ϊ0���ؼ������������д���
                    v[i][j]=v[i - 1][j];//�ͽ�ͬһ�е���һ������ֵ������ǰ��
                else {
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j]=val[i - 1] + v[i - 1][j - w[i - 1]];
                        trace[i][j]=1;
                    }
                    else
                        v[i][j]=v[i-1][j];
                }
            }
        }

        int i1=trace.length-1;
        int j1=trace[0].length-1;
        while (i1>0&&j1>0){
            if (trace[i1][j1]==1){
                System.out.println("��"+i1+"����Ʒ�ŵ�����");
                j1-=w[i1-1];//����������Ϊ��������j��ȥ��ǰ��Ʒ��������ֵ
            }
            i1--;
        }

        //���v
        for (int i=0; i < v.length; i++) {
            for (int j=0; j < v[i].length; j++) {
                System.out.print( v[i][j] + " " );
            }
            System.out.println();
        }
    }
}
