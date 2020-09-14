package Algorithm.kruskal;

import java.util.Arrays;

public class KruskalAlgorithm {
    private int edgeNum;//边
    private char[] vertexs;//顶点
    private int[][] matrix;//邻接矩阵
    private static final int INF=Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs=new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][]={
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalAlgorithm kruskalAlgorithm=new KruskalAlgorithm( vertexs, matrix );
        kruskalAlgorithm.print();

        kruskalAlgorithm.kruskal();
    }

    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {
        this.vertexs=new char[vertexs.length];
        //一维数组浅拷贝
        this.vertexs=vertexs.clone();
        //二维数组深拷贝
        this.matrix=new int[matrix.length][];
        for (int i=0; i < matrix.length; i++)
            this.matrix[i]=matrix[i].clone();

        //统计边数，单向边数
        for (int i=0; i < matrix.length; i++) {
            for (int j=i + 1; j < matrix[i].length; j++) {//单向
                if (matrix[i][j] != INF)
                    edgeNum++;
            }
        }
    }

    public void print( ) {
        System.out.println( "邻接矩阵为：" );
        for (int[] m : this.matrix) {
            for (int we : m)
                System.out.printf( "%12d", we );
            System.out.println();
        }
    }

    public void kruskal( ) {
        int index=0;
        int[] ends=new int[edgeNum];//存储终点
        EData[] rets=new EData[edgeNum];
        EData[] edges=getEdges();
        Arrays.sort( edges );
        System.out.println(Arrays.toString(edges));
        for (int i=0; i < edgeNum; i++) {
            int p1=getPosition( edges[i].start );
            int p2=getPosition( edges[i].end );

            int e1=getEnd( ends, p1 );
            int e2=getEnd( ends, p2 );

            if (e1 != e2) {
                ends[e1]=e2;
                rets[index++]=edges[i];
            }
        }

        for (int i=0; i <index; i++) {

            System.out.println(rets[i] );
        }
    }

    //给一个顶点，返回其索引
    public int getPosition(char ch) {
        for (int i=0; i < vertexs.length; i++) {
            if (ch == vertexs[i])
                return i;
        }
        return -1;
    }

    private EData[] getEdges( ) {
        EData[] edges=new EData[edgeNum];
        for (int i=0, index=0; i < vertexs.length; i++) {
            for (int j=i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF)
                    edges[index++]=new EData( vertexs[i], vertexs[j], matrix[i][j] );
            }
        }
        return edges;
    }

    //核心。获得某个顶点对应的终点的下标
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0)
            i=ends[i];
        return i;
    }
}

//边类
class EData implements Comparable<EData> {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start=start;
        this.end=end;
        this.weight=weight;
    }

    @Override
    public String toString( ) {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                "}";
    }

    @Override
    public int compareTo(EData eData) {
        return this.weight - eData.weight;
    }
}