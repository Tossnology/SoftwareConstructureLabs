package circularOrbit;

import java.util.ArrayList;
import java.util.Map;
import phisicalObject.PhisicalObject;
import track.Track;

/**
 * 一个环形轨道系统，其中包括中心点物体、轨道物体、轨道 
 * L为中心点物体类型，E为轨道物体类型，都必须为immutable
 * 
 * @author yRXX
 *
 * @param <L> 中心点物体类型，immutable
 * @param <E> 轨道物体类型，immutable
 */
public interface CircularOrbit<L, E extends PhisicalObject> {

	/**
	 * 静态工厂方法，创建一个空的环形轨道系统
	 * 
	 * @param <L> 中心点物体类型，immutable
	 * @param <E> 轨道物体类型，immutable 
	 * 
	 * @return 一个空的环形轨道系统
	 */
	public static <L,E extends PhisicalObject> CircularOrbit<L,E> getEmpty(){
		CircularOrbit<L,E> empty = new ConcreteCircularOrbit<L,E>();
		return empty;
	}
	
	/**
	 * 添加轨道
	 * 
	 * @param track 新的轨道
	 * @return 如果系统中已经存在相同的轨道，则不变并返回false
	 *         如果不存在，则添加并返回true
	 */
	public boolean addTrack(Track track);
	
	/**
	 * 删除轨道
	 * 
	 * @param track 要删除的轨道
	 * @return 如果系统中已经存在相同的轨道，则删除并返回true
	 *         如果不存在，则不变并返回false
	 */
	public boolean removeTrack(Track track);
	
	/**
	 * 添加中心物体
	 * 
	 * @param object 要添加的物体
	 * @return 根据具体情况添加成功返回true，添加失败返回false
	 */
	public boolean addCentralObject(L object);
	
	/**
	 * 添加轨道物体
	 * 
	 * @param object 要添加的物体
	 * @param track 目标轨道
	 */
	public void addTrackObject(E object,Track track);
	
//	/**
//	 * 添加中心物体与轨道物体的关系
//	 * 
//	 * @param object 轨道物体
//	 * @return 根据具体情况添加成功返回true，添加失败返回false
//	 */
//	public void addRelationshipC2T(E object);
//	
	/**
	 * 删除轨道物体
	 * 
	 * @param name 要删除的物体名字
	 */
	public void removeTrackObject(String name);
	
	/**
	 * 切换轨道
	 * 
	 * @param name 要切换的物体名字
	 * @param source 起始轨道
	 * @param target 目标轨道
	 */
	public void changeTrack(String name,Track source,Track target);
	
	/**
	 * 获取轨道集合
	 * @return 轨道集合
	 */
	public ArrayList<Track> getTracks();
	
	/**
	 * 获取轨道物体
	 * @return 所有轨道物体及其所在轨道
	 */
	public Map<E,Track> getTrackObjects();
	
	/**
	 * 获取中心物体
	 * @return 中心物体列表
	 */
	public ArrayList<L> getCentralObjects();
	
	/**
	 * 给轨道排序
	 */
	public void sortTracks();
}
