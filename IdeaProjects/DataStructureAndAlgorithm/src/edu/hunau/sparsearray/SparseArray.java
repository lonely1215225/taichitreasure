package edu.hunau.sparsearray;

import java.io.*;

public class SparseArray {

	public static void main(String[] args) throws IOException {
		sparseArrayCalc(5, 5, 5);
	}

	private static void sparseArrayCalc(int row, int col, int value) throws IOException {
		// ����11*11����
		int chessarray[][] = new int[11][11];
		chessarray[2][3] = 1;
		chessarray[3][4] = 2;
		chessarray[4][5] = 1;
		chessarray[row][col] = value;

		// �����е�������
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

		// ����ϡ�����ѹ����������
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
		System.out.println("chessarray��ϡ�����Ϊ��");
		// ����ϡ�����̵��ļ���
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
		bw.close();//�ر�֮ǰ�����ˢ�»�����
		fw.close();

		// ���ļ��ж�ȡϡ�����̲��ָ�����
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
		System.out.println("���ļ��ж�ȡϡ�����-->�ָ��������:");
		for (int chessarr[] : chessarray2) {
			for (int data : chessarr) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}

	}
}
