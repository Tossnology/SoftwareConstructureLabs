package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MagicSquare {

	boolean isLegalMagicSquare(String fileName) {
		String line;int rowNum = 0;int columnNum = 0;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
			//��һ��
			if ((line = in.readLine()) != null) {
				String s[] = line.split("\t");
				columnNum = s.length;
				rowNum++;
				for (int i = 0; i < s.length; i++) {
					for (int j = 0; j < s[i].length(); j++) {
						int chr = s[i].charAt(j);
						if (chr < 48 || chr > 57) {
							return false;
						}
					}
				}
			}
			//������
			while ((line = in.readLine()) != null) {
				String s[] = line.split("\t");
				if (s.length != columnNum) {
					return false;
				}
				rowNum++;
				for (int i = 0; i < s.length; i++) {
					for (int j = 0; j < s[i].length(); j++) {
						int chr = s[i].charAt(j);
						if (chr < 48 || chr > 57) {
							return false;
						}
					}
				}
			}
			//�������Ƿ����
			if (columnNum != rowNum) {
				return false;
			}
			//�Ƿ�Ϊ��
			if (columnNum == 0) {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} // ����Ƿ�Ϊ��ʽ��ȷ�ľ���

		int sideLength = columnNum;
		int matrix[][] = new int[sideLength][sideLength];

		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
			int i = 0;
			while ((line = in.readLine()) != null) {
				String s[] = line.split("\t");
				for (int j = 0; j < sideLength; j++) {
					matrix[i][j] = Integer.valueOf(s[j]);
				}
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} // ���ļ��е����ݵ����ά����matrix

		int constant = 0;
		for (int i = 0; i < sideLength; i++) {
			int sum = 0;
			for (int j = 0; j < sideLength; j++) {
				sum += matrix[i][j];
			}
			if (i == 0) {
				constant = sum;
			} else {
				if (sum != constant) {
					return false;
				}
			}
		} // ����ÿһ�еĺ�

		for (int j = 0; j < sideLength; j++) {
			int sum = 0;
			for (int i = 0; i < sideLength; i++) {
				sum += matrix[i][j];
			}
			if (sum != constant) {
				return false;
			}
		} // ����ÿһ�еĺ�

		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if (i == j) {
					sum1 += matrix[i][j];
				}
				if ((i + j) == (sideLength - 1)) {
					sum2 += matrix[i][j];
				}
			}
		} // ���������Խ��ߵĺ�
		if (sum1 != constant || sum2 != constant) {
			return false;
		}
		return true;
	}

	public static boolean generateMagicSquare(int n) {

		if (n < 0 || (n % 2) == 0) {
			System.out.println("�Ƿ����룡");
			return false;
		}

		int magic[][] = new int[n][n];//����n*n����
		int row = 0, col = n / 2, i, j, square = n * n;//��ʼ������Ϊ0������Ϊn/2��Ԫ������n*n

		//Ϊ����ֵ
		/*
		 * ����1�����������м�ĸ�����
		 * �����Ͻ�б�У�������������
		 * ������Ϸ�������ϱ߽磬���Գ��������ⷽ��λ��Ϊ��׼����������ֱ���������ж�Ӧ�ĸ�����
		 * ͬ�ϣ����ҳ��˱߽磬���Գ��������ⷽ��λ��Ϊ��׼��������ƽ���������ж�Ӧ�ĸ�����
		 * �������n���ϵĸ����ѱ���������ռ�죬�ͽ�n+1 ��д��n����ĸ�����
		 * ��������Ͻǳ��磬�������ظ��������ͬ������
		 */
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/P1/txt/6.txt")))) {
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					bw.write(magic[i][j] + "\t");
				bw.newLine();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MagicSquare test = new MagicSquare();
		System.out.println(test.isLegalMagicSquare("src/P1/txt/1.txt"));
		System.out.println(test.isLegalMagicSquare("src/P1/txt/2.txt"));
		System.out.println(test.isLegalMagicSquare("src/P1/txt/3.txt"));
		System.out.println(test.isLegalMagicSquare("src/P1/txt/4.txt"));
		System.out.println(test.isLegalMagicSquare("src/P1/txt/5.txt"));
		 test.generateMagicSquare(7);
		System.out.println(test.isLegalMagicSquare("src/P1/txt/6.txt"));
	}

}
