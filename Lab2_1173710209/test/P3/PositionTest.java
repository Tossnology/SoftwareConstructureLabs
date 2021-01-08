package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testPosition() {
		new Position(0,0);
		new Position(0,1);
		new Position(1,0);
		
		try {
			new Position(-1,0);
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "横纵坐标必须为自然数");
		}
		
		try {
			new Position(-1,-4);
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "横纵坐标必须为自然数");
		}
		
		try {
			new Position(2,-4);
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "横纵坐标必须为自然数");
		}
	}

	@Test
	public void testGetX() {
		Position p = new Position(0,1);
		
		assertEquals(p.getX(),0);
	}
	
	@Test
	public void testGetY() {
		Position p = new Position(0,1);
		
		assertEquals(p.getY(),1);
	}
	
	@Test
	public void testToString() {
		Position p = new Position(0,1);
		
		assertEquals(p.toString(),"(0,1)");
	}
	
	@Test
	public void testEquals() {
		Position p1 = new Position(0,1);
		Position p2 = new Position(0,1);
		Position p3 = new Position(0,2);
		Position p4 = new Position(2,1);
		Position p5 = new Position(2,2);
		Piece p = new Piece("rook","white");
		
		assertTrue(p1.equals(p1));
		assertFalse(p1.equals(p));
		assertFalse(p1.equals(null));
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
		assertFalse(p1.equals(p4));
		assertFalse(p1.equals(p5));
	}
	
	@Test
	public void testHashCode() {
		Position p = new Position(0,1);
		
		assertEquals(p.hashCode(),1);
	}
}
