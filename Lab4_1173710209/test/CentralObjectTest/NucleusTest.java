package CentralObjectTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import centralObject.Nucleus;

class NucleusTest {

	@Test
	void testGetName() {
		Nucleus n = new Nucleus("Er");
		assertEquals(n.getName(),"Er");
	}

}
