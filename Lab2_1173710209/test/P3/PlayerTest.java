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
			assertEquals(e.getMessage(), "�������ֲ���Ϊ��");
		}
		
		try {
			new Player("jojo",null);
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "���ֲ���û����ɫ");
		}
		
		try {
			new Player("sekiro","orange");
		}catch(AssertionError e) {
			assertEquals(e.getMessage(), "������ɫֻ���ǺںͰ�");
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
