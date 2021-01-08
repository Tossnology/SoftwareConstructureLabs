package P3;

import java.util.Scanner;
/**
 * 主程序
 * @author yRXX
 *
 */
public class MyChessAndGoGame {
	
	private Scanner in;
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		MyChessAndGoGame game = new MyChessAndGoGame();
		game.Start();
	}
	
	/**
	 * 启动围棋游戏
	 */
	public void StartGo() {
		in = new Scanner(System.in);
		
		System.out.println("请输入玩家(白)");
		String s1 = in.nextLine();
		Player p1 = new Player(s1,"white");
		
		System.out.println("请输入玩家(黑)");
		String s2 = in.nextLine();
		Player p2 = new Player(s2,"black");
		
		new GoGame(p1,p2);
	}
	
	/**
	 * 启动象棋游戏
	 */
	public void StartChess() {
		in = new Scanner(System.in);
		
		System.out.println("请输入玩家(白)");
		String s1 = in.nextLine();
		Player p1 = new Player(s1,"white");
		
		System.out.println("请输入玩家(黑)");
		String s2 = in.nextLine();
		Player p2 = new Player(s2,"black");
		
		new ChessGame(p1,p2);
	}
	
	/**
	 * 启动
	 */
	public void Start() {
		System.out.println("1.围棋");
		System.out.println("2.国际象棋");
		System.out.println("输入end退出");
		
		int choice;

		while (true) {
			System.out.println("请输入:");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("[1-2]")) {
				choice = Integer.valueOf(s);
				break;
			}else if(s.equals("end")){
				System.out.println("游戏结束");
				System.exit(0);
			}else {
				System.out.println("非法输入(请输入1,2,end)");
			}
		}
		
		switch(choice) {
			case 1:{
				StartGo();
				break;
			}
			
			case 2:{
				StartChess();
				break;
			}
		}
	}
}
