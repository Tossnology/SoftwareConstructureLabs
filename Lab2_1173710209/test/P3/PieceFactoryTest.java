package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceFactoryTest {

	private PieceFactory pf = new PieceFactory();
	
	@Test
	public void testGetGo() {
		Piece p1 = pf.getGo("white");
		Piece p2 = pf.getGo("black");
		
		assertEquals(p1.getName(),"go");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"go");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getGo("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getGo(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}

	@Test
	public void testGetKing() {
		Piece p1 = pf.getKing("white");
		Piece p2 = pf.getKing("black");
		
		assertEquals(p1.getName(),"king");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"king");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getKing("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getKing(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
	
	@Test
	public void testGetQueen() {
		Piece p1 = pf.getQueen("white");
		Piece p2 = pf.getQueen("black");
		
		assertEquals(p1.getName(),"queen");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"queen");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getQueen("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getQueen(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
	
	@Test
	public void testGetRook() {
		Piece p1 = pf.getRook("white");
		Piece p2 = pf.getRook("black");
		
		assertEquals(p1.getName(),"rook");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"rook");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getRook("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getRook(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
	
	@Test
	public void testGetBishop() {
		Piece p1 = pf.getBishop("white");
		Piece p2 = pf.getBishop("black");
		
		assertEquals(p1.getName(),"bishop");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"bishop");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getBishop("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getBishop(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
	
	@Test
	public void testGetKnight() {
		Piece p1 = pf.getKnight("white");
		Piece p2 = pf.getKnight("black");
		
		assertEquals(p1.getName(),"knight");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"knight");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getKnight("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getKnight(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
	
	@Test
	public void testGetPawn() {
		Piece p1 = pf.getPawn("white");
		Piece p2 = pf.getPawn("black");
		
		assertEquals(p1.getName(),"pawn");
		assertEquals(p1.getColor(),"white");
		
		assertEquals(p2.getName(),"pawn");
		assertEquals(p2.getColor(),"black");
		
		try {
			pf.getPawn("red");
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("没有这种颜色"));
		}
		
		try {
			pf.getPawn(null);
		}catch(AssertionError e) {
			assertTrue(e.getMessage().equals("棋的颜色不能为空"));
		}
	}
}
