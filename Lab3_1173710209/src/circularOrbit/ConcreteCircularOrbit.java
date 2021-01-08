package circularOrbit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import applications.TrackComparator;
import phisicalObject.PhisicalObject;
import track.Track;

/**
 * ����Ļ��ι��ϵͳ��ʵ��CircularOrbit�ӿ�
 * 
 * @author yRXX
 *
 * @param <L>
 *            ���ĵ��������ͣ�immutable
 * @param <E>
 *            ����������ͣ�immutable
 */
public class ConcreteCircularOrbit<L, E extends PhisicalObject> implements CircularOrbit<L, E> {

	private final ArrayList<L> CentralObjects = new ArrayList<L>();
	private final Map<E, Track> TrackObjects = new HashMap<E, Track>();
	private final ArrayList<Track> tracks = new ArrayList<Track>();

	/*
	 * Abstraction function:
	 * AF(CentralObjects,TrackObjects,tracks,relations)=һ�����ι��ϵͳ Representation
	 * invariant: CentralObjects:���������б� TrackObjects:��������б� tracks:�������
	 * relations:��ϵͼ Safety from rep exposure: �������Զ���private
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
