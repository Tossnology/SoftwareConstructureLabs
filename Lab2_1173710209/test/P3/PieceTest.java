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
			assertTrue(e.getMessage().equals("û��������ɫ"));
		}
		
		try {
			new Piece("cat","white");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("û��������"));
		}
		
		try {
			new Piece("cat","red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("û��������"));
		}
		
		try {
			new Piece(null,"red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("��������Ϊ��"));
		}
		
		try {
			new Piece("knight",null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("�����ɫ����Ϊ��"));
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
