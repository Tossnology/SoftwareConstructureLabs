package P3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FriendshipGraph {
	ArrayList<Person> vertex = new ArrayList<>();

	public static void main(String args[]) {
		FriendshipGraph graph = new FriendshipGraph();
		while(true) {
			System.out.println("请输入姓名(输入-1结束):");
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			if(s.equals("-1")) {
				break;
			}
			Person p = new Person(s);
			graph.addVertex(p);
		}
		while(true) {
			System.out.println("请输入一对朋友(输入-1结束):");
			Scanner in = new Scanner(System.in);
			String s1 = in.nextLine();
			if(s1.equals("-1")) {
				break;
			}
			String s2 = in.nextLine();
			Person p1=null,p2=null;
			for(Person p: graph.vertex) {
				if(p.getName().equals(s1)) {
					p1 = p;
				}
				if(p.getName().equals(s2)) {
					p2 = p;
				}
			}
			graph.addEdge(p1, p2);
		}
		System.out.println("输入两个姓名查看二者关系:");
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine();
		String s2 = in.nextLine();
		Person p1=null,p2=null;
		for(Person p: graph.vertex) {
			if(p.getName().equals(s1)) {
				p1 = p;
			}
			if(p.getName().equals(s2)) {
				p2 = p;
			}
		}
		System.out.println("二者距离为:"+graph.getDistance(p1, p2));
	}

	void addVertex(Person person) {
		int flag = 1;
		for(Person p: this.vertex) {
			if (person.getName().equals(p.getName())){
				flag = 0;
				break;
			}
		}
		if(flag == 0) {
			System.out.println(person.getName()+"已存在");
			System.exit(0);
		}
		else {
			this.vertex.add(person);
		}
	}

	void addEdge(Person person1, Person person2) {
		if(contains(person1,person2)) {
			person1.friends.add(person2);
		}else
		{
			System.exit(0);
		}
	}

	int getDistance(Person person1, Person person2) {
			for(Person p:this.vertex) {
				p.dist = -1;
			}
			Queue<Person> q = new LinkedList<Person>();
			person1.dist = 0;
			q.add(person1);
			while (!q.isEmpty()) {
				Person p1 = q.poll();
				if (p1==person2 ) {
					return p1.dist;
				}
				for (Person p2 : p1.friends) {
					if (p2.dist == -1) {
						p2.dist = p1.dist + 1;
						q.add(p2);
					}
				}
			}
			return -1;
	}
	
	boolean contains(Person person1,Person person2) {
		int flag1 = 0,flag2 = 0;
		for(Person p:this.vertex) {
			if(p.getName().equals(person1.getName())) {
				flag1 = 1;
			}
			if(p.getName().equals(person2.getName())) {
				flag2 = 1;
			}
		}
		if(flag1==0||flag2==0) {
			if(flag1 == 0) {
				System.out.println(person1.getName()+"不存在");
			}
			if(flag2 == 0) {
				System.out.println(person2.getName()+"不存在");
			}
			return false;
		}
		return true;
	}
}