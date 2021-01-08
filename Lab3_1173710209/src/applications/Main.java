package applications;

import java.util.Scanner;

public class Main{
	
	private Scanner in;
	
	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}

	public void start() {
		System.out.println("1.StellarSystem");
		System.out.println("2.AtomStructure");
		System.out.println("3.SocialNetworkCircle");
		System.out.println("输入end退出");
		
		int choice;

		while (true) {
			System.out.println("请输入:");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("[1-3]")) {
				choice = Integer.valueOf(s);
				break;
			}else if(s.equals("end")){
				System.out.println("谢谢使用");
				System.exit(0);
			}else {
				System.out.println("非法输入(请输入1,2,3,end)");
			}
		}
		
		switch(choice) {
			case 1:{
				StellarSystem.main(null);
				break;
			}
			
			case 2:{
				AtomStructure.main(null);
				break;
			}
			
			case 3:{
				SocialNetworkCircle.main(null);
				break;
			}
		}
	}
}
