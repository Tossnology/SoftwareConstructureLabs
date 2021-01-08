package P3;

import java.util.HashMap;
import java.util.Map;
/**
 * 围棋棋盘，实现棋盘接口
 * @author yRXX
 *
 */
public class GoBoard implements Board{
	private Map<Position,Piece> status;
	// Abstraction function:
	//  AF(status)=一个围棋棋盘
	// Representation invariant:
	//  status:棋盘状态，类型为Map<Position,Piece>，其中Position范围为(0,0)~(18,18)，Piece的名字只能为go
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * 围棋棋盘构造器，初始化围棋棋盘
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
	 * 检查rep
	 */
	public void checkRep() {
		assert status.size()==361 : "棋盘上应该有361个点";
		assert status.keySet().size()==status.size() : "棋盘上位置不能重复";
		for(Position p : status.keySet()) {
			assert p.getX()>=0&&p.getX()<=18 : "横坐标0~18";
			assert p.getY()>=0&&p.getY()<=18 : "纵坐标0~18";
			assert ((status.get(p)!=null)&&(status.get(p).getName().equals("go")))||status.get(p)==null : "一个位置要么没有棋子，要么棋子是围棋";
		}
	}
}
