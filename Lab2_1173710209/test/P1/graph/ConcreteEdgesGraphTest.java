/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   给出期望的结果，进行对比
    
    // TODO tests for ConcreteEdgesGraph.toString()
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
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   给出期望的结果，进行对比
    
    // TODO tests for operations of Edge
    @Test
    public void EdgeTest() {
    	Edge<String> e = new Edge<String>("a","b",4);
    	assertEquals(e.getSource(),"a");
    	assertEquals(e.getTarget(),"b");
    	assertEquals(e.getWeight(),4);
    	assertEquals(e.toString(),"a-->b weight=4");
    }
}
