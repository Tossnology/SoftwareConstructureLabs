package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessActionTest {

	private ChessAction Do = new ChessAction(new ChessBoard());
	
	@Test
	public void testMovePiece() {
		Player player = new Player("yRXX","white");
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,1);
		Position p3 = new Position(0,2);
		Position p4 = new Position(8,8);
		Position p5 = new Position(0,7);
		
		assertFalse(Do.movePiece(player, p1.getX(), p1.getY(), p2.getX(), p2.getY()));
		
		assertFalse(Do.movePiece(player, p1.getX(), p1.getY(), p4.getX(), p4.getY()));
		
		assertFalse(Do.movePiece(player, p3.getX(), p3.getY(), p2.getX(), p2.getY()));
		
		assertFalse(Do.movePiece(player, p3.getX(), p3.getY(), p4.getX(), p4.getY()));
		
		assertFalse(Do.movePiece(player, p4.getX(), p4.getY(), p3.getX(), p3.getY()));
		
		assertFalse(Do.movePiece(player, p5.getX(), p5.getY(), p3.getX(), p3.getY()));
		
		assertFalse(Do.movePiece(player, p1.getX(), p1.getY(), p1.getX(), p1.getY()));
		
		assertTrue(Do.movePiece(player, p1.getX(), p1.getY(), p3.getX(), p3.getY()));
	}

	@Test
	public void testEatPiece() {
		Player player = new Player("yRXX","white");
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,1);
		Position p3 = new Position(0,2);
		Position p4 = new Position(8,8);
		Position p5 = new Position(0,7);
		
		assertFalse(Do.eatPiece(player, p1.getX(), p1.getY(), p2.getX(), p2.getY()));
		
		assertFalse(Do.eatPiece(player, p1.getX(), p1.getY(), p3.getX(), p3.getY()));
		
		assertFalse(Do.eatPiece(player, p3.getX(), p3.getY(), p1.getX(), p1.getY()));
		
		assertFalse(Do.eatPiece(player, p1.getX(), p1.getY(), p1.getX(), p1.getY()));
		
		assertFalse(Do.eatPiece(player, p4.getX(), p4.getY(), p1.getX(), p1.getY()));
		
		assertFalse(Do.eatPiece(player, p1.getX(), p1.getY(), p4.getX(), p4.getY()));
		
		assertFalse(Do.eatPiece(player, p5.getX(), p5.getY(), p1.getX(), p1.getY()));
		
		assertTrue(Do.eatPiece(player, p1.getX(), p1.getY(), p5.getX(), p5.getY()));
	}
	
	@Test
	public void testGetHistory() {
		Player player = new Player("yRXX","white");
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,2);
		Position p3 = new Position(0,7);
		
		assertTrue(Do.getHistory().isEmpty());
		
		Do.movePiece(player, p1.getX(), p1.getY(), p2.getX(), p2.getY());
		Do.eatPiece(player, p2.getX(), p2.getY(), p3.getX(), p3.getY());
		
		assertEquals(Do.getHistory().size(),2);
		assertEquals(Do.getHistory().get(0),"玩家yRXX将(0,0)处的rook移动到了(0,2)");
		assertEquals(Do.getHistory().get(1),"玩家yRXX用(0,2)处的rook吃掉了对方(0,7)处的rook");
	}

	@Test
	public void testQueryPosition() {
		Player player = new Player("yRXX","white");
		Position p1 = new Position(0,0);
		Position p2 = new Position(0,2);
		Position p3 = new Position(0,7);
		Position p4 = new Position(8,8);
		
		assertEquals(Do.queryPosition(p1.getX(), p1.getY()),"(0,0) rook white");
		
		assertEquals(Do.queryPosition(p2.getX(), p2.getY()),"此处无棋子");
		
		assertEquals(Do.queryPosition(p4.getX(), p4.getY()),"此处无棋子");
		
		Do.movePiece(player, p1.getX(), p1.getY(), p2.getX(), p2.getY());
		assertEquals(Do.queryPosition(p2.getX(), p2.getY()),"(0,2) rook white");
		assertEquals(Do.queryPosition(p1.getX(), p1.getY()),"此处无棋子");
		
		assertEquals(Do.queryPosition(p3.getX(), p3.getY()),"(0,7) rook black");
		Do.eatPiece(player, p2.getX(), p2.getY(), p3.getX(), p3.getY());
		assertEquals(Do.queryPosition(p3.getX(), p3.getY()),"(0,7) rook white");
		assertEquals(Do.queryPosition(p2.getX(), p2.getY()),"此处无棋子");
	}
}
