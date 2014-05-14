// This is main class that runs to generate snippets based on a given document string and query terms
package com.yelp;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	// extra characters to print along with pattern.
	public static final int extra = 40;
	public static final int snippetSize = 200;

	final static String starthl = "[[HIGHLIGHT]";
	final static String endhl = "[[ENDHIGHLIGHT]";


	// this function searches at what location is the pattern present and also calls the minwindow to 
	// check the minimum window in which all pattern would match
	public static String highlight_doc(String pat,String txt){

		if(pat == null || txt == null) return null;
		if(pat.length() >= txt.length()) return null;
		if(pat.length() < 2) return null;
		String sub;
		Search s = new Search();
		String[] patarray;
		// first search for entire pattern
		s.preprocess(pat);
		ArrayList<Integer> poslist = s.search(txt);
		// ie - if the whole pattern is not matched
		if(poslist.size() == 0){
			// ArrayList for individual words search from pattern
			ArrayList<ArrayList<Integer>> pl = new ArrayList<ArrayList<Integer>>();

			// pattern not found split and check for individual patterns
			patarray = pat.split(" ");

			// for each word call the search and add the list of positions
			for(String str: patarray){
				s.preprocess(str);
				ArrayList<Integer> temp = s.search(txt);
				// add only if that pattern word is found in original txt
				if(temp.size() > 0) pl.add(temp);
			}

			// if pattern not found
			if(pl.size()<1) return null;
			
			// get the min window
			int[] res = FindWindow.findMinWindow(pl);
			ArrayList<Integer> res1 = new ArrayList<Integer>();
			for(int j=0;j<res.length;j++){
				res1.add(pl.get(j).get(res[j]));
			}
			
			// send the array to print
			sub = printSnippet(res1,txt,patarray);

		}
		else{
			// whole pattern found;find min and max and print it..
			patarray = new String[]{pat};
			sub = printSnippet(poslist,txt,patarray);
		}

		return sub;
	}


	// print snippet for match
	public static String printSnippet(ArrayList<Integer> poslist,String txt,String[] pat){
		int patlength = lenMaxPattern(pat);
		if(patlength<0) return null;
		int min= Collections.min(poslist);	
		int max = Collections.max(poslist);
		int diff = max - min;
		
		int minext = min;
		int maxext = max;
		String sub=null;

		if(diff < snippetSize){
			int c = 0;
			while(minext>0  && c<extra){
				minext--; 
				c++;
			}
			c = 0;
			// take next 25 character
			while(maxext<txt.length() && c<extra){
				maxext++;
				c++;
			}
			
			// to avoid half words
			while(minext>0 && txt.charAt(minext)!= ' ') minext--;
			while(maxext<txt.length() && txt.charAt(maxext) != ' ') maxext++;

			// print values depending on its pos
			if((minext)>0 && ((maxext) < txt.length()))
			{ 
				sub = txt.substring(minext,maxext);
			}
			else if(minext>0 && (maxext) > txt.length()){
				sub = txt.substring(minext,maxext--);
			}
			else if(minext<0 && (maxext) < txt.length()){
				sub = txt.substring(minext++,maxext);
			}
			else{
				sub = txt.substring(minext++,maxext--);
			}
			for(String patstr:pat)
				sub = formatsnippet(sub,patstr);
		}
		else{
			// for all patterns
			int countpattern = 0;
			// for each value calculate min and max..
			for(int value: poslist){
				int start = value;
				int end = value;
				int c=0;
				//take the prev 35 characters
				while(start>0  && c<extra){
					start--; 
					c++;
				}
				c = 0;
				// take next 25 character
				while(end<max && c<extra){
					end++;
					c++;
				}

				// to avoid half words
				while(start>0 && txt.charAt(start)!= ' ') start--;
				while(end<max && txt.charAt(end+patlength) != ' ') end++;

				// first time
				if(sub == null){
					sub = txt.substring(start,end+patlength);
					// for full pattern matches dont run format everytime
					if(pat.length>1)
						sub = formatsnippet(sub,pat[countpattern]);
				}
				else{
					sub = sub + ".." + txt.substring(start,end+patlength);
					if(pat.length>1)
						sub = formatsnippet(sub,pat[countpattern]);
				}
				// full pattern match dont increment the pattern pointer
				if(pat.length>1)
					countpattern++;
			}
			// if only 1 pattern format at end..
			if(pat.length == 1)
			 sub = formatsnippet(sub,pat[countpattern]);
		}
	//	System.out.print(sub);
		return sub;
	}

	//format the string by adding highlights to pattern
	public static String formatsnippet(String sub,String pat){
		String hlpat = starthl + pat + endhl;
		sub = sub.replaceAll(pat,hlpat);
		return sub;
	}
	
	// Calculate string with max length in pat array
	public static int lenMaxPattern(String[] pat){
		int maxLen= Integer.MIN_VALUE;
		for(String str:pat){
			if(str.length()>maxLen){
				maxLen = str.length();
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		String pat = "deep the pizza";
		String txt = "end this is for deep dish for the think in string and for dish . " +
		"  also for pizza the deep dish pizza  and " +
		"  also for pizza the deep dish pizza  and " +
		"  pizza another fish one of a plus  " +
		" given deep dish  is "       		       ;
		Main.highlight_doc(pat, txt);


	}
}

