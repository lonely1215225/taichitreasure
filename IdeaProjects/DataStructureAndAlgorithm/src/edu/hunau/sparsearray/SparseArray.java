package edu.hunau.sparsearray;

import java.io.*;

public class SparseArray {

	public static void main(String[] args) throws IOException {
		sparseArrayCalc(5, 5, 5);
	}

	private static void sparseArrayCalc(int row, int col, int value) throws IOException {
		// 创建11*11棋盘
		int chessarray[][] = new int[11][11];
		chessarray[2][3] = 1;
		chessarray[3][4] = 2;
		chessarray[4][5] = 1;
		chessarray[row][col] = value;

		// 棋盘中的棋子数
		int sum = 0;
		for (int chessarr[] : chessarray) {
			for (int data : chessarr) {
				System.out.printf("%d\t", data);
				if (data != 0) {
					sum++;
				}
			}
			System.out.println();
		}

		// 构建稀疏矩阵压缩保存棋盘
		int sparsearray[][] = new int[sum + 1][3];
		sparsearray[0][0] = chessarray.length;
		sparsearray[0][1] = chessarray.length;
		sparsearray[0][2] = sum;
		int count = 0;
		for (int i = 0; i < chessarray.length; i++) {
			for (int j = 0; j < chessarray.length; j++) {
				if (chessarray[i][j] != 0) {
					count++;
					sparsearray[count][0] = i;
					sparsearray[count][1] = j;
					sparsearray[count][2] = chessarray[i][j];
				}
			}
		}
		count = 0;
		System.out.println("chessarray的稀疏矩阵为：");
		// 保存稀疏棋盘到文件中
		FileWriter fw = new FileWriter("test.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int sparsearr[] : sparsearray) {
			System.out.printf("%d\t%d\t%d\t\n", sparsearr[0], sparsearr[1], sparsearr[2]);
			for (int data : sparsearr) {
				bw.write(String.valueOf(data) + "\t");
			}
			bw.newLine();
		}
//		bw.flush();
		bw.close();//关闭之前会进行刷新缓冲流
		fw.close();

		// 从文件中读取稀疏棋盘并恢复棋盘
		System.out.println("===================================");
		int chessarray2[][] = new int[sparsearray[0][0]][sparsearray[0][1]];
		FileReader fr = new FileReader("test.txt");
		BufferedReader br = new BufferedReader(fr);
		String str;
		String datas[] = new String[sum+1];
		while ((str = br.readLine()) != null) {
			//System.out.println(str);
			datas[count] = str;
			count++;		
		}
		br.close();
		fr.close();
		count = 0;
		String da[]=new String[3*(sum+1)];
		String d[];
		for (String data : datas) {
			d=data.split("\t");
			for(String s:d) {
				da[count]=s;
				count++;
			}
		}
		
		for(int i=3;i<da.length;i+=3) {
			chessarray2[Integer.parseInt(da[i])][Integer.parseInt(da[i+1])]=Integer.parseInt(da[i+2]);
		}
		
//		for (int i = 1; i < sparsearray.length; i++) {
//			chessarray2[sparsearray[i][0]][sparsearray[i][1]] = sparsearray[i][2];
//		}
		System.out.println("从文件中读取稀疏矩阵-->恢复后的棋盘:");
		for (int chessarr[] : chessarray2) {
			for (int data : chessarr) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

	}
}
