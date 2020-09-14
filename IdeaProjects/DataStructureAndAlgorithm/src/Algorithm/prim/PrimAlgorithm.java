package Algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] datas=new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs=datas.length;
        int[][] weight=new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        MGraph mGraph=new MGraph( vertexs );
        MinTree minTree=new MinTree();
        minTree.createGraph( mGraph, vertexs, datas, weight );
        minTree.showGraph( mGraph );
        minTree.prim( mGraph, 1 );
    }

}

//创建最小生成树
class MinTree {
    public void createGraph(MGraph graph, int vertexs, char datas[], int[][] weight) {
        int i, j;
        for (i=0; i < vertexs; i++) {
            graph.datas[i]=datas[i];
            for (j=0; j < vertexs; j++) {
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println( Arrays.toString( link ) );
        }
    }

    //prim算法，得到最小生成树
    public void prim(MGraph graph, int vertex) {
        int[] visited=new int[graph.vertexs];
        visited[vertex]=1;
        int v1=-1;
        int v2=-1;
        int minWeight;
        for (int n=1; n < graph.vertexs; n++) {
            minWeight=10000;
            for (int i=0; i < graph.vertexs; i++) {
                for (int j=0; j < graph.vertexs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight=graph.weight[i][j];
                        v1=i;
                        v2=j;
                    }
                }
            }
            System.out.println( graph.datas[v1] + "," + graph.datas[v2] + "权值:" + minWeight );
            visited[v2]=1;
        }
    }
}

class MGraph {
    int vertexs;
    char[] datas;
    int[][] weight;

    public MGraph(int vertexs) {
        this.vertexs=vertexs;
        datas=new char[vertexs];
        weight=new int[vertexs][vertexs];
    }
}