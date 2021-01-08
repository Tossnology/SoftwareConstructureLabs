package CircularOrbitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import centralObject.Stellar;
import circularOrbit.ConcreteCircularOrbit;
import phisicalObject.Planet;
import track.Track;

class ConcreteCircularOrbitTest {

	@Test
	void testAddTrack() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		
		c.addTrack(new Track(25));
		assertEquals(c.getTracks().size(),1);
		assertEquals(c.getTracks().get(0).getRadius(),25);
		
		c.addTrack(new Track(25));
		assertEquals(c.getTracks().size(),1);
		assertEquals(c.getTracks().get(0).getRadius(),25);
		
		c.addTrack(new Track(28));
		assertEquals(c.getTracks().size(),2);
		assertEquals(c.getTracks().get(1).getRadius(),28);
	}

	@Test
	void testRemoveTrack() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		
		c.addTrack(new Track(25));
		c.removeTrack(new Track(28));
		assertEquals(c.getTracks().size(),1);
		assertEquals(c.getTracks().get(0).getRadius(),25);
		
		c.removeTrack(new Track(25));
		assertTrue(c.getTracks().isEmpty());
	}
	
	@Test
	void testAddCentralObject() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		Stellar s = new Stellar("Sun",6.96392e5,1.9885e30);
		
		c.addCentralObject(s);
		assertEquals(c.getCentralObjects().size(),1);
		assertEquals(c.getCentralObjects().get(0).getName(),"Sun");
	}
	
	@Test
	void testAddTrackObject() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		Planet p = new Planet("Venus","Solid","Red",6378.137,1.49e20,203.24,"CCW",181.23);
		Track t = new Track(p.getOrbitRadius());
		c.addTrack(t);
		
		c.addTrackObject(p, t);
		assertEquals(c.getTrackObjects().size(),1);
		assertEquals(c.getTrackObjects().get(p),t);
	}
	
	@Test
	void testRemoveTrackObject() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		Planet p = new Planet("Venus","Solid","Red",6378.137,1.49e20,203.24,"CCW",181.23);
		Track t = new Track(p.getOrbitRadius());
		c.addTrack(t);
		c.addTrackObject(p, t);
		
		c.removeTrackObject("Venus");
		assertTrue(c.getTrackObjects().isEmpty());
	}
	
	@Test
	void testChangeTrack() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		Planet p = new Planet("Venus","Solid","Red",6378.137,1.49e20,203.24,"CCW",181.23);
		Track t1 = new Track(p.getOrbitRadius());
		Track t2 = new Track(123456);
		c.addTrack(t1);
		c.addTrack(t2);
		c.addTrackObject(p, t1);
		
		c.changeTrack("Venus", t1, t2);
		assertEquals(c.getTrackObjects().get(p),t2);
	}
	
	@Test
	void testGetTracks() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		Track t1 = new Track(123);
		Track t2 = new Track(123456);
		c.addTrack(t1);
		c.addTrack(t2);

		assertEquals(c.getTracks().size(),2);
		assertEquals(c.getTracks().get(0),t1);
		assertEquals(c.getTracks().get(1),t2);
	}
	
	@Test
	void testSortTracks() {
		ConcreteCircularOrbit<Stellar,Planet> c = new ConcreteCircularOrbit<Stellar,Planet>();
		Track t1 = new Track(123456);
		Track t2 = new Track(123);
		c.addTrack(t1);
		c.addTrack(t2);
		
		c.sortTracks();
		assertEquals(c.getTracks().size(),2);
		assertEquals(c.getTracks().get(0),t2);
		assertEquals(c.getTracks().get(1),t1);
	}
}
