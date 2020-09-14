package Algorithm.alitest;

import java.util.Scanner;

public class WordsSearch {
//    给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//    单词必须按照字母顺序，通过相邻的单元格内的字母构成，
//    其中“相邻”单元格是那些水平相邻或垂直相邻的单元格,
//    同一个单元格内的字母不允许被重复使用。

    public static void main(String[] args) {
        WordsSearch search = new WordsSearch();
        char[][] board={{'A','B','C','E'},
                        {'S','F','C','S'},
                        {'A','D','E','E'}
                        };
        Scanner scanner=new Scanner(System.in);
        String word = scanner.next();
        System.out.println(search.exist(board, word));
    }
    boolean[][] marked;
    int[][] direction = {{-1,0},{0,-1},{0,1},{1,0}};
    int m;
    int n;
    String word;
    char[][] board;

    public  boolean exist(char[][] board,String word){
        m=board.length;
        if (m==0) return false;
        n=board[0].length;
        marked=new boolean[m][n];
        this.word=word;
        this.board=board;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i,j,0)) return true;
            }
        }
        return false;
    }
    boolean dfs(int i,int j,int start){
        if (start==word.length()-1) return board[i][j]==word.charAt(start);
        if (board[i][j]==word.charAt(start)){
            marked[i][j]=true;
            for (int k = 0; k < 4; k++) {
                int x=i+direction[k][0];
                int y=j+direction[k][1];
                if (ranged(x,y)&& !marked[x][y]){
                    if (dfs(x,y,start+1)) return true;
                }
            }
            marked[i][j]=false;
        }
        return false;
    }
    boolean ranged(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
}
