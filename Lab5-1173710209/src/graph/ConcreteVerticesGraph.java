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
  // AF(vertices)=һ����Ȩ����ͼ
  // Representation invariant:
  // vertices:ͼ�нڵ���б��������ظ�
  // Safety from rep exposure:
  // ��֤����private��ͨ��һϵ��mutator�޸�

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
 * ͼ�еĽڵ�Ԫ�أ�����һЩ��Ϣ (�ڵ����֣���ָ��ĵ��ڽӵ��Ȩ��) Mutable. This class is internal to the rep
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
  // AF(name,targets)=ͼ�е�һ�������һЩ��Ϣ(�������֣���ָ��ĵ��ڽӵ��Ȩ��)
  // Representation invariant:
  // name:L���ͣ��ǿ�
  // targets:Map<L,Integer>���ͣ�keyΪָ����ڽӵ�����֣�valueΪȨ�أ�key�����ظ���valueΪ������
  // Safety from rep exposure:
  // ����Ϊmutable����Ϊ�˷�ֹй©��������һϵ��mutator����

  // TODO constructor
  /**
   * ��һ�������ڵ�.
   * 
   * @param name
   *          �ڵ����֣��ǿ�
   */
  public Vertex(L name) {
    this.name = name;
    targets = new HashMap<L, Double>();
    checkRep();
  }

  /**
   * ��������Ƿ�Ϸ� 1.���ַǿ� 2.���ظ�Ԫ�� 3.Ȩ�ض�Ϊ������
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
   * �õ��ڵ�����.
   * 
   * @return �ڵ�����
   */
  public L getName() {
    checkRep();
    return name;
  }

  /**
   * �Ͼ��ڽӹ�ϵ(ɾ��һ����).
   * 
   * @param name
   *          �ڽӵ�����֣��ǿգ���Ӧ�ڵ���������vertices��
   */
  public void rmTarget(L name) {
    targets.remove(name);
    checkRep();
  }

  /**
   * �����ڽӵ㼯��.
   * 
   * @return �ڽӵ㼯��
   */
  public Set<L> Targets() {
    Set<L> result = targets.keySet();
    checkRep();
    return result;
  }

  /**
   * �����ڽӹ�ϵ(����һ����).
   * 
   * @param name
   *          �ڽӵ����֣��ǿգ���Ӧ�ڵ���������vertices��
   * @param weight
   *          Ȩ�أ�������
   */
  public void putTarget(L name, double weight) {
    targets.put(name, weight);
    checkRep();
  }

  /**
   * ��ȡȨ��.
   * 
   * @param name
   *          �ڽӵ����֣��ǿգ���Ӧ�ڵ���������vertices��
   * @return ��Ӧ�ߵ�Ȩ��
   */
  public double getWeight(L name) {
    checkRep();
    return targets.get(name);
  }

  /**
   * ��ȡ�����ڽӱ�.
   * 
   * @return �ڽӱ�Map
   */
  public Map<L, Double> getTargets() {
    checkRep();
    return targets;
  }
  // TODO toString()

  /**
   * ��ͼת��Ϊ�ַ���.
   * 
   * @return ͼ���ַ�����ʾ
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
