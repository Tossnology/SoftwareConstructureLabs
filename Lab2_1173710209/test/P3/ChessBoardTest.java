package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessBoardTest {

	@Test
	public void testChessBoard() {
		new ChessBoard();
	}

	private ChessBoard board = new ChessBoard();
	private PieceFactory pf = new PieceFactory();
	
	@Test
	public void testGetPiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,2);
		Position p3 = new Position(8,9);
		
		assertEquals(board.getPiece(p1).getName(),"rook");
		assertEquals(board.getPiece(p1).getColor(),"white");
		
		assertEquals(board.getPiece(p2),null);
		
		assertEquals(board.getPiece(p3),null);
	}
	
	@Test
	public void testRemovePiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,2);
		Position p3 = new Position(8,9);
		
		assertFalse(board.removePiece(p3));
		
		assertFalse(board.removePiece(p2));
		
		assertTrue(board.removePiece(p1));
		assertEquals(board.getPiece(p1),null);
	}
	
	@Test
	public void testAddPiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,2);
		Position p3 = new Position(8,9);
		
		assertFalse(board.addPiece(p1,pf.getKing("white")));
		
		assertFalse(board.addPiece(p2,pf.getKing("white")));
		
		assertFalse(board.addPiece(p3,pf.getKing("white")));
		
		board.removePiece(p1);
		assertFalse(board.addPiece(p1,pf.getKing("black")));
		assertTrue(board.addPiece(p1,pf.getKing("white")));
		assertEquals(board.getPiece(p1).getName(),"king");
		assertEquals(board.getPiece(p1).getColor(),"white");
	}
	
	@Test
	public void testGetBlackNum() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,7);
		
		assertEquals(board.getBlackNum(),16);
		
		board.removePiece(p1);
		assertEquals(board.getBlackNum(),16);
		
		board.removePiece(p2);
		assertEquals(board.getBlackNum(),15);
		
		board.addPiece(p1, pf.getKing("white"));
		assertEquals(board.getBlackNum(),15);
		
		board.addPiece(p2, pf.getKing("black"));
		assertEquals(board.getBlackNum(),16);
	}
	
	@Test
	public void testGetWhiteNum() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,7);
		
		assertEquals(board.getWhiteNum(),16);
		
		board.removePiece(p1);
		assertEquals(board.getWhiteNum(),15);
		
		board.removePiece(p2);
		assertEquals(board.getWhiteNum(),15);
		
		board.addPiece(p1, pf.getKing("white"));
		assertEquals(board.getWhiteNum(),16);
		
		board.addPiece(p2, pf.getKing("black"));
		assertEquals(board.getWhiteNum(),16);
	}
	
}
