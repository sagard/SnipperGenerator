// This function generates the minimum window that contains all the query words 


package com.yelp;
import java.util.ArrayList;

public class FindWindow {

	public static int[] findMinWindow(Integer[][] lists) {  
		int size = lists.length;

		// array to hold current min from all arrays
		int[] curr = new int[size];
		//the current best solution positions
		int[] sol = new int[size];

		//the window length of current solution
		int currMin = Integer.MAX_VALUE;

		while(true) {
			//select the list that has the increasing minimum element
			int minList = min(curr,lists);      
			
			//if you cannot increase the minimum breakd
			if (minList == -1) break; 
			
			//calculate the window size
			int minValue = lists[minList][curr[minList]];
			int maxValue = max(curr,lists);      
			int diff = maxValue - minValue;
			//update the solution if necessary 
			if(diff < currMin ) {
				currMin = diff;
				System.arraycopy(curr, 0, sol, 0, size);
			}

			//update the current minumum element
			int s = curr[minList] + 1;
			if(s>=lists[minList].length) break;
			else curr[minList]++;
		}     
		return sol;
	}

	// find the min element
	protected static int min(int[] curr, Integer[][] v) {
		int min = Integer.MAX_VALUE;
		int arg = -1;

		for(int i = 0 ; i < v.length; i++) {
			if(v[i][curr[i]] < min) {
				min = v[i][curr[i]];
				arg = i;
			}
		}  

		return arg;
	}

	// find the max element
	protected static int max(int[] pos, Integer[][] v) {
		int max = -1;
		int arg = -1;
		for(int i = 0 ; i < v.length; ++i) {
			if(v[i][pos[i]] > max) {
				max = v[i][pos[i]];
				arg = i;
			}
		}  
		return v[arg][pos[arg]];
	}
}
