package P2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FriendshipGraphTest {
	
	// Testing strategy
    //   true,false
	
	Person rachel = new Person("Rachel");
	Person ross = new Person("Ross");
	Person ben = new Person("Ben");
	Person kramer = new Person("Kramer");
	Person peter = new Person("Peter");
	Person bob = new Person("Bob");
	@Test
	public void addVertexTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Set<Person> result = new HashSet<Person>();
		
		graph.addVertex(rachel);
		result.add(rachel);
		assertEquals(graph.getGraph().vertices(),result);
		
		graph.addVertex(ben);
		result.add(ben);
		assertEquals(graph.getGraph().vertices(),result);
		
		//若检测到重复的点时不退出程序，可以用下面代码测试
		//graph.addVertex(rachel);
		//assertEquals(graph.vertex,result);
	}
	@Test
	public void addEdgeTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Set<Person> result1 = new HashSet<Person>();
		Set<Person> result2 = new HashSet<Person>();
		graph.addVertex(ben);
		graph.addVertex(bob);
		graph.addEdge(ben, bob);
		result1.add(bob);
		graph.addEdge(bob, ben);
		result2.add(ben);
		assertEquals(graph.getGraph().targets(ben).keySet(),result1);
		assertEquals(graph.getGraph().targets(bob).keySet(),result2);
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
