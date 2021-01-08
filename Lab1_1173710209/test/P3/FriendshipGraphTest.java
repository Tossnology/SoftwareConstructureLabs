package P3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FriendshipGraphTest {
	Person rachel = new Person("Rachel");
	Person ross = new Person("Ross");
	Person ben = new Person("Ben");
	Person kramer = new Person("Kramer");
	Person peter = new Person("Peter");
	Person bob = new Person("Bob");
	@Test
	public void addVertexTest() {
		FriendshipGraph graph = new FriendshipGraph();
		ArrayList<Person> result = new ArrayList<>();
		
		graph.addVertex(rachel);
		result.add(rachel);
		assertEquals(graph.vertex,result);
		
		graph.addVertex(ben);
		result.add(ben);
		assertEquals(graph.vertex,result);
		
		//若检测到重复的点时不退出程序，可以用下面代码测试
		//graph.addVertex(rachel);
		//assertEquals(graph.vertex,result);
	}
	@Test
	public void addEdgeTest() {
		FriendshipGraph graph = new FriendshipGraph();
		ArrayList<Person> result1 = new ArrayList<>();
		ArrayList<Person> result2 = new ArrayList<>();
		graph.addVertex(ben);
		graph.addVertex(bob);
		graph.addEdge(ben, bob);
		result1.add(bob);
		graph.addEdge(bob, ben);
		result2.add(ben);
		assertEquals(ben.friends,result1);
		assertEquals(bob.friends,result2);
	}
	@Test
	public void getDistanceTest() {
		FriendshipGraph graph = new FriendshipGraph();
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ben, ross);
		graph.addEdge(ross, ben);
		assertEquals(1,graph.getDistance(rachel, ross));
		assertEquals(2,graph.getDistance(rachel, ben));
		assertEquals(0,graph.getDistance(rachel, rachel));
		assertEquals(-1,graph.getDistance(rachel, kramer));
		assertEquals(-1,graph.getDistance(rachel, peter));
		assertEquals(-1,graph.getDistance(peter, bob));
	}

}
