package P3;

import java.util.ArrayList;
/**
 * 象棋棋手动作，实现棋手动作接口
 * @author yRXX
 *
 */
public class ChessAction implements Action{

	private ChessBoard board;
	private ArrayList<String> History;
	
	// Abstraction function:
	//  AF(board,History)=围棋棋手动作
	// Representation invariant:
	//  board:象棋棋盘
	//  History:走棋历史，类型为ArrayList<String>;
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * 象棋棋手动作构造器
	 * @param board 象棋棋盘
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
			System.out.println("不能出界");
			return false;
		}
		
		Piece p1 = board.getPiece(source);
		Piece p2 = board.getPiece(target);
		
		if(p1==null) {
			System.out.println("没有可移动棋子");
			return false;
		}
		
		if(!p1.getColor().equals(player.getColor())) {
			System.out.println("不是己方棋子");
			return false;
		}
		
		if(p2!=null) {
			System.out.println("目标位置已有棋子");
			return false;
		}
		
		boolean b1 = board.removePiece(source);
		boolean b2 = board.addPiece(target, p1);
		
		if(b1&&b2) {
			System.out.println("玩家"+player.getName()+"将("+source.getX()+","+source.getY()+")处的"+p1.getName()+"移动到了("+target.getX()+","+target.getY()+")");
			History.add("玩家"+player.getName()+"将("+source.getX()+","+source.getY()+")处的"+p1.getName()+"移动到了("+target.getX()+","+target.getY()+")");
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
			System.out.println("没有可移动棋子");
			return false;
		}
		
		if(!p1.getColor().equals(player.getColor())) {
			System.out.println("移动的不是己方棋子");
			return false;
		}
		
		if(p2==null) {
			System.out.println("没有可吃棋子");
			return false;
		}
		
		if(p2.getColor().equals(player.getColor())) {
			System.out.println("吃掉的不是对方棋子");
			return false;
		}
		
		boolean b1 = board.removePiece(source);
		boolean b2 = board.removePiece(target);
		boolean b3 = board.addPiece(target, p1);
		
		if(b1&&b2&&b3) {
			System.out.println("玩家"+player.getName()+"用("+source.getX()+","+source.getY()+")处的"+p1.getName()+"吃掉了对方("+target.getX()+","+target.getY()+")处的"+p2.getName());
			History.add("玩家"+player.getName()+"用("+source.getX()+","+source.getY()+")处的"+p1.getName()+"吃掉了对方("+target.getX()+","+target.getY()+")处的"+p2.getName());
		}
		
		return b1&&b2&&b3;
	}

	@Override
	public boolean putPiece(Player player, Piece piece, int x, int y) {
		throw new RuntimeException("象棋中无此规则");
	}

	@Override
	public boolean takePiece(Player player, int x, int y) {
		throw new RuntimeException("象棋中无此规则");
	}

	@Override
	public ArrayList<String> getHistory() {
		return History;
	}

	@Override
	public String queryPosition(int x, int y) {
		Position position = new Position(x,y);
		if(board.getPiece(position)==null) {
			return "此处无棋子";
		}else {
			return position.toString()+" "+board.getPiece(position).getName()+" "+board.getPiece(position).getColor();
		}
	}

}
