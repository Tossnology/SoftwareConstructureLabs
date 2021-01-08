package PhisicalObjectTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import phisicalObject.Electron;

class ElectronTest {

	@Test
	void testGetName() {
		Electron e = new Electron();
		assertEquals(e.getName(),"electron");
	}

}
