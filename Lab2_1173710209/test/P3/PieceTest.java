package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {
	
	@Test
	public void testPiece() {
		new Piece("go","white");
		new Piece("go","black");
		new Piece("rook","white");
		
		try {
			new Piece("king","blue");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			new Piece("cat","white");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种棋"));
		}
		
		try {
			new Piece("cat","red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种棋"));
		}
		
		try {
			new Piece(null,"red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋名不能为空"));
		}
		
		try {
			new Piece("knight",null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
	
	@Test
	public void testGetName() {
		Piece p = new Piece("queen","black");
		
		assertEquals(p.getName(),"queen");
	}
	
	@Test
	public void testGetColor() {
		Piece p = new Piece("queen","black");
		
		assertEquals(p.getColor(),"black");
	}
}
