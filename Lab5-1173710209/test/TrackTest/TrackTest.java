package TrackTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import track.Track;

class TrackTest {

	@Test
	void testGetRadius() {
		Track t = new Track(2333);
		assertEquals(t.getRadius(),2333);
	}

	void testEquals() {
		Track t1 = new Track(2333);
		Track t2 = new Track(2333);
		Track t3 = new Track(233);
		assertEquals(t1,t2);
		assertNotEquals(t1,t3);
	}
}
