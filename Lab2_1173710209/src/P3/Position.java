package P3;
/**
 * λ�ã��к�������
 * immutable
 * @author yRXX
 *
 */
public class Position {
	private int x;
	private int y;
	
	// Abstraction function:
	//  AF(x,y)=һ�������λ�ö���
	// Representation invariant:
	//  x:�ǿգ���Ȼ��
	//	y:�ǿգ���Ȼ��
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * λ�ö�������
	 * 
	 * @param x ������
	 * @param y ������
	 */
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	
	/**
	 * ��ȡ������
	 * 
	 * @return ������ֵ
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * ��ȡ������
	 * 
	 * @return ������ֵ
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * ��дtoString����
	 * 
	 * @return λ�õ��ַ�����ʾ��ʽ(x,y)
	 */
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	/**
	 * ��дequals����
	 * �ж�����Ϊ��Ӧ��������һ��
	 * 
	 * @param obj �ȽϵĶ���
	 * @return �Ƿ�ȼ�
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
	 * ��дHashCode
	 * 
	 * @return ��������֮��
	 */
	@Override
	public int hashCode() {
		return x+y;
	}
	
	/**
	 * ���rep
	 */
	public void checkRep() {
		assert x>=0&&y>=0 : "�����������Ϊ��Ȼ��";
	}
}
