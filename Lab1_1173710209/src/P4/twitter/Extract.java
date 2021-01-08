/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import turtle.Point;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
    	tweets.sort(new TimestampComparator());
    	Timespan result = new Timespan(tweets.get(0).getTimestamp(),tweets.get(tweets.size()-1).getTimestamp());
    	return result;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
    	Set<String> result = new HashSet();
    	
    	for(Tweet t:tweets) {
    		String text = t.getText();
    		String[] words = text.split(" ");
    		for(String s :words) {
    			if(s.charAt(0)=='@') {
    				s = s.substring(1);
    				s = s.toLowerCase();
    				int flag = 1;
    				if(s.isEmpty()) {
    					flag = 0;
    				}
    				for(char chr:s.toCharArray()) {
    					if(chr<45||(chr>45&&chr<48)||(chr>57&&chr<65)||(chr>90&&chr<95)||(chr>95&&chr<97)||chr>122) {
    						flag = 0;
    						break;
    					}
    				}
    				if(flag==1&&!result.contains(s)) {
    					result.add(s);
    				}
    			}
    		}
    	}
    	return result;
    }
}
class TimestampComparator implements Comparator<Tweet> {
	public int compare(Tweet t1, Tweet t2) {
		if (t1.getTimestamp().isAfter(t2.getTimestamp())) {
			return 1;
		} else {
			return -1;
		}
	}
}