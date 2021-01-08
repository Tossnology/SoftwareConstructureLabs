package P3;

import java.util.ArrayList;

/**
 * 棋手动作接口
 * 
 * @author yRXX
 *
 */
public interface Action {
	
	/**
	 * 落子(针对围棋)
	 * 
	 * @param player 执行动作的棋手
	 * @param piece 将被放入棋盘的棋子对象
	 * @param x 落子位置的横坐标
	 * @param y 落子位置的纵坐标
	 * @return 是否落子成功
	 */
	public boolean putPiece(Player player, Piece piece, int x, int y);
	
	/**
	 * 提子(针对围棋)
	 * 
	 * @param player 执行动作的棋手
	 * @param x 提子位置的横坐标
	 * @param y 提子位置的纵坐标
	 * @return 是否提子成功
	 */
	public boolean takePiece(Player player, int x, int y);
	
	/**
	 * 移动棋子(针对国际象棋)
	 * 
	 * @param player 执行动作的棋手
	 * @param x1 起点横坐标
	 * @param y1 起点纵坐标
	 * @param x2 终点横坐标
	 * @param y2 终点纵坐标
	 * @return 是否移动成功
	 */
	public boolean movePiece(Player player, int x1, int y1, int x2, int y2);
	
	/**
	 * 吃子(针对国际象棋)
	 * 
	 * @param player 执行动作的棋手
	 * @param x1 所选棋子的横坐标
	 * @param y1 所选棋子的纵坐标
	 * @param x2 被吃棋子的横坐标
	 * @param y2 被吃棋子的纵坐标
	 * @return 是否吃子成功
	 */
	public boolean eatPiece(Player player, int x1, int y1, int x2, int y2);
	
	/**
	 * 获取走棋历史
	 * 
	 * @return 走棋历史记录表
	 */
	public ArrayList<String> getHistory();
	
	/**
	 * 查询位置
	 * 
	 * @param x 查询位置的横坐标
	 * @param y 查询位置的纵坐标
	 * @return 所查位置信息
	 */
	public String queryPosition(int x,int y);
}
