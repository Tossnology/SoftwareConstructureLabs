package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TopVotedCandidateTest {

	 @Test
	    public void test1() {
	        int[] persons = {0,1,1,0,0,1,0};
	        int[] times = {0,5,10,15,20,25,30};
	        TopVotedCandidate top = new TopVotedCandidate(persons,times);
	        System.out.println(top.q(3)+","+top.q(12)+","+top.q(25)+","+top.q(15)+","+top.q(24)+","+top.q(8));
	        assertEquals(0,top.q(3));
	        assertEquals(1,top.q(12));
	        assertEquals(1,top.q(25));
	        assertEquals(0,top.q(15));
	        assertEquals(0,top.q(24));
	        assertEquals(1,top.q(8));
	 }

}
