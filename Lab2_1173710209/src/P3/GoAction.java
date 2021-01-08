package P3;

import java.util.ArrayList;
/**
 * Χ�����ֶ�����ʵ�����ֶ����ӿ�
 * @author yRXX
 *
 */
public class GoAction implements Action{
	private GoBoard board;
	private ArrayList<String> History;
	
	// Abstraction function:
	//  AF(board,History)=Χ�����ֶ���
	// Representation invariant:
	//  board:Χ������
	//  History:������ʷ������ΪArrayList<String>;
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * ���ֶ���������
	 * @param board ����
	 */
	public GoAction(GoBoard board) {
		this.board = board;
		History = new ArrayList<String>();
	}
	
	@Override
	public boolean putPiece(Player player, Piece piece, int x, int y) {
		if(!piece.getName().equals("go")) {
			System.out.println("����Χ��");
			return false;
		}
		
		Position p = new Position(x, y);
		if(piece.getColor().equals(player.getColor())) {
			if(board.addPiece(p, piece)) {
				System.out.println("���"+player.getName()+"��("+p.getX()+","+p.getY()+")������");
				History.add("���"+player.getName()+"��("+p.getX()+","+p.getY()+")������");
				return true;
			}else {
				System.out.println("�˴���������");
				return false;
			}
		}else {
			System.out.println("���Ǽ�������");
			return false;
		}
	}

	@Override
	public boolean takePiece(Player player, int x, int y) {
		Position p = new Position(x, y);
		if(board.getPiece(p)==null) {
			System.out.println("�˴�û������");
			return false;
		}
		
		if(!board.getPiece(p).getColor().equals(player.getColor())) {
			if(board.removePiece(p)) {
				System.out.println("���"+player.getName()+"��("+p.getX()+","+p.getY()+")������");
				History.add("���"+player.getName()+"��("+p.getX()+","+p.getY()+")������");
				return true;
			}else {
				System.out.println("�˴�û������");
				return false;
			}
		}else {
			System.out.println("���ǶԷ�����");
			return false;
		}
	}

	@Override
	public boolean movePiece(Player player, int x1, int y1, int x2, int y2) {
		throw new RuntimeException("Χ�����޴˹���");
	}

	@Override
	public boolean eatPiece(Player player, int x1, int y1, int x2, int y2) {
		throw new RuntimeException("Χ�����޴˹���");
	}

	@Override
	public ArrayList<String> getHistory() {
		return History;
	}

	@Override
	public String queryPosition(int x, int y) {
		Position position = new Position(x,y);
		if(board.getPiece(position)==null) {
			return "�˴�������";
		}else {
			return position.toString()+" "+board.getPiece(position).getName()+" "+board.getPiece(position).getColor();
		}
	}
}
