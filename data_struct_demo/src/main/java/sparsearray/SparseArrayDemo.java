package sparsearray;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class SparseArrayDemo {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int[][] chessArr1 = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
		// 输出原始数组
		System.out.println("输出原始数组");
		for (int[] row : chessArr1) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		// 遍历原始数组，找出有效值个数
		int sum = 0;
		for (int[] row : chessArr1) {
			for (int data : row) {
				if (data != 0) {
					sum++;
				}
			}
		}
		// 创建稀疏数组
		int[][] sparseArr = new int[sum + 1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		int count = 0;
		// 遍历原始数组并且赋给稀疏数组
		for (int i = 1; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];
				}
			}
		}
		// 输出稀疏数组
		System.out.println("输出稀疏数组");
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}

		//稀疏数组写入磁盘
		OutputStream os = new FileOutputStream("F:/output.txt");
		PrintWriter pw = new PrintWriter(os);
		for (int i = 0; i < sparseArr.length; i++) {
			pw.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		pw.close();
		os.close();
		//从磁盘上读取稀疏数组
		System.out.println("从磁盘上读取稀疏数组");
		BufferedReader buf = new BufferedReader(new FileReader("F:/output.txt"));
		String line = null;
		while ((line=buf.readLine()) != null) {
			String[] sparseArr2 = line.split("\t\n");
			line = buf.readLine();
			System.out.print(sparseArr2[0]);
			System.out.println();
		}
		buf.close();
		//稀疏数组恢复成数组
		System.out.println("稀疏数组恢复成数组");
		int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		for(int[] row : chessArr2 ) {
			for (int data : row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}

		sc.close();
	}

}
