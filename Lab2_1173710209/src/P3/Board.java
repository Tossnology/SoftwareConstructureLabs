package P3;
/**
 * ���̽ӿڣ���һϵ������Ӧ�߱��ķ���
 * 
 * @author yRXX
 *
 */
public interface Board {
	
	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public int getBlackNum();
	
	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public int getWhiteNum();
	
	/**
	 * ���������������
	 * 
	 * @param position ������ӵ�λ��
	 * @param piece ���Ӷ���
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean addPiece(Position position,Piece piece);
	
	/**
	 * ��ָ��λ��ȥ������
	 * 
	 * @param position ָ����λ��
	 * @return �Ƿ�ȥ���ɹ�
	 */
	public boolean removePiece(Position position);
	
	/**
	 * ��ȡָ��λ�õ�����
	 * 
	 * @param position ָ����λ��
	 * @return ָ��λ�õ����Ӷ���
	 */
	public Piece getPiece(Position position);
}
