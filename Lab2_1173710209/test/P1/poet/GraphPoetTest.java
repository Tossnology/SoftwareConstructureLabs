/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   true,false
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void GraphPoetTest() throws IOException {
    	GraphPoet gp = new GraphPoet(new File("src/P1/poet/corpus.txt"));
    	assertTrue(gp.getGraph().vertices().containsAll(Arrays.asList("new","worlds","explore","and","to","civilizations","seek","strange","life","out"))&&gp.getGraph().vertices().size()==10);
    }
    
    @Test
    public void poemTest() throws IOException {
    	GraphPoet gp = new GraphPoet(new File("src/P1/poet/corpus.txt"));
    	assertEquals(gp.poem("Seek to explore new and exciting synergies!"),"Seek to explore strange new life and exciting synergies!");
    }
    
    @Test
    public void toStringTest() throws IOException {
    	GraphPoet gp = new GraphPoet(new File("src/P1/poet/corpus.txt"));
    	System.out.println(gp.toString());
    	assertEquals(gp.toString(),"new-->{(worlds:1),(civilizations:1),(life:1)}\n" + 
    			"worlds-->{(to:1)}\n" + 
    			"explore-->{(strange:1)}\n" + 
    			"and-->{(new:1)}\n" + 
    			"to-->{(explore:1),(seek:1)}\n" + 
    			"civilizations-->{}\n" + 
    			"seek-->{(out:1)}\n" + 
    			"strange-->{(new:1)}\n" + 
    			"life-->{(and:1)}\n" + 
    			"out-->{(new:1)}\n" );
    }
}
