package P3;

import java.util.ArrayList;

public class Person {
	private String name;
	public ArrayList<Person> friends;
	int dist;//��ԭ��ľ���
	
	public Person(String name) {
		this.name = name;
		this.friends = new ArrayList<>();
		this.dist = -1;
	}
	
	String getName() {
		return this.name;
	}
}
