/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   ��Ҫ����add,set,remove����������������set�д���������������ڲ��Ժ���������
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    @Test
    public void testAdd() {
    	Graph<String> g = emptyInstance();
    	
    	assertTrue(g.add("first"));//�ռ���Ӽ�Ԫ��
    	assertEquals(g.vertices().size(),1);
    	
    	assertFalse(g.add("first"));//����ظ�Ԫ��
    	
    	assertTrue(g.add("second"));//�ǿռ����Ԫ��
    	assertEquals(g.vertices().size(),2);
    }
    
    @Test
    public void testSet() {
    	Graph<String> g = emptyInstance();
    	
    	//��ӵ�һ����(���������ڵĵ�)
    	assertEquals(g.set("a", "b", 3),3);
    	assertTrue(g.vertices().containsAll(Arrays.asList("a","b")));
    	assertEquals(g.vertices().size(),2);
    	
    	assertEquals(g.sources("a").keySet(),Collections.emptySet());
    	assertTrue(g.sources("b").keySet().contains("a")&&g.sources("b").keySet().size()==1);
    	assertEquals(g.sources("b").get("a"),Integer.valueOf(3));
    	
    	assertEquals(g.targets("b").keySet(),Collections.emptySet());
    	assertTrue(g.targets("a").keySet().contains("b")&&g.targets("a").keySet().size()==1);
    	assertEquals(g.targets("a").get("b"),Integer.valueOf(3));
    	
    	//��ӵڶ�����(source���ڣ�target������)
    	assertEquals(g.set("a", "c", 2),2);
    	assertTrue(g.vertices().containsAll(Arrays.asList("a","b","c")));
    	assertEquals(g.vertices().size(),3);
    	
    	assertEquals(g.sources("a").keySet(),Collections.emptySet());
    	assertTrue(g.sources("b").keySet().contains("a")&&g.sources("b").keySet().size()==1&&g.sources("b").get("a")==3);
    	assertTrue(g.sources("c").keySet().contains("a")&&g.sources("c").keySet().size()==1&&g.sources("c").get("a")==2);
    	
    	assertTrue(g.targets("a").keySet().containsAll(Arrays.asList("b","c"))&&g.targets("a").keySet().size()==2&&g.targets("a").get("b")==3&&g.targets("a").get("c")==2);
    	assertEquals(g.targets("b").keySet(),Collections.emptySet());
    	assertEquals(g.targets("c").keySet(),Collections.emptySet());
    	
    	//��ӵ�������(source�����ڣ�target����)
    	assertEquals(g.set("d", "c", 1),1);
    	assertTrue(g.vertices().containsAll(Arrays.asList("a","b","c","d")));
    	assertEquals(g.vertices().size(),4);
    	
    	assertTrue(g.sources("c").keySet().containsAll(Arrays.asList("a","d"))&&g.sources("c").keySet().size()==2&&g.sources("c").get("d")==1);
    	
    	//�޸ı߳�
    	assertEquals(g.set("a", "b", 5),5);
    	assertTrue(g.vertices().containsAll(Arrays.asList("a","b","c","d")));
    	assertEquals(g.vertices().size(),4);
    	
    	assertEquals(g.sources("b").get("a"),Integer.valueOf(5));
    	assertEquals(g.targets("a").get("b"),Integer.valueOf(5));
    	
    	//ɾ��һ����
    	assertEquals(g.set("a", "b", 0),0);
    	assertTrue(g.vertices().containsAll(Arrays.asList("a","b","c","d")));
    	assertEquals(g.vertices().size(),4);
    	
    	assertTrue(g.targets("a").keySet().size()==1);
    	assertEquals(g.sources("b").keySet(),Collections.emptySet());
    }
    
    @Test
    public void testRemove() {
    	Graph<String> g = emptyInstance();
    	g.set("a", "b", 3);
    	g.set("a", "c", 2);
    	
    	
    	assertTrue(g.remove("a"));
    	assertTrue(g.vertices().containsAll(Arrays.asList("b","c"))&&g.vertices().size()==2);
    	assertEquals(g.sources("b").keySet(),Collections.emptySet());
    	assertEquals(g.targets("b").keySet(),Collections.emptySet());
    	assertEquals(g.sources("c").keySet(),Collections.emptySet());
    	assertEquals(g.targets("c").keySet(),Collections.emptySet());
    	
    	assertFalse(g.remove("a"));
    }
}
