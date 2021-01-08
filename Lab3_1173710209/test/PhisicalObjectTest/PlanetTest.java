package PhisicalObjectTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import phisicalObject.Planet;

class PlanetTest {

	@Test
	void testAll() {
		Planet p = new Planet("Venus","Solid","Red",6378.137,1.49e20,203.24,"CCW",181.23);
		
		assertEquals(p.getName(),"Venus");
		assertEquals(p.getState(),"Solid");
		assertEquals(p.getColor(),"Red");
		assertEquals(p.getRadius(),6378.137);
		assertEquals(p.getOrbitRadius(),1.49e20);
		assertEquals(p.getSpeed(),203.24);
		assertEquals(p.getDirection(),"CCW");
		assertEquals(p.getInitAngle(),181.23);
	}

}
