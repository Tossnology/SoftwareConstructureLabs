package P3;

import java.util.ArrayList;
/**
 * 围棋棋手动作，实现棋手动作接口
 * @author yRXX
 *
 */
public class GoAction implements Action{
	private GoBoard board;
	private ArrayList<String> History;
	
	// Abstraction function:
	//  AF(board,History)=围棋棋手动作
	// Representation invariant:
	//  board:围棋棋盘
	//  History:走棋历史，类型为ArrayList<String>;
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * 棋手动作构造器
	 * @param board 棋盘
	 */
	public GoAction(GoBoard board) {
		this.board = board;
		History = new ArrayList<String>();
	}
	
	@Override
	public boolean putPiece(Player player, Piece piece, int x, int y) {
		if(!piece.getName().equals("go")) {
			System.out.println("不是围棋");
			return false;
		}
		
		Position p = new Position(x, y);
		if(piece.getColor().equals(player.getColor())) {
			if(board.addPiece(p, piece)) {
				System.out.println("玩家"+player.getName()+"在("+p.getX()+","+p.getY()+")处落子");
				History.add("玩家"+player.getName()+"在("+p.getX()+","+p.getY()+")处落子");
				return true;
			}else {
				System.out.println("此处已有棋子");
				return false;
			}
		}else {
			System.out.println("不是己方棋子");
			return false;
		}
	}

	@Override
	public boolean takePiece(Player player, int x, int y) {
		Position p = new Position(x, y);
		if(board.getPiece(p)==null) {
			System.out.println("此处没有棋子");
			return false;
		}
		
		if(!board.getPiece(p).getColor().equals(player.getColor())) {
			if(board.removePiece(p)) {
				System.out.println("玩家"+player.getName()+"在("+p.getX()+","+p.getY()+")处提子");
				History.add("玩家"+player.getName()+"在("+p.getX()+","+p.getY()+")处提子");
				return true;
			}else {
				System.out.println("此处没有棋子");
				return false;
			}
		}else {
			System.out.println("不是对方棋子");
			return false;
		}
	}

	@Override
	public boolean movePiece(Player player, int x1, int y1, int x2, int y2) {
		throw new RuntimeException("围棋中无此规则");
	}

	@Override
	public boolean eatPiece(Player player, int x1, int y1, int x2, int y2) {
		throw new RuntimeException("围棋中无此规则");
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
