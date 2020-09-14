package edu.hunau.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private int edge[][];
    private List<String> vertexList;
    private int numOfEdge;
    //定义数组boolean[]，记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        Graph graph=new Graph( 8 );
        String vertexValue[]={"1", "2", "3", "4", "5","6","7","8"};
        for (String value : vertexValue)
            graph.insertVertex( value );

        graph.insertEdge( 0, 1, 1 );
        graph.insertEdge( 0, 2, 1 );
        graph.insertEdge( 1, 4, 1 );
        graph.insertEdge( 1, 3, 1 );
        graph.insertEdge( 3, 7, 1 );
        graph.insertEdge( 4, 7, 1 );
        graph.insertEdge( 2, 5, 1 );
        graph.insertEdge( 2, 6, 1 );
        graph.insertEdge( 5, 6, 1 );

        graph.showGraph();

       graph.dfs();
        //graph.bfs();

//        Class<Graph> graphClass=Graph.class;
//        Class<? extends Graph> aClass=graph.getClass();
//        try {
//            Class<?> aClass1=Class.forName( "edu.hunau.ReadWriteLock" );
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public Graph(int n) {
        edge=new int[n][n];
        vertexList=new ArrayList<>( n );
        numOfEdge=0;
        isVisited=new boolean[n];
    }

    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for (int i=0; i < vertexList.size(); i++) {
            if (edge[index][i] == 1)
                return i;
        }
        return -1;

    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int i=v2 + 1; i < vertexList.size(); i++) {
            if (edge[v1][i] == 1)
                return i;
        }
        return -1;
    }

    private void dfs(boolean[] isVisited, int i) {
        isVisited[i]=true;
        System.out.print( getValueByIndex( i ) + "->" );
        int w=getFirstNeighbor( i );
        while (w != -1) {
            if (!isVisited[w])
                dfs( isVisited, w );
            w=getNextNeighbor( i, w );
        }
    }

    public void dfs( ) {
        for (int i=0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                dfs( isVisited, i );
        }
    }


    public void bfs(boolean[] isVisited, int i) {
        int u;
        int w;
        LinkedList<Integer> list=new LinkedList();
        isVisited[i]=true;
        System.out.print( getValueByIndex( i ) + "->" );
        list.addLast( i );
        while (!list.isEmpty()) {
            u=list.removeFirst();
            w=getFirstNeighbor(u);
            while (w!= -1) {
                if (!isVisited[w]) {
                    System.out.print( getValueByIndex( w ) + "->" );
                    isVisited[w]=true;
                    list.addLast( w );
                }
                w=getNextNeighbor( u,w );
            }

        }
    }
    public void bfs(){
        for (int i=0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                bfs(isVisited,i);
        }
    }

    public void showGraph( ) {
        for (int[] link : edge) {
            System.err.println( Arrays.toString( link ) );
        }
    }

    public int getNumOfVertex( ) {
        return vertexList.size();
    }

    public int getNumOfEdges( ) {
        return numOfEdge;
    }

    public String getValueByIndex(int i) {
        return vertexList.get( i );
    }

    public int getWeight(int v1, int v2) {
        return edge[v1][v2];
    }

    public void insertVertex(String vertex) {
        vertexList.add( vertex );
    }

    public void insertEdge(int v1, int v2, int weight) {
        edge[v1][v2]=weight;
        edge[v2][v1]=weight;
        numOfEdge++;
    }

}
