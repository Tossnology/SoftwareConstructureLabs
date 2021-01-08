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
	//  AF(vertices,edges)=一个有权有向图
	// Representation invariant:
	//  vertices:图中点的集合，数据类型为泛型L
	//  edges:图中边的列表，没有重复
	// Safety from rep exposure:
	//  保证属性private，通过一系列mutator修改

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
 * 图的边，由起点，终点，权重组成，其中起点和终点必须已经存在，权重必须为正整数
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
	//  AF(source,target,weight)=一条具有起点终点和权重的边
	// Representation invariant:
	//  source:L类型，非空
	//  target:L类型，非空
	//  weight:正整数
	// Safety from rep exposure:
	// 属性都为private防止泄漏，如果要访问通过get方法防止修改

	// TODO constructor
	/**
	 * 造一条有向有权边
	 * @param source 起点标签，非空，必须包含在vertices
	 * @param target 终点标签，非空，必须包含在vertices
	 * @param weight 权重，正整数
	 */
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}

	// TODO checkRep
	/**
	 * 检查属性是否合法
	 * 1.source非空
	 * 2.target非空
	 * 3.权重为正整数
	 */
	public void checkRep() {
		assert source!=null;
		assert target!=null;
		assert weight>0;
	}
	
	// TODO methods
	/**
	 * 访问边的起点
	 * @return 该边的起点标签
	 */
	public L getSource() {
		checkRep();
		return source;
	}

	/**
	 * 访问终点
	 * @return 该边的终点标签
	 */
	public L getTarget() {
		checkRep();
		return target;
	}

	/**
	 * 访问权重
	 * @return 该边的权重
	 */
	public int getWeight() {
		checkRep();
		return weight;
	}
	
	// TODO toString()
	/**
	 * 将边转化为字符串
	 * @return 边的字符串表示
	 */
	@Override
	public String toString() {
		checkRep();
		return source+"-->"+target+" "+"weight="+weight;
	}
}
