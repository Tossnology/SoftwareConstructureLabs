package P3;
/**
 * 游戏接口
 * 
 * @author yRXX
 *
 */
public interface Game {
	
	/**
	 * 游戏主体
	 */
	public void Body();
	
	/**
	 * 输出走棋历史
	 */
	public void showHistory();
	
	/**
	 * 回合
	 * 
	 * @param player 该回合行动的棋手
	 */
	public void Turn(Player player);
	
	/**
	 * 输入坐标
	 * 
	 * @return 输入的坐标
	 */
	public Position inputPosition();
}
