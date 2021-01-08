package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer() {
		new Player("yRXX","black");
		new Player("kit","white");
		
		try {
			new Player(null,"black");
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "棋手名字不能为空");
		}
		
		try {
			new Player("jojo",null);
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "棋手不能没有颜色");
		}
		
		try {
			new Player("sekiro","orange");
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "棋手颜色只能是黑和白");
		}
	}

	@Test
	public void testGetName() {
		Player p = new Player("yRXX","black");
		
		assertEquals(p.getName(),"yRXX");
	}
	
	@Test
	public void testGetColor() {
		Player p = new Player("yRXX","black");
		
		assertEquals(p.getColor(),"black");
	}
}
