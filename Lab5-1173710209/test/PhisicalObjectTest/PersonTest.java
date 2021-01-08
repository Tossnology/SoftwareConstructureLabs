package PhisicalObjectTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import phisicalObject.Person;

class PersonTest {

	@Test
	void testGetName() {
		Person p = new Person("LisaWong",25,"F");
		assertEquals(p.getName(),"LisaWong");
	}

	@Test
	void testGetAge() {
		Person p = new Person("LisaWong",25,"F");
		assertEquals(p.getAge(),25);
	}
	
	@Test
	void testGetSex() {
		Person p = new Person("LisaWong",25,"F");
		assertEquals(p.getSex(),"F");
	}
}
