/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    @Test
    public void testInt() {
    	Graph<Integer> g = new ConcreteEdgesGraph<Integer>();
    	g.add(10);
    	g.set(10, 20, 30);
    	assertEquals(g.toString(),"20-->{}\n" + 
    			"10-->{(20:30)}\n");
    }
    
    @Test
    public void testDouble() {
    	Graph<Double> g = new ConcreteVerticesGraph<Double>();
    	g.add(3.1);
    	g.set(7.7, 20.1, 30);
    	assertEquals(g.toString(),"3.1-->{}\n" + 
    			"7.7-->{(20.1:30)}\n" + 
    			"20.1-->{}\n" );

    }
}
