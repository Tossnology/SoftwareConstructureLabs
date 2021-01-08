package P3;

import java.util.Scanner;
/**
 * ������
 * @author yRXX
 *
 */
public class MyChessAndGoGame {
	
	private Scanner in;
	
	/**
	 * ������
	 * @param args
	 */
	public static void main(String[] args) {
		MyChessAndGoGame game = new MyChessAndGoGame();
		game.Start();
	}
	
	/**
	 * ����Χ����Ϸ
	 */
	public void StartGo() {
		in = new Scanner(System.in);
		
		System.out.println("���������(��)");
		String s1 = in.nextLine();
		Player p1 = new Player(s1,"white");
		
		System.out.println("���������(��)");
		String s2 = in.nextLine();
		Player p2 = new Player(s2,"black");
		
		new GoGame(p1,p2);
	}
	
	/**
	 * ����������Ϸ
	 */
	public void StartChess() {
		in = new Scanner(System.in);
		
		System.out.println("���������(��)");
		String s1 = in.nextLine();
		Player p1 = new Player(s1,"white");
		
		System.out.println("���������(��)");
		String s2 = in.nextLine();
		Player p2 = new Player(s2,"black");
		
		new ChessGame(p1,p2);
	}
	
	/**
	 * ����
	 */
	public void Start() {
		System.out.println("1.Χ��");
		System.out.println("2.��������");
		System.out.println("����end�˳�");
		
		int choice;

		while (true) {
			System.out.println("������:");
			in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.matches("[1-2]")) {
				choice = Integer.valueOf(s);
				break;
			}else if(s.equals("end")){
				System.out.println("��Ϸ����");
				System.exit(0);
			}else {
				System.out.println("�Ƿ�����(������1,2,end)");
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
