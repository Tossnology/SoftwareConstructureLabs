/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	//  AF(vertices,edges)=һ����Ȩ����ͼ
	// Representation invariant:
	//  vertices:ͼ�е�ļ��ϣ���������Ϊ����L
	//  edges:ͼ�бߵ��б�û���ظ�
	// Safety from rep exposure:
	//  ��֤����private��ͨ��һϵ��mutator�޸�

	// TODO constructor
	public ConcreteEdgesGraph(){
		checkRep();
	}
	// TODO checkRep
	public void checkRep() {
		assert edges.size()==new HashSet<Edge<L>>(edges).size();
	}

	@Override
	public boolean add(L vertex) {
		if (!vertices.contains(vertex)) {
			vertices.add(vertex);
			checkRep();
			return true;
		} else {
			checkRep();
			return false;
		}
	}

	@Override
	public int set(L source, L target, int weight) {
		add(source);
		add(target);
		for (Edge<L> e : edges) {
			if (e.getSource().equals(source) && e.getTarget().equals(target)) {
				edges.remove(e);
				if (weight != 0) {
					edges.add(new Edge<L>(source, target, weight));
				}
				checkRep();
				return weight;
			}
		}
		if(weight!=0) {
			edges.add(new Edge<L>(source, target, weight));
		}
		checkRep();
		return weight;
	}

	@Override
	public boolean remove(L vertex) {
		if (vertices.contains(vertex)) {
			ArrayList<Edge<L>> t = new ArrayList<Edge<L>>();
			for (Edge<L> e : edges) {
				//System.out.println(e.getSource()+" "+e.getTarget()+" "+e.getWeight());
				if (e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
					t.add(e);
				}
			}
			edges.removeAll(t);
			vertices.remove(vertex);
			checkRep();
			return true;
		}
		checkRep();
		return false;
	}

	@Override
	public Set<L> vertices() {
		Set<L> result = new HashSet<L>();
		for (L v : vertices) {
			result.add(v);
		}
		checkRep();
		return result;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> result = new HashMap<L, Integer>();
		for (Edge<L> e : edges) {
			if (e.getTarget().equals(target)) {
				result.put(e.getSource(), e.getWeight());
			}
		}
		checkRep();
		return result;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> result = new HashMap<L, Integer>();
		for (Edge<L> e : edges) {
			if (e.getSource().equals(source)) {
				result.put(e.getTarget(), e.getWeight());
			}
		}
		checkRep();
		return result;
	}

	// TODO toString()
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (L s : vertices) {
			result.append(s);
			result.append("-->");
			result.append("{");
			for (int i = 0;i<targets(s).keySet().size();i++) {
				result.append("(");
				result.append(targets(s).keySet().toArray()[i]);
				result.append(":");
				result.append(targets(s).get(targets(s).keySet().toArray()[i]));
				result.append(")");
				if(i!=targets(s).keySet().size()-1) {
					result.append(",");
				}
			}
			result.append("}\n");
		}
		checkRep();
		return result.toString();
	}
}

/**
 * ͼ�ıߣ�����㣬�յ㣬Ȩ����ɣ����������յ�����Ѿ����ڣ�Ȩ�ر���Ϊ������
 * Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	// TODO fields
	private L source;
	private L target;
	private int weight;

	// Abstraction function:
	//  AF(source,target,weight)=һ����������յ��Ȩ�صı�
	// Representation invariant:
	//  source:L���ͣ��ǿ�
	//  target:L���ͣ��ǿ�
	//  weight:������
	// Safety from rep exposure:
	// ���Զ�Ϊprivate��ֹй©�����Ҫ����ͨ��get������ֹ�޸�

	// TODO constructor
	/**
	 * ��һ��������Ȩ��
	 * @param source ����ǩ���ǿգ����������vertices
	 * @param target �յ��ǩ���ǿգ����������vertices
	 * @param weight Ȩ�أ�������
	 */
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}

	// TODO checkRep
	/**
	 * ��������Ƿ�Ϸ�
	 * 1.source�ǿ�
	 * 2.target�ǿ�
	 * 3.Ȩ��Ϊ������
	 */
	public void checkRep() {
		assert source!=null;
		assert target!=null;
		assert weight>0;
	}
	
	// TODO methods
	/**
	 * ���ʱߵ����
	 * @return �ñߵ�����ǩ
	 */
	public L getSource() {
		checkRep();
		return source;
	}

	/**
	 * �����յ�
	 * @return �ñߵ��յ��ǩ
	 */
	public L getTarget() {
		checkRep();
		return target;
	}

	/**
	 * ����Ȩ��
	 * @return �ñߵ�Ȩ��
	 */
	public int getWeight() {
		checkRep();
		return weight;
	}
	
	// TODO toString()
	/**
	 * ����ת��Ϊ�ַ���
	 * @return �ߵ��ַ�����ʾ
	 */
	@Override
	public String toString() {
		checkRep();
		return source+"-->"+target+" "+"weight="+weight;
	}
}
