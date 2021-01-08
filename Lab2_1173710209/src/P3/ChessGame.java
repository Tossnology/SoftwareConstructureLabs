package P3;

import java.util.Scanner;
/**
 * 象棋游戏，实现游戏接口
 * @author yRXX
 *
 */
public class ChessGame implements Game{

	private ChessBoard board;
	private Player player1;
	private Player player2;
	private ChessAction Do;
	private Scanner in;
	
	// Abstraction function:
	//  AF(board,player1,player2,Do,in)=一局象棋游戏
	// Representation invariant:
	//  board:象棋棋盘
	//  player1:白色方棋手
	//  player2:黑色方棋手
	//  Do:象棋棋手动作
	//  in:输入
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * 象棋游戏构造器，初始化象棋游戏
	 * @param p1 白色方棋手
	 * @param p2 黑色方棋手
	 */
	public ChessGame(Player p1, Player p2) {
		board = new ChessBoard();
		player1 = p1;
		player2 = p2;
		Do = new ChessAction(board);
		new PieceFactory();
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
		System.out.println("1.移动棋子");
		System.out.println("2.吃子");
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
			System.out.println("请输入起点坐标");
			Position p1 = inputPosition();
			System.out.println("请输入终点坐标");
			Position p2 = inputPosition();

			while(!Do.movePiece(player, p1.getX(), p1.getY(), p2.getX(), p2.getY())) {
				System.out.println("请输入起点坐标");
				p1 = inputPosition();
				System.out.println("请输入终点坐标");
				p2 = inputPosition();
			}
			break;
		}

		case 2: {
			System.out.println("请输入起点坐标");
			Position p1 = inputPosition();
			System.out.println("请输入终点坐标");
			Position p2 = inputPosition();

			while(!Do.eatPiece(player, p1.getX(), p1.getY(), p2.getX(), p2.getY())) {
				System.out.println("请输入起点坐标");
				p1 = inputPosition();
				System.out.println("请输入终点坐标");
				p2 = inputPosition();
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
			System.out.println("请输入横坐标(0~7):");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("-?[0-9]\\d*")) {
				if (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 7) {
					x = Integer.valueOf(s);
					break;
				} else {
					System.out.println("非法输入(请输入0~7)");
				}
			} else {
				System.out.println("非法输入(请输入0~7)");
			}
		}

		while (true) {
			System.out.println("请输入纵坐标(0~7):");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("-?[0-9]\\d*")) {
				if (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 7) {
					y = Integer.valueOf(s);
					break;
				} else {
					System.out.println("非法输入(请输入0~7)");
				}
			} else {
				System.out.println("非法输入(请输入0~7)");
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
