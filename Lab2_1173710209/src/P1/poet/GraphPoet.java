/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   AF(graph)=一个基于语料库构建的词句关系图
    // Representation invariant:
    //   graph中的节点标签非空，不区分大小写，不能有空格，换行符
    //   边的权重都为1
    // Safety from rep exposure:
    //   private final
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	String line;
    	ArrayList<String> allWords = new ArrayList<String>();
    	
    	try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(corpus)))){
    		while((line = in.readLine()) != null) {
    			String[] words = line.split(" ");
    			for(int i = 0;i<words.length;i++) {
    				allWords.add(words[i].toLowerCase());
    			}
    		}
    	}
    	
    	for(int i=0;i<allWords.size()-1;i++) {
			graph.set(allWords.get(i), allWords.get(i+1), 1);
		}
    	checkRep();
    }
    
    // TODO checkRep
    public void checkRep() {
    	//节点
    	for(String s:graph.vertices()) {
    		//非空
    		assert s!=null;
    		
    		//合法
    		assert !s.contains(" ")&&!s.contains("\n");
    		
    		//唯一
    		int count = 0;
    		for(String t:graph.vertices()) {
    			if(t.toLowerCase().equals(s.toLowerCase())) {
    				count++;
    			}
    		}
    		assert count==1;
    	}
    	
    	//边
    	for(String s:graph.vertices()) {
    		//权重都为1
    		for(Integer w:graph.targets(s).values()) {
    			assert w==1;
    		}
    	}
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	StringBuilder result = new StringBuilder();
    	String[] words = input.split(" ");
    	for(int i = 0;i<words.length-1;i++) {
    		result.append(words[i]+" ");
    		for(String w:bridges(words[i].toLowerCase(),words[i+1].toLowerCase())) {
    			result.append(w+" ");
    		}
    	}
    	result.append(words[words.length-1]);
    	return result.toString();
    }
    
    /**
     * 找出两个单词中间的bridge
     * 
     * @param source
     * @param target
     * @return 两点间bridge的集合
     */
    private Set<String> bridges(String source,String target){
    	Set<String> result = new HashSet<String>();
    	for(String v: graph.vertices()) {
    		if(graph.sources(v).containsKey(source)&&graph.targets(v).containsKey(target)) {
    			result.add(v);
    		}
    	}
    	return result;
    }
    
    // TODO toString()
    /**
     * 将GraphPoet转化成字符串
     * @return GraphPoet的字符串形式
     */
    @Override
    public String toString() {
    	return graph.toString();
    }
    
    /**
     * 访问graph
     * @return graph
     */
    public Graph<String> getGraph(){
    	return graph;
    }
}
