package circularOrbit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import applications.TrackComparator;
import phisicalObject.PhisicalObject;
import track.Track;

/**
 * 具体的环形轨道系统，实现CircularOrbit接口
 * 
 * @author yRXX
 *
 * @param <L>
 *            中心点物体类型，immutable
 * @param <E>
 *            轨道物体类型，immutable
 */
public class ConcreteCircularOrbit<L, E extends PhisicalObject> implements CircularOrbit<L, E> {

	private final ArrayList<L> CentralObjects = new ArrayList<L>();
	private final Map<E, Track> TrackObjects = new HashMap<E, Track>();
	private final ArrayList<Track> tracks = new ArrayList<Track>();

	/*
	 * Abstraction function:
	 * AF(CentralObjects,TrackObjects,tracks,relations)=一个环形轨道系统 Representation
	 * invariant: CentralObjects:中心物体列表 TrackObjects:轨道物体列表 tracks:轨道集合
	 * relations:关系图 Safety from rep exposure: 所有属性都是private
	 */

	@Override
	public boolean addTrack(Track track) {
		if (!tracks.contains(track)) {
			tracks.add(track);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeTrack(Track track) {
		if (tracks.contains(track)) {
			ArrayList<E> tmp = new ArrayList<E>();
			for (E e : TrackObjects.keySet()) {
				if (TrackObjects.get(e).equals(track)) {
					tmp.add(e);
				}
			}
			TrackObjects.keySet().removeAll(tmp);
			tracks.remove(track);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addCentralObject(L object) {
		if (CentralObjects.add(object)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addTrackObject(E object, Track track) {
		if (tracks.contains(track)) {
			TrackObjects.put(object, track);
		}else if(track.getRadius()==-1)
		{
			TrackObjects.put(object, track);
		}
	}

	@Override
	public void removeTrackObject(String name) {
		for(E object:TrackObjects.keySet()) {
			if(object.getName().equals(name)) {
				TrackObjects.remove(object);
				break;
			}
		}
	}

	@Override
	public void changeTrack(String name, Track source, Track target) {
		for (E e : TrackObjects.keySet()) {
			if (e.getName().equals(name) && TrackObjects.get(e).equals(source)) {
				TrackObjects.replace(e, target);
				break;
			}
		}
	}

	@Override
	public ArrayList<Track> getTracks() {
		return tracks;
	}

	@Override
	public Map<E, Track> getTrackObjects() {
		return TrackObjects;
	}

	@Override
	public ArrayList<L> getCentralObjects() {
		return CentralObjects;
	}

	@Override
	public void sortTracks() {
		// TODO Auto-generated method stub
		tracks.sort(new TrackComparator());
	}
}
