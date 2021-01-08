package P3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * �������̣�ʵ�����̽ӿ�
 * @author yRXX
 *
 */
public class ChessBoard implements Board{
	private Map<Position,Piece> status;
	// Abstraction function:
	//  AF(status)=һ����������
	// Representation invariant:
	//  status:����״̬������ΪMap<Position,Piece>������Position��ΧΪ(0,0)~(7,7)��Piece������Ϊ��������������
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * �������̹���������ʼ������
	 */
	public ChessBoard() {
		status = new HashMap<Position,Piece>();
		PieceFactory f2 = new PieceFactory();
		
		for(int y = 0;y<4;y++) {
			for(int x = 0;x<8;x++) {
				if((x==0&&y==0)||(x==7&&y==0)) {
					status.put(new Position(x, y), f2.getRook("white"));
					status.put(new Position(x, 7-y), f2.getRook("black"));
				}else if((x==1&&y==0)||(x==6&&y==0)) {
					status.put(new Position(x, y), f2.getKnight("white"));
					status.put(new Position(x, 7-y), f2.getKnight("black"));
				}else if((x==2&&y==0)||(x==5&&y==0)) {
					status.put(new Position(x, y), f2.getBishop("white"));
					status.put(new Position(x, 7-y), f2.getBishop("black"));
				}else if(x==3&&y==0) {
					status.put(new Position(x, y), f2.getQueen("white"));
					status.put(new Position(x, 7-y), f2.getQueen("black"));
				}else if(x==4&&y==0) {
					status.put(new Position(x, y), f2.getKing("white"));
					status.put(new Position(x, 7-y), f2.getKing("black"));
				}else if(y==1) {
					status.put(new Position(x, y), f2.getPawn("white"));
					status.put(new Position(x, 7-y), f2.getPawn("black"));
				}else if(y==2||y==3) {
					status.put(new Position(x, y), null);
					status.put(new Position(x, 7-y), null);
				}
			}
		}
		
		checkRep();
	}
	
	public int getBlackNum() {
		int result = 0;
		for(Position p:status.keySet()) {
			if(status.get(p)!=null&&status.get(p).getColor().equals("black")) {
				result++;
			}
		}
		return result;
	}
	
	public int getWhiteNum() {
		int result = 0;
		for(Position p:status.keySet()) {
			if(status.get(p)!=null&&status.get(p).getColor().equals("white")) {
				result++;
			}
		}
		return result;
	}
	
	public boolean addPiece(Position position,Piece piece) {
		if(piece.getColor().equals("white")&&getWhiteNum()+1>16) {
			return false;
		}
		
		if(piece.getColor().equals("black")&&getBlackNum()+1>16) {
			return false;
		}
		
		if(status.keySet().contains(position)&&status.get(position)==null) {
			status.put(position, piece);
			checkRep();
			return true;
		}else {
			checkRep();
			return false;
		}
	}
	
	public boolean removePiece(Position position) {
		if(status.containsKey(position)&&status.get(position)!=null) {
			status.put(position, null);
			checkRep();
			return true;
		}else {
			checkRep();
			return false;
		}
	}
	
	public Piece getPiece(Position position) {
		if(status.containsKey(position)&&status.get(position)!=null) {
			return status.get(position);
		}else {
			return null;
		}
	}
	
	/**
	 * ���rep
	 */
	public void checkRep() {
		assert status.size()==64 : "������Ӧ����64������";
		assert status.keySet().size()==status.size() : "������λ�ò����ظ�";
		for(Position p : status.keySet()) {
			assert p.getX()>=0&&p.getX()<=7 : "������0~7";
			assert p.getY()>=0&&p.getY()<=7 : "������0~7";
			assert (status.get(p)!=null&&Arrays.asList("pawn","rook","knight","bishop","king","queen").contains(status.get(p).getName()))||status.get(p)==null : "һ��λ��Ҫôû�����ӣ�Ҫô�ǹ�������";
		}
		assert getBlackNum()<=16&&getBlackNum()>=0&&getWhiteNum()<=16&&getWhiteNum()>=0 : "˫��������������������0~16��";
	}
}
