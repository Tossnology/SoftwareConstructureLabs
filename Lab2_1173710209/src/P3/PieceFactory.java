package P3;
/**
 * 棋子工厂
 * @author yRXX
 *
 */
public class PieceFactory {

	/**
	 * 造一个围棋
	 * 
	 * @param color 棋子颜色
	 * @return 一个新的围棋对象
	 */
	public Piece getGo(String color) {
		return new Piece("go",color);
	}
	
	/**
	 * 造一个国王棋
	 * @param color 棋子颜色
	 * @return 一个新的国王棋对象
	 */
	public Piece getKing(String color) {
		return new Piece("king", color);
	}
	
	/**
	 * 造一个皇后棋
	 * @param color 棋子颜色
	 * @return 一个新的皇后棋对象
	 */
	public Piece getQueen(String color) {
		return new Piece("queen", color);
	}
	
	/**
	 * 造一个堡垒棋
	 * @param color 棋子颜色
	 * @return 一个新的堡垒棋对象
	 */
	public Piece getRook(String color) {
		return new Piece("rook", color);
	}
	
	/**
	 * 造一个主教棋
	 * @param color 棋子颜色
	 * @return 一个新的主教棋对象
	 */
	public Piece getBishop(String color) {
		return new Piece("bishop", color);
	}
	
	/**
	 * 造一个骑士棋
	 * @param color 棋子颜色
	 * @return 一个新的骑士棋对象
	 */
	public Piece getKnight(String color) {
		return new Piece("knight", color);
	}
	
	/**
	 * 造一个士兵棋
	 * @param color 棋子颜色
	 * @return 一个新的士兵棋对象
	 */
	public Piece getPawn(String color) {
		return new Piece("pawn", color);
	}
}
