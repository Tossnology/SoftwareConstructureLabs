package P3;

import java.util.Arrays;

/**
 * ���ӣ������ֺ���ɫ
 * immutable
 * @author yRXX
 *
 */
public class Piece {
	private String name;
	private String color;
	
	// Abstraction function:
	//  AF(name,color)=һ���������ֺ���ɫ�����Ӷ���
	// Representation invariant:
	//  name:{go,pawn,rook,knight,bishop,king,queen}
	//	color:{black,white}
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�
	
	/**
	 * Piece��������
	 * 
	 * @param name ��������
	 * @param color ������ɫ
	 */
	public Piece(String name,String color) {
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
	 * 
	 */
	public void checkRep() {
		assert name!=null : "��������Ϊ��";
		assert color!=null : "�����ɫ����Ϊ��";
		assert Arrays.asList("go","pawn","rook","knight","bishop","king","queen").contains(name) : "û��������";
		assert color.equals("white")||color.equals("black") : "û��������ɫ";
	}
}
