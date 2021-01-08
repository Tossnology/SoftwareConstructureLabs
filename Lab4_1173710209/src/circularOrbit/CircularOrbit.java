package circularOrbit;

import java.util.ArrayList;
import java.util.Map;
import phisicalObject.PhisicalObject;
import track.Track;

/**
 * һ�����ι��ϵͳ�����а������ĵ����塢������塢��� 
 * LΪ���ĵ��������ͣ�EΪ����������ͣ�������Ϊimmutable
 * 
 * @author yRXX
 *
 * @param <L> ���ĵ��������ͣ�immutable
 * @param <E> ����������ͣ�immutable
 */
public interface CircularOrbit<L, E extends PhisicalObject> {

	/**
	 * ��̬��������������һ���յĻ��ι��ϵͳ
	 * 
	 * @param <L> ���ĵ��������ͣ�immutable
	 * @param <E> ����������ͣ�immutable 
	 * 
	 * @return һ���յĻ��ι��ϵͳ
	 */
	public static <L,E extends PhisicalObject> CircularOrbit<L,E> getEmpty(){
		CircularOrbit<L,E> empty = new ConcreteCircularOrbit<L,E>();
		return empty;
	}
	
	/**
	 * ��ӹ��
	 * 
	 * @param track �µĹ��
	 * @return ���ϵͳ���Ѿ�������ͬ�Ĺ�����򲻱䲢����false
	 *         ��������ڣ�����Ӳ�����true
	 */
	public boolean addTrack(Track track);
	
	/**
	 * ɾ�����
	 * 
	 * @param track Ҫɾ���Ĺ��
	 * @return ���ϵͳ���Ѿ�������ͬ�Ĺ������ɾ��������true
	 *         ��������ڣ��򲻱䲢����false
	 */
	public boolean removeTrack(Track track);
	
	/**
	 * �����������
	 * 
	 * @param object Ҫ��ӵ�����
	 * @return ���ݾ��������ӳɹ�����true�����ʧ�ܷ���false
	 */
	public boolean addCentralObject(L object);
	
	/**
	 * ��ӹ������
	 * 
	 * @param object Ҫ��ӵ�����
	 * @param track Ŀ����
	 */
	public void addTrackObject(E object,Track track);
	
//	/**
//	 * �������������������Ĺ�ϵ
//	 * 
//	 * @param object �������
//	 * @return ���ݾ��������ӳɹ�����true�����ʧ�ܷ���false
//	 */
//	public void addRelationshipC2T(E object);
//	
	/**
	 * ɾ���������
	 * 
	 * @param name Ҫɾ������������
	 */
	public void removeTrackObject(String name);
	
	/**
	 * �л����
	 * 
	 * @param name Ҫ�л�����������
	 * @param source ��ʼ���
	 * @param target Ŀ����
	 */
	public void changeTrack(String name,Track source,Track target);
	
	/**
	 * ��ȡ�������
	 * @return �������
	 */
	public ArrayList<Track> getTracks();
	
	/**
	 * ��ȡ�������
	 * @return ���й�����弰�����ڹ��
	 */
	public Map<E,Track> getTrackObjects();
	
	/**
	 * ��ȡ��������
	 * @return ���������б�
	 */
	public ArrayList<L> getCentralObjects();
	
	/**
	 * ���������
	 */
	public void sortTracks();
}
