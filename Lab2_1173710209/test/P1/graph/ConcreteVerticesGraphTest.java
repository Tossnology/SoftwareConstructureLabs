/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // 给出几组边，看是否构成预期的字符串
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void toStringTest() {
    	Graph<String> g = emptyInstance();
    	g.set("a", "b", 3);
    	g.set("a", "c", 2);
    	g.set("d", "c", 1);
    	assertEquals(g.toString(),"a-->{(b:3),(c:2)}\n" + 
    			"b-->{}\n" + 
    			"c-->{}\n" + 
    			"d-->{(c:1)}\n");
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   给出预期结果进行对比
    
    // TODO tests for operations of Vertex
    @Test
    public void VertexTest() {
    	Vertex<String> v = new Vertex<String>("a");
    	assertEquals(v.getName(),"a");
    	assertEquals(v.getTargets(),Collections.emptyMap());
    	v.putTarget("b", 4);
    	assertTrue(v.getWeight("b")==4);
    	assertTrue(v.getTargets().containsKey("b")&&v.getTargets().containsValue(4)&&v.getTargets().size()==1);
    	assertEquals(v.toString(),"a-->{(b:4)}");
    	Set<String> s = new HashSet<String>();
    	s.add("b");
    	assertEquals(v.Targets(),s);
    	v.rmTarget("b");
    	assertEquals(v.getTargets(),Collections.emptyMap());
    }
}
