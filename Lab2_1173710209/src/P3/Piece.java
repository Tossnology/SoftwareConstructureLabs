package P3;

import java.util.Arrays;

/**
 * 棋子，有名字和颜色
 * immutable
 * @author yRXX
 *
 */
public class Piece {
	private String name;
	private String color;
	
	// Abstraction function:
	//  AF(name,color)=一个具有名字和颜色的棋子对象
	// Representation invariant:
	//  name:{go,pawn,rook,knight,bishop,king,queen}
	//	color:{black,white}
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * Piece对象构造器
	 * 
	 * @param name 棋子名字
	 * @param color 棋子颜色
	 */
	public Piece(String name,String color) {
		this.name = name;
		this.color = color;
		checkRep();
	}
	
	/**
	 * 获取名字
	 * 
	 * @return 棋子名字
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 获取颜色
	 * 
	 * @return 棋子颜色
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * 检查rep
	 * 
	 */
	public void checkRep() {
		assert name!=null : "棋名不能为空";
		assert color!=null : "棋的颜色不能为空";
		assert Arrays.asList("go","pawn","rook","knight","bishop","king","queen").contains(name) : "没有这种棋";
		assert color.equals("white")||color.equals("black") : "没有这种颜色";
	}
}
