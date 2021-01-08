package P3;

/**
 * ���֣������ֺ���ɫ
 * immutable
 * @author yRXX
 *
 */
public class Player {
	private String name;
	private String color;
	
	// Abstraction function:
	//  AF(name,color)=һ���������ֺ���ɫ�����ֶ���
	// Representation invariant:
	//  name:�ǿ�
	//	color:{black,white}
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	/**
	 * ���ֶ�������
	 * 
	 * @param name ��������
	 * @param color ������ɫ
	 */
	public Player(String name,String color) {
		this.name = name;
		this.color = color;
		checkRep();
	}
	
	/**
	 * ��ȡ����
	 * 
	 * @return ��������
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ��ȡ��ɫ
	 * 
	 * @return ������ɫ
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * ���rep
	 */
	public void checkRep() {
		assert name!=null : "�������ֲ���Ϊ��";
		assert color!=null : "���ֲ���û����ɫ";
		assert color.equals("white")||color.equals("black") : "������ɫֻ���ǺںͰ�";
	}
}
