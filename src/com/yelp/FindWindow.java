// This function generates the minimum window that contains all the query words 


package com.yelp;
import java.util.ArrayList;

public class FindWindow {

	public static int[] findMinWindow(ArrayList<ArrayList<Integer>> lists) {  
		int size = lists.size();
        
		// array to hold current min from all arrays
		int[] curr = new int[size];
		//the current best solution positions
		int[] sol = new int[size];
		//ArrayList<Integer> sol = new ArrayList<Integer>(size);

		//the window length of current solution
		int currMin = Integer.MAX_VALUE;

		while(true) {
			//select the list that has the increasing minimum element
			int minList = min(curr,lists);      
			
			//if you cannot increase the minimum breakd
			if (minList == -1) break; 
			
			//calculate the window size
			int minValue = lists.get(minList).get(curr[minList]);
			int maxValue = max(curr,lists);      
			int diff = maxValue - minValue;
			//update the solution if necessary 
			if(diff < currMin ) {
				currMin = diff;
				System.arraycopy(curr, 0, sol, 0, size);
			}

			//update the current minumum element
			int s = curr[minList] + 1;
			if(s>=lists.get(minList).size()) break;
			else curr[minList]++;
		}     
		return sol;
	}

	// find the min element
	protected static int min(int[] curr, ArrayList<ArrayList<Integer>> v) {
		int min = Integer.MAX_VALUE;
		int arg = -1;

		for(int i = 0 ; i < v.size(); i++) {
			if(v.get(i).get(curr[i]) < min) {
				min = v.get(i).get(curr[i]);
				arg = i;
			}
		}  

		return arg;
	}

	// find the max element
	protected static int max(int[] pos, ArrayList<ArrayList<Integer>> v) {
		int max = -1;
		int arg = -1;
		for(int i = 0 ; i < v.size(); ++i) {
			if(v.get(i).get(pos[i]) > max) {
				max = v.get(i).get(pos[i]);
				arg = i;
			}
		}  
		return v.get(arg).get(pos[arg]);
	}
}
