package P3;

import java.util.ArrayList;

/**
 * ���ֶ����ӿ�
 * 
 * @author yRXX
 *
 */
public interface Action {
	
	/**
	 * ����(���Χ��)
	 * 
	 * @param player ִ�ж���������
	 * @param piece �����������̵����Ӷ���
	 * @param x ����λ�õĺ�����
	 * @param y ����λ�õ�������
	 * @return �Ƿ����ӳɹ�
	 */
	public boolean putPiece(Player player, Piece piece, int x, int y);
	
	/**
	 * ����(���Χ��)
	 * 
	 * @param player ִ�ж���������
	 * @param x ����λ�õĺ�����
	 * @param y ����λ�õ�������
	 * @return �Ƿ����ӳɹ�
	 */
	public boolean takePiece(Player player, int x, int y);
	
	/**
	 * �ƶ�����(��Թ�������)
	 * 
	 * @param player ִ�ж���������
	 * @param x1 ��������
	 * @param y1 ���������
	 * @param x2 �յ������
	 * @param y2 �յ�������
	 * @return �Ƿ��ƶ��ɹ�
	 */
	public boolean movePiece(Player player, int x1, int y1, int x2, int y2);
	
	/**
	 * ����(��Թ�������)
	 * 
	 * @param player ִ�ж���������
	 * @param x1 ��ѡ���ӵĺ�����
	 * @param y1 ��ѡ���ӵ�������
	 * @param x2 �������ӵĺ�����
	 * @param y2 �������ӵ�������
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean eatPiece(Player player, int x1, int y1, int x2, int y2);
	
	/**
	 * ��ȡ������ʷ
	 * 
	 * @return ������ʷ��¼��
	 */
	public ArrayList<String> getHistory();
	
	/**
	 * ��ѯλ��
	 * 
	 * @param x ��ѯλ�õĺ�����
	 * @param y ��ѯλ�õ�������
	 * @return ����λ����Ϣ
	 */
	public String queryPosition(int x,int y);
}
