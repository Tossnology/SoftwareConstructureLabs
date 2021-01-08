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
			//第一行
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
			//其他行
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
			//行列数是否相等
			if (columnNum != rowNum) {
				return false;
			}
			//是否为空
			if (columnNum == 0) {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} // 检测是否为格式正确的矩阵

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
		} // 将文件中的内容导入二维数组matrix

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
		} // 检验每一行的和

		for (int j = 0; j < sideLength; j++) {
			int sum = 0;
			for (int i = 0; i < sideLength; i++) {
				sum += matrix[i][j];
			}
			if (sum != constant) {
				return false;
			}
		} // 检验每一列的和

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
		} // 检验两条对角线的和
		if (sum1 != constant || sum2 != constant) {
			return false;
		}
		return true;
	}

	public static boolean generateMagicSquare(int n) {

		if (n < 0 || (n % 2) == 0) {
			System.out.println("非法输入！");
			return false;
		}

		int magic[][] = new int[n][n];//定义n*n矩阵
		int row = 0, col = n / 2, i, j, square = n * n;//初始化行数为0，列数为n/2，元素总数n*n

		//为矩阵赋值
		/*
		 * 数字1放在首行最中间的格子中
		 * 向右上角斜行，依次填入数字
		 * 如果右上方向出了上边界，就以出框后的虚拟方格位置为基准，将数字竖直降落至底行对应的格子中
		 * 同上，向右出了边界，就以出框后的虚拟方格位置为基准，将数字平移至最左列对应的格子中
		 * 如果数字n右上的格子已被其它数字占领，就将n+1 填写在n下面的格子中
		 * 如果朝右上角出界，和上面重复的情况做同样处理
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
