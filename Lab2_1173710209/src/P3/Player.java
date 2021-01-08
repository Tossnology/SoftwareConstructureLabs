package P3;

/**
 * 棋手，有名字和颜色
 * immutable
 * @author yRXX
 *
 */
public class Player {
	private String name;
	private String color;
	
	// Abstraction function:
	//  AF(name,color)=一个具有名字和颜色的棋手对象
	// Representation invariant:
	//  name:非空
	//	color:{black,white}
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	/**
	 * 棋手对象构造器
	 * 
	 * @param name 棋手名字
	 * @param color 棋手颜色
	 */
	public Player(String name,String color) {
		this.name = name;
		this.color = color;
		checkRep();
	}
	
	/**
	 * 获取名字
	 * 
	 * @return 棋手名字
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 获取颜色
	 * 
	 * @return 棋手颜色
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * 检查rep
	 */
	public void checkRep() {
		assert name!=null : "棋手名字不能为空";
		assert color!=null : "棋手不能没有颜色";
		assert color.equals("white")||color.equals("black") : "棋手颜色只能是黑和白";
	}
}
