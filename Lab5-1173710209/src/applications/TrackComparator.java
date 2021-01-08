package applications;

import java.util.Comparator;

import track.Track;

public class TrackComparator implements Comparator<Track> {

  @Override
  public int compare(Track track1, Track track2) {
    if (track1.getRadius() < track2.getRadius()) {
      return -1;
    } else {
      return 1;
    }
  }

}
