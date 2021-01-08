package P2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import P2.Person;
import graph.Graph;

/**
 * 重写了Lab1中的FriendshipGraph，复用了本实验中的Graph
 * 
 * @author yRXX
 *
 */
public class FriendshipGraph {
	private final Graph<Person> graph = Graph.empty();
	
	// Abstraction function:
	//  AF(graph)=一个关系网络图
	// Representation invariant:
	//  graph:初始图，标签类型为Person
	// Safety from rep exposure:
	//  保证属性private，通过一系列mutator修改

	/**
	 * 客户端
	 * 所有输入应该合理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		while (true) {
			System.out.println("请输入姓名(输入-1结束):");
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.equals("-1")) {
				break;
			}
			Person p = new Person(s);
			graph.addVertex(p);
		}
		while (true) {
			System.out.println("请输入一对朋友(输入-1结束):");
			Scanner in = new Scanner(System.in);
			String s1 = in.nextLine();
			if (s1.equals("-1")) {
				break;
			}
			String s2 = in.nextLine();
			Person p1 = null, p2 = null;
			for (Person p : graph.getGraph().vertices()) {
				if (p.getName().equals(s1)) {
					p1 = p;
				}
				if (p.getName().equals(s2)) {
					p2 = p;
				}
			}
			graph.addEdge(p1, p2);
			graph.addEdge(p2, p1);
		}
		System.out.println("输入两个姓名查看二者关系:");
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine();
		String s2 = in.nextLine();
		Person p1 = null, p2 = null;
		for (Person p : graph.getGraph().vertices()) {
			if (p.getName().equals(s1)) {
				p1 = p;
			}
			if (p.getName().equals(s2)) {
				p2 = p;
			}
		}
		System.out.println("二者距离为:" + graph.getDistance(p1, p2));
	}

	/**
	 * 添加节点
	 * 如果已存在则退出程序
	 * 
	 * @param person 要添加的节点标签
	 */
	public void addVertex(Person person) {
		if (!graph.add(person)) {
			System.out.println(person.getName() + "已存在");
			System.exit(0);
		}
	}

	/**
	 * 添加边
	 * 如果有参数节点不在图中，则退出程序
	 * 
	 * @param person1 起点标签
	 * @param person2 终点标签
	 */
	public void addEdge(Person person1, Person person2) {
		int flag = 1;
		if (!graph.vertices().contains(person1)) {
			System.out.println(person1.getName() + "不存在");
			flag = 0;
		}
		if (!graph.vertices().contains(person2)) {
			System.out.println(person2.getName() + "不存在");
			flag = 0;
		}

		if (flag == 0) {
			System.exit(0);
		} else {
			graph.set(person1, person2, 1);
		}
	}

	/**
	 * 获取二者距离，采用BFS算法
	 * 
	 * @param person1 标签1
	 * @param person2 标签2
	 * @return 二者最短距离
	 *         如果参数一样，返回0
	 *         如果不存在点或边，返回-1
	 */
	public int getDistance(Person person1, Person person2) {
		Queue<Person> q = new LinkedList<Person>();
		Map<Person, Integer> m = new HashMap<Person, Integer>();
		for (Person p : graph.vertices()) {
			if (p.equals(person1)) {
				m.put(p, 0);
				q.add(p);
			} else {
				m.put(p, -1);
			}
		}
		while (!q.isEmpty()) {
			Person p1 = q.poll();
			if (p1.equals(person2)) {
				return m.get(p1);
			}
			for (Person p2 : graph.targets(p1).keySet()) {
				if (m.get(p2) == -1) {
					m.put(p2, m.get(p1) + 1);
					q.add(p2);
				}
			}
		}
		return -1;
	}

	/**
	 * 获取graph
	 * 
	 * @return graph属性
	 */
	public Graph<Person> getGraph() {
		return graph;
	}
}
