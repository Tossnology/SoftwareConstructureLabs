package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoActionTest {
	
	private GoAction Do = new GoAction(new GoBoard());
	private PieceFactory pf = new PieceFactory();
	
	@Test
	public void testPutPiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(19,19);
		Player player = new Player("yRXX","white");
		
		assertFalse(Do.putPiece(player, pf.getGo("black"), p1.getX(), p1.getY()));
		
		assertFalse(Do.putPiece(player, pf.getKing("white"), p1.getX(), p1.getY()));
		
		assertFalse(Do.putPiece(player, pf.getGo("white"), p2.getX(), p2.getY()));
		
		assertTrue(Do.putPiece(player, pf.getGo("white"), p1.getX(), p1.getY()));
	}
	
	@Test
	public void testTakePiece() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(19,19);
		Player player1 = new Player("yRXX","white");
		Player player2 = new Player("kit","black");
		
		assertFalse(Do.takePiece(player1, p1.getX(), p1.getY()));
		
		assertFalse(Do.takePiece(player1, p2.getX(), p2.getY()));
		
		Do.putPiece(player1, pf.getGo("white"), p1.getX(), p1.getY());
		assertFalse(Do.takePiece(player1, p1.getX(), p1.getY()));
		assertTrue(Do.takePiece(player2, p1.getX(), p1.getY()));
	}
	
	@Test
	public void testGetHistory() {
		Position p1 = new Position(0,0);
		Player player1 = new Player("yRXX","white");
		Player player2 = new Player("kit","black");
		
		assertTrue(Do.getHistory().isEmpty());
		
		Do.putPiece(player1, pf.getGo("white"), p1.getX(), p1.getY());
		assertEquals(Do.getHistory().size(),1);
		assertTrue(Do.getHistory().contains("玩家yRXX在(0,0)处落子"));
		
		Do.takePiece(player2, p1.getX(), p1.getY());
		assertEquals(Do.getHistory().size(),2);
		assertTrue(Do.getHistory().contains("玩家yRXX在(0,0)处落子"));
		assertTrue(Do.getHistory().contains("玩家kit在(0,0)处提子"));
	}
	
	@Test
	public void testQueryPosition() {
		Position p1 = new Position(0,0);
		Position p2 = new Position(19,19);
		Player player1 = new Player("yRXX","white");
		Player player2 = new Player("kit","black");
		
		assertEquals(Do.queryPosition(p1.getX(), p1.getY()), "此处无棋子");
		
		assertEquals(Do.queryPosition(p2.getX(), p2.getY()), "此处无棋子");
		
		Do.putPiece(player1, pf.getGo("white"), p1.getX(), p1.getY());
		assertEquals(Do.queryPosition(p1.getX(), p1.getY()), "(0,0) go white");
		
		Do.takePiece(player2, p1.getX(), p1.getY());
		assertEquals(Do.queryPosition(p1.getX(), p1.getY()), "此处无棋子");
	}

}
