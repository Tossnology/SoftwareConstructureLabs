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
 * ��д��Lab1�е�FriendshipGraph�������˱�ʵ���е�Graph
 * 
 * @author yRXX
 *
 */
public class FriendshipGraph {
	private final Graph<Person> graph = Graph.empty();
	
	// Abstraction function:
	//  AF(graph)=һ����ϵ����ͼ
	// Representation invariant:
	//  graph:��ʼͼ����ǩ����ΪPerson
	// Safety from rep exposure:
	//  ��֤����private��ͨ��һϵ��mutator�޸�

	/**
	 * �ͻ���
	 * ��������Ӧ�ú���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		while (true) {
			System.out.println("����������(����-1����):");
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			if (s.equals("-1")) {
				break;
			}
			Person p = new Person(s);
			graph.addVertex(p);
		}
		while (true) {
			System.out.println("������һ������(����-1����):");
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
		System.out.println("�������������鿴���߹�ϵ:");
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
		System.out.println("���߾���Ϊ:" + graph.getDistance(p1, p2));
	}

	/**
	 * ��ӽڵ�
	 * ����Ѵ������˳�����
	 * 
	 * @param person Ҫ��ӵĽڵ��ǩ
	 */
	public void addVertex(Person person) {
		if (!graph.add(person)) {
			System.out.println(person.getName() + "�Ѵ���");
			System.exit(0);
		}
	}

	/**
	 * ��ӱ�
	 * ����в����ڵ㲻��ͼ�У����˳�����
	 * 
	 * @param person1 ����ǩ
	 * @param person2 �յ��ǩ
	 */
	public void addEdge(Person person1, Person person2) {
		int flag = 1;
		if (!graph.vertices().contains(person1)) {
			System.out.println(person1.getName() + "������");
			flag = 0;
		}
		if (!graph.vertices().contains(person2)) {
			System.out.println(person2.getName() + "������");
			flag = 0;
		}

		if (flag == 0) {
			System.exit(0);
		} else {
			graph.set(person1, person2, 1);
		}
	}

	/**
	 * ��ȡ���߾��룬����BFS�㷨
	 * 
	 * @param person1 ��ǩ1
	 * @param person2 ��ǩ2
	 * @return ������̾���
	 *         �������һ��������0
	 *         ��������ڵ��ߣ�����-1
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
	 * ��ȡgraph
	 * 
	 * @return graph����
	 */
	public Graph<Person> getGraph() {
		return graph;
	}
}
