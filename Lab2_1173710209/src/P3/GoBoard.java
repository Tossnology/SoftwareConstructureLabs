package P3;

import java.util.HashMap;
import java.util.Map;
/**
 * Χ�����̣�ʵ�����̽ӿ�
 * @author yRXX
 *
 */
public class GoBoard implements Board{
	private Map<Position,Piece> status;
	// Abstraction function:
	//  AF(status)=һ��Χ������
	// Representation invariant:
	//  status:����״̬������ΪMap<Position,Piece>������Position��ΧΪ(0,0)~(18,18)��Piece������ֻ��Ϊgo
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * Χ�����̹���������ʼ��Χ������
	 */
	public GoBoard() {
		status = new HashMap<Position,Piece>();
		
		for(int y = 0;y<19;y++) {
			for(int x = 0;x<19;x++) {
				status.put(new Position(x, y), null);
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
		if(piece==null) {
			return false;
		}
		
		if(!piece.getName().equals("go")) {
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
		assert status.size()==361 : "������Ӧ����361����";
		assert status.keySet().size()==status.size() : "������λ�ò����ظ�";
		for(Position p : status.keySet()) {
			assert p.getX()>=0&&p.getX()<=18 : "������0~18";
			assert p.getY()>=0&&p.getY()<=18 : "������0~18";
			assert ((status.get(p)!=null)&&(status.get(p).getName().equals("go")))||status.get(p)==null : "һ��λ��Ҫôû�����ӣ�Ҫô������Χ��";
		}
	}
}
