package track;

/**
 * һ����������������л��ι��ϵͳ
 * immutable
 * @author yRXX
 *
 */
public class Track {
	
	private double radius;
	
	/*
	 * Abstraction function:
	 *   AF(radius)=һ�뾶Ϊradius�Ĺ��
	 * Representation invariant:
	 *   radius:�뾶��double���ͣ�����0
	 * Safety from rep exposure:
	 *   �������Զ���private
	 */
	
	public void checkRep() {
		assert radius>=-1 : "�뾶�������0";
	}
	
	/**
	 * �����������ɰ뾶Ϊradius�Ĺ��
	 * @param radius ����뾶���������0
	 */
	public Track(double radius) {
		this.radius = radius;
		checkRep();
	}
	
	/**
	 * ��ȡ����뾶
	 * @return �ù���İ뾶
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