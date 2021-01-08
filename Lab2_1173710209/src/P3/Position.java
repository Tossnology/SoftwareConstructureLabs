package P3;
/**
 * 位置，有横纵坐标
 * immutable
 * @author yRXX
 *
 */
public class Position {
	private int x;
	private int y;
	
	// Abstraction function:
	//  AF(x,y)=一个坐标点位置对象
	// Representation invariant:
	//  x:非空，自然数
	//	y:非空，自然数
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改
	
	/**
	 * 位置对象构造器
	 * 
	 * @param x 横坐标
	 * @param y 纵坐标
	 */
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	
	/**
	 * 获取横坐标
	 * 
	 * @return 横坐标值
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 获取纵坐标
	 * 
	 * @return 纵坐标值
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 重写toString方法
	 * 
	 * @return 位置的字符串表示形式(x,y)
	 */
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	/**
	 * 重写equals方法
	 * 判定条件为对应横纵坐标一致
	 * 
	 * @param obj 比较的对象
	 * @return 是否等价
	 */
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		
		if(obj==null) {
			return false;
		}
		
		if(getClass()!=obj.getClass()) {
			return false;
		}
		
		Position other = (Position) obj;
		
		return this.x==other.x&&this.y==other.y;
	}
	
	/**
	 * 重写HashCode
	 * 
	 * @return 横纵坐标之和
	 */
	@Override
	public int hashCode() {
		return x+y;
	}
	
	/**
	 * 检查rep
	 */
	public void checkRep() {
		assert x>=0&&y>=0 : "横纵坐标必须为自然数";
	}
}
