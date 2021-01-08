package P3;
/**
 * 棋盘接口，有一系列棋盘应具备的方法
 * 
 * @author yRXX
 *
 */
public interface Board {
	
	/**
	 * 获取黑棋数量
	 * 
	 * @return 黑棋数量
	 */
	public int getBlackNum();
	
	/**
	 * 获取白棋数量
	 * 
	 * @return 白棋数量
	 */
	public int getWhiteNum();
	
	/**
	 * 在棋盘上添加棋子
	 * 
	 * @param position 添加棋子的位置
	 * @param piece 棋子对象
	 * @return 是否添加成功
	 */
	public boolean addPiece(Position position,Piece piece);
	
	/**
	 * 在指定位置去掉棋子
	 * 
	 * @param position 指定的位置
	 * @return 是否去除成功
	 */
	public boolean removePiece(Position position);
	
	/**
	 * 获取指定位置的棋子
	 * 
	 * @param position 指定的位置
	 * @return 指定位置的棋子对象
	 */
	public Piece getPiece(Position position);
}
