package P3;

import java.util.Scanner;
/**
 * Χ����Ϸ��ʵ����Ϸ�ӿ�
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
	//  AF(board,player1,player2,Do,f2,in)=һ��Χ����Ϸ
	// Representation invariant:
	//  board:Χ������
	//  player1:��ɫ������
	//  player2:��ɫ������
	//  Do:Χ�����ֶ���
	//  f2:���ӹ���
	//  in:����
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * Χ����Ϸ����������ʼ��Χ����Ϸ
	 * @param p1 ��ɫ������
	 * @param p2 ��ɫ������
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
		System.out.println("����" + player.getName() + "�Ļغ�");
		System.out.println("1.����");
		System.out.println("2.����");
		System.out.println("3.��ѯλ��");
		System.out.println("4.����˫����������");
		System.out.println("5.�����غ�");
		System.out.println("����end������Ϸ���鿴������ʷ");

		int choice;

		while (true) {
			System.out.println("������:");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("[1-5]")) {
				choice = Integer.valueOf(s);
				break;
			}else if(s.equals("end")){
				System.out.println("��Ϸ����");
				showHistory();
				System.exit(0);
			}else {
				System.out.println("�Ƿ�����(������1,2,3,4,5,end)");
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
			System.out.println("��ɫ����������:" + board.getWhiteNum());
			System.out.println("��ɫ����������:" + board.getBlackNum());
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
			System.out.println("�����������(0~18):");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("-?[0-9]\\d*")) {
				if (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 18) {
					x = Integer.valueOf(s);
					break;
				} else {
					System.out.println("�Ƿ�����(������0~18)");
				}
			} else {
				System.out.println("�Ƿ�����(������0~18)");
			}
		}

		while (true) {
			System.out.println("������������(0~18):");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("-?[0-9]\\d*")) {
				if (Integer.valueOf(s) >= 0 && Integer.valueOf(s) <= 18) {
					y = Integer.valueOf(s);
					break;
				} else {
					System.out.println("�Ƿ�����(������0~18)");
				}
			} else {
				System.out.println("�Ƿ�����(������0~18)");
			}
		}
		
		return new Position(x,y);
	}
	
	/**
	 * ���rep
	 */
	public void checkRep() {
		assert player1.getColor().equals("white");
		assert player2.getColor().equals("black");
	}
}
