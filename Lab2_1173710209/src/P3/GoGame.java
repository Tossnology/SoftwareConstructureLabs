package P3;

import java.util.Scanner;
/**
 * 围棋游戏，实现游戏接口
 * mutable
 * @author yRXX
 *
 */
public class GoGame implements Game {

	private GoBoard board;
	private Player player1;
	private Player player2;
	private GoAction Do;
	private PieceFactory f2;
	private Scanner in;

	// Abstraction function:
	//  AF(board,player1,player2,Do,f2,in)=一局围棋游戏
	// Representation invariant:
	//  board:围棋棋盘
	//  player1:白色方棋手
	//  player2:黑色方棋手
	//  Do:围棋棋手动作
	//  f2:棋子工厂
	//  in:输入
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * 围棋游戏构造器，初始化围棋游戏
	 * @param p1 白色方棋手
	 * @param p2 黑色方棋手
	 */
	public GoGame(Player p1, Player p2) {
		board = new GoBoard();
		player1 = p1;
		player2 = p2;
		Do = new GoAction(board);
		f2 = new PieceFactory();
		Body();
	}

	@Override
	public void Body() {
		int count = 0;
		while (true) {
			if (count % 2 == 0) {
				Turn(player1);
				count++;
			} else {
				Turn(player2);
				count++;
			}
		}
	}

	@Override
	public void showHistory() {
		for (String r : Do.getHistory()) {
			System.out.println(r);
		}
	}

	@Override
	public void Turn(Player player) {
		System.out.println("到了" + player.getName() + "的回合");
		System.out.println("1.落子");
		System.out.println("2.提子");
		System.out.println("3.查询位置");
		System.out.println("4.计算双方棋子总数");
		System.out.println("5.跳过回合");
		System.out.println("输入end结束游戏并查看走棋历史");

		int choice;

		while (true) {
			System.out.println("请输入:");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("[1-5]")) {
				choice = Integer.valueOf(s);
				break;
			}else if(s.equals("end")){
				System.out.println("游戏结束");
				showHistory();
				System.exit(0);
			}else {
				System.out.println("非法输入(请输入1,2,3,4,5,end)");
			}
		}

		switch (choice) {
		case 1: {
			Position p = inputPosition();

			while(!Do.putPiece(player, f2.getGo(player.getColor()), p.getX(), p.getY())) {
				p = inputPosition();
			}
			
			break;
		}

		case 2: {
			Position p = inputPosition();

			while(!Do.takePiece(player, p.getX(), p.getY())) {
				p = inputPosition();
			}
			break;
		}

		case 3: {
			Position p = inputPosition();

			System.out.println(Do.queryPosition(p.getX(), p.getY()));
			break;
		}

		case 4: {
			System.out.println("白色方棋子总数:" + board.getWhiteNum());
			System.out.println("黑色方棋子总数:" + board.getBlackNum());
			break;
		}

		case 5: {
			break;
		}
		}
	}

	@Override
	public Position inputPosition() {
		
		int x,y;
		
		while (true) {
			System.out.println("请输入横坐标(0~18):");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("-?[0-9]\\d*")) {
				if (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 18) {
					x = Integer.valueOf(s);
					break;
				} else {
					System.out.println("非法输入(请输入0~18)");
				}
			} else {
				System.out.println("非法输入(请输入0~18)");
			}
		}

		while (true) {
			System.out.println("请输入纵坐标(0~18):");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("-?[0-9]\\d*")) {
				if (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 18) {
					y = Integer.valueOf(s);
					break;
				} else {
					System.out.println("非法输入(请输入0~18)");
				}
			} else {
				System.out.println("非法输入(请输入0~18)");
			}
		}
		
		return new Position(x,y);
	}
	
	/**
	 * 检查rep
	 */
	public void checkRep() {
		assert player1.getColor().equals("white");
		assert player2.getColor().equals("black");
	}
}
