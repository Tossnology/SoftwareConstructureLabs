package P3;
/**
 * ��Ϸ�ӿ�
 * 
 * @author yRXX
 *
 */
public interface Game {
	
	/**
	 * ��Ϸ����
	 */
	public void Body();
	
	/**
	 * ���������ʷ
	 */
	public void showHistory();
	
	/**
	 * �غ�
	 * 
	 * @param player �ûغ��ж�������
	 */
	public void Turn(Player player);
	
	/**
	 * ��������
	 * 
	 * @return ���������
	 */
	public Position inputPosition();
}
