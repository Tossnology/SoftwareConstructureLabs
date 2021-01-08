package P3;
/**
 * ���ӹ���
 * @author yRXX
 *
 */
public class PieceFactory {

	/**
	 * ��һ��Χ��
	 * 
	 * @param color ������ɫ
	 * @return һ���µ�Χ�����
	 */
	public Piece getGo(String color) {
		return new Piece("go",color);
	}
	
	/**
	 * ��һ��������
	 * @param color ������ɫ
	 * @return һ���µĹ��������
	 */
	public Piece getKing(String color) {
		return new Piece("king", color);
	}
	
	/**
	 * ��һ���ʺ���
	 * @param color ������ɫ
	 * @return һ���µĻʺ������
	 */
	public Piece getQueen(String color) {
		return new Piece("queen", color);
	}
	
	/**
	 * ��һ��������
	 * @param color ������ɫ
	 * @return һ���µı��������
	 */
	public Piece getRook(String color) {
		return new Piece("rook", color);
	}
	
	/**
	 * ��һ��������
	 * @param color ������ɫ
	 * @return һ���µ����������
	 */
	public Piece getBishop(String color) {
		return new Piece("bishop", color);
	}
	
	/**
	 * ��һ����ʿ��
	 * @param color ������ɫ
	 * @return һ���µ���ʿ�����
	 */
	public Piece getKnight(String color) {
		return new Piece("knight", color);
	}
	
	/**
	 * ��һ��ʿ����
	 * @param color ������ɫ
	 * @return һ���µ�ʿ�������
	 */
	public Piece getPawn(String color) {
		return new Piece("pawn", color);
	}
}
