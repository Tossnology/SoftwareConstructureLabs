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
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

  private final List<Vertex<L>> vertices = new ArrayList<>();

  // Abstraction function:
  // AF(vertices)=一个有权有向图
  // Representation invariant:
  // vertices:图中节点的列表，不能有重复
  // Safety from rep exposure:
  // 保证属性private，通过一系列mutator修改

  // TODO constructor
  public ConcreteVerticesGraph() {
    checkRep();
  }

  // TODO checkRep
  public void checkRep() {
    assert vertices.size() == new HashSet<Vertex<L>>(vertices).size();
  }

  @Override
  public boolean add(L vertex) {
    for (Vertex<L> v : vertices) {
      if (v.getName().equals(vertex)) {
        checkRep();
        return false;
      }
    }
    vertices.add(new Vertex<L>(vertex));
    checkRep();
    return true;
  }

  @Override
  public double set(L source, L target, double weight) {
    add(source);
    add(target);
    for (Vertex<L> v : vertices) {
      if (v.getName().equals(source) && v.Targets().contains(target)) {

        for (L s : v.Targets()) {
          if (s.equals(target)) {
            v.rmTarget(s);// v.targets.remove(target);
            if (weight != 0) {
              v.putTarget(target, weight);
            }
            checkRep();
            return weight;
          }
        }
      }
    }
    if (weight != 0) {
      for (Vertex<L> v : vertices) {
        if (v.getName().equals(source)) {
          v.putTarget(target, weight);
        }
      }
    }
    checkRep();
    return weight;
  }

  @Override
  public boolean remove(L vertex) {
    int flag = 0;
    ArrayList<Vertex<L>> t = new ArrayList<Vertex<L>>();
    for (Vertex<L> v : vertices) {
      if (v.getName().equals(vertex)) {
        t.add(v);
        flag = 1;
        continue;
      }
      for (L s : v.Targets()) {
        if (s.equals(vertex)) {
          v.rmTarget(s);
          flag = 1;
          break;
        }
      }
    }
    vertices.removeAll(t);
    if (flag == 1) {
      checkRep();
      return true;
    } else {
      checkRep();
      return false;
    }
  }

  @Override
  public Set<L> vertices() {
    Set<L> result = new HashSet<L>();
    for (Vertex<L> v : vertices) {
      result.add(v.getName());
    }
    checkRep();
    return result;
  }

  @Override
  public Map<L, Double> sources(L target) {
    Map<L, Double> result = new HashMap<L, Double>();
    for (Vertex<L> v : vertices) {
      for (L s : v.Targets()) {
        if (s.equals(target)) {
          result.put(v.getName(), v.getWeight(s));
        }
      }
    }
    checkRep();
    return result;
  }

  @Override
  public Map<L, Double> targets(L source) {
    Map<L, Double> result = new HashMap<L, Double>();
    for (Vertex<L> v : vertices) {
      if (v.getName().equals(source)) {
        result = v.getTargets();
        break;
      }
    }
    checkRep();
    return result;
  }

  // TODO toString()
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (Vertex<L> v : vertices) {
      result.append(v.getName());
      result.append("-->");
      result.append("{");
      for (int i = 0; i < v.Targets().size(); i++) {
        result.append("(");
        result.append(v.Targets().toArray()[i]);
        result.append(":");
        result.append(v.getWeight((L) v.Targets().toArray()[i]));
        result.append(")");
        if (i != v.Targets().size() - 1) {
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
 * 图中的节点元素，包括一些信息 (节点名字，它指向的的邻接点和权重) Mutable. This class is internal to the rep
 * of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

  // TODO fields
  private L name;
  private Map<L, Double> targets;

  // Abstraction function:
  // AF(name,targets)=图中的一个顶点的一些信息(顶点名字，它指向的的邻接点和权重)
  // Representation invariant:
  // name:L类型，非空
  // targets:Map<L,Integer>类型，key为指向的邻接点的名字，value为权重，key不能重复，value为正整数
  // Safety from rep exposure:
  // 该类为mutable，但为了防止泄漏，加入了一系列mutator方法

  // TODO constructor
  /**
   * 造一个独立节点.
   * 
   * @param name
   *          节点名字，非空
   */
  public Vertex(L name) {
    this.name = name;
    targets = new HashMap<L, Double>();
    checkRep();
  }

  /**
   * 检查属性是否合法 1.名字非空 2.无重复元素 3.权重都为正整数
   */
  public void checkRep() {
    assert name != null;
    assert targets.size() == new HashSet<L>(targets.keySet()).size();
    for (L t : targets.keySet()) {
      assert targets.get(t) > 0;
    }
  }

  // TODO methods
  /**
   * 得到节点名字.
   * 
   * @return 节点名字
   */
  public L getName() {
    checkRep();
    return name;
  }

  /**
   * 断绝邻接关系(删除一条边).
   * 
   * @param name
   *          邻接点的名字，非空，对应节点必须包含在vertices中
   */
  public void rmTarget(L name) {
    targets.remove(name);
    checkRep();
  }

  /**
   * 访问邻接点集合.
   * 
   * @return 邻接点集合
   */
  public Set<L> Targets() {
    Set<L> result = targets.keySet();
    checkRep();
    return result;
  }

  /**
   * 新增邻接关系(新增一条边).
   * 
   * @param name
   *          邻接点名字，非空，对应节点必须包含在vertices中
   * @param weight
   *          权重，正整数
   */
  public void putTarget(L name, double weight) {
    targets.put(name, weight);
    checkRep();
  }

  /**
   * 获取权重.
   * 
   * @param name
   *          邻接点名字，非空，对应节点必须包含在vertices中
   * @return 对应边的权重
   */
  public double getWeight(L name) {
    checkRep();
    return targets.get(name);
  }

  /**
   * 获取所有邻接边.
   * 
   * @return 邻接边Map
   */
  public Map<L, Double> getTargets() {
    checkRep();
    return targets;
  }
  // TODO toString()

  /**
   * 将图转化为字符串.
   * 
   * @return 图的字符串表示
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(name);
    result.append("-->");
    result.append("{");
    for (int i = 0; i < targets.size(); i++) {
      result.append("(");
      result.append(targets.keySet().toArray()[i]);
      result.append(":");
      result.append(targets.get(targets.keySet().toArray()[i]));
      result.append(")");
      if (i != targets.size() - 1) {
        result.append(",");
      }
    }
    result.append("}");
    checkRep();
    return result.toString();
  }

}
