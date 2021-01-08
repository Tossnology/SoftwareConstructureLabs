package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoBoardTest {

	@Test
	public void testGoBoard() {
		new GoBoard();
	}

	private GoBoard board = new GoBoard();
	private PieceFactory pf = new PieceFactory();
	
	@Test
	public void testAddPiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(19,19);
		
		assertFalse(board.addPiece(p2, pf.getGo("white")));
		
		assertFalse(board.addPiece(p1, null));
		
		assertFalse(board.addPiece(p1, pf.getRook("black")));
		
		assertTrue(board.addPiece(p1, pf.getGo("black")));
		assertFalse(board.addPiece(p1, pf.getGo("white")));
	}
	
	@Test
	public void testGetPiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,3);
		Position p3 = new Position(19,19);
		
		assertEquals(board.getPiece(p1),null);
		assertEquals(board.getPiece(p2),null);
		assertEquals(board.getPiece(p3),null);
		
		board.addPiece(p1, pf.getGo("black"));
		assertEquals(board.getPiece(p1).getName(),"go");
		assertEquals(board.getPiece(p1).getColor(),"black");
	}
	
	@Test
	public void testRemovePiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,3);
		Position p3 = new Position(19,19);
		
		assertFalse(board.removePiece(p1));
		assertFalse(board.removePiece(p2));
		assertFalse(board.removePiece(p3));
		
		board.addPiece(p1, pf.getGo("black"));
		assertTrue(board.removePiece(p1));
		assertEquals(board.getPiece(p1),null);
	}
	
	@Test
	public void testGetBlackNum() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,1);
		
		assertEquals(board.getBlackNum(), 0);
		
		board.addPiece(p1, pf.getGo("black"));
		assertEquals(board.getBlackNum(), 1);
		
		board.addPiece(p2, pf.getGo("white"));
		assertEquals(board.getBlackNum(), 1);
		
		board.removePiece(p1);
		assertEquals(board.getBlackNum(), 0);
	}
	
	@Test
	public void testGetWhiteNum() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,1);
		
		assertEquals(board.getWhiteNum(), 0);
		
		board.addPiece(p1, pf.getGo("black"));
		assertEquals(board.getWhiteNum(), 0);
		
		board.addPiece(p2, pf.getGo("white"));
		assertEquals(board.getWhiteNum(), 1);
		
		board.removePiece(p1);
		assertEquals(board.getWhiteNum(), 1);
	}
}
