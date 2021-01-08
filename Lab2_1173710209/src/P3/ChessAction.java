package P3;

import java.util.ArrayList;
/**
 * �������ֶ�����ʵ�����ֶ����ӿ�
 * @author yRXX
 *
 */
public class ChessAction implements Action{

	private ChessBoard board;
	private ArrayList<String> History;
	
	// Abstraction function:
	//  AF(board,History)=Χ�����ֶ���
	// Representation invariant:
	//  board:��������
	//  History:������ʷ������ΪArrayList<String>;
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * �������ֶ���������
	 * @param board ��������
	 */
	public ChessAction(ChessBoard board) {
		this.board = board;
		History = new ArrayList<String>();
	}

	@Override
	public boolean movePiece(Player player, int x1, int y1, int x2, int y2) {
		Position source = new Position(x1, y1);
		Position target = new Position(x2, y2);
		
		if(!(target.getX()>=0&&target.getX()<=7&&target.getY()>=0&&target.getY()<=7)) {
			System.out.println("���ܳ���");
			return false;
		}
		
		Piece p1 = board.getPiece(source);
		Piece p2 = board.getPiece(target);
		
		if(p1==null) {
			System.out.println("û�п��ƶ�����");
			return false;
		}
		
		if(!p1.getColor().equals(player.getColor())) {
			System.out.println("���Ǽ�������");
			return false;
		}
		
		if(p2!=null) {
			System.out.println("Ŀ��λ����������");
			return false;
		}
		
		boolean b1 = board.removePiece(source);
		boolean b2 = board.addPiece(target, p1);
		
		if(b1&&b2) {
			System.out.println("���"+player.getName()+"��("+source.getX()+","+source.getY()+")����"+p1.getName()+"�ƶ�����("+target.getX()+","+target.getY()+")");
			History.add("���"+player.getName()+"��("+source.getX()+","+source.getY()+")����"+p1.getName()+"�ƶ�����("+target.getX()+","+target.getY()+")");
		}
		
		return b1&&b2;
	}

	@Override
	public boolean eatPiece(Player player, int x1, int y1, int x2, int y2) {
		Position source = new Position(x1, y1);
		Position target = new Position(x2, y2);
		Piece p1 = board.getPiece(source);
		Piece p2 = board.getPiece(target);
		
		if(p1==null) {
			System.out.println("û�п��ƶ�����");
			return false;
		}
		
		if(!p1.getColor().equals(player.getColor())) {
			System.out.println("�ƶ��Ĳ��Ǽ�������");
			return false;
		}
		
		if(p2==null) {
			System.out.println("û�пɳ�����");
			return false;
		}
		
		if(p2.getColor().equals(player.getColor())) {
			System.out.println("�Ե��Ĳ��ǶԷ�����");
			return false;
		}
		
		boolean b1 = board.removePiece(source);
		boolean b2 = board.removePiece(target);
		boolean b3 = board.addPiece(target, p1);
		
		if(b1&&b2&&b3) {
			System.out.println("���"+player.getName()+"��("+source.getX()+","+source.getY()+")����"+p1.getName()+"�Ե��˶Է�("+target.getX()+","+target.getY()+")����"+p2.getName());
			History.add("���"+player.getName()+"��("+source.getX()+","+source.getY()+")����"+p1.getName()+"�Ե��˶Է�("+target.getX()+","+target.getY()+")����"+p2.getName());
		}
		
		return b1&&b2&&b3;
	}

	@Override
	public boolean putPiece(Player player, Piece piece, int x, int y) {
		throw new RuntimeException("�������޴˹���");
	}

	@Override
	public boolean takePiece(Player player, int x, int y) {
		throw new RuntimeException("�������޴˹���");
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
