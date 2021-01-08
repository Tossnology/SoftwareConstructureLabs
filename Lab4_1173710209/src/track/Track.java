package track;

/**
 * 一个轨道，适用于所有环形轨道系统
 * immutable
 * @author yRXX
 *
 */
public class Track {
	
	private double radius;
	
	/*
	 * Abstraction function:
	 *   AF(radius)=一半径为radius的轨道
	 * Representation invariant:
	 *   radius:半径，double类型，大于0
	 * Safety from rep exposure:
	 *   所有属性都是private
	 */
	
	public void checkRep() {
		assert radius>=-1 : "半径必须大于0";
	}
	
	/**
	 * 构造器，生成半径为radius的轨道
	 * @param radius 轨道半径，必须大于0
	 */
	public Track(double radius) {
		this.radius = radius;
		checkRep();
	}
	
	/**
	 * 获取轨道半径
	 * @return 该轨道的半径
	 */
	public double getRadius() {
		return radius;
	}
	
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
		
		Track other = (Track) obj;
		
		return this.radius==other.radius;
	}
	
	@Override
	public int hashCode() {
		long f = Double.doubleToLongBits(radius);
		int result = (int)(f^(f>>>32));
		return result;
	}
}