// This searches for pattern in string using BoyerMoore search algo using the bad-character skip array
// and returns the positions of all matches

package com.yelp;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
	
	private final int R=256;     
	// the bad-character skip array
	private int[] right;    
	
	private String pat;      

	// pattern provided as a string
	public void preprocess(String pat){
		// position of rightmost occurrence of c in the pattern
		this.pat = pat;
		right = new int[R];
		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 0; j < pat.length(); j++)
			right[pat.charAt(j)] = j;

	}

	// return positions of all matches
	public  ArrayList<Integer> search(String txt) {
		int M = pat.length();
		int N = txt.length();
		ArrayList<Integer> newArrayInt = new ArrayList<Integer>();
		int skip;
		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (int j = M-1; j >= 0; j--) {
				if (pat.charAt(j) != txt.charAt(i+j)) {
					skip = Math.max(1, j - right[txt.charAt(i+j)]);
					break;
				}
			}
			// if value found
			if (skip == 0) 
			{
				newArrayInt.add(i);    
				skip++;
			}
		}
		return newArrayInt;                       
	}


}