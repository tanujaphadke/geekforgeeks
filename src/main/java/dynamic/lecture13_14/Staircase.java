package dynamic.lecture13_14;

import java.util.HashMap;
import java.util.Map;

//Very good https://www.youtube.com/watch?v=sF7hzgUW5uY

//http://www.geeksforgeeks.org/count-ways-reach-nth-stair
//https://www.youtube.com/watch?v=HmiEHhSCx0U

//There are n stairs, a person standing at the bottom wants to reach the top.
// The person can climb either 1 stair or 2 stairs at a time. Count the number of ways,
// the person can reach the top.
public class Staircase {
	public static Map<Integer, Integer> storedWays = new HashMap<Integer, Integer>();
	
	public int findWays(int n){
		if ( n ==0) return 0; //invaid case
		if ( n ==1 ) return 1;
		if (n ==2 ) return 2;
		if (storedWays.get(n) !=  null ) {
			return storedWays.get(n);
		}
		else{
			int ways = findWays(n-1) + findWays(n-2);
			storedWays.put(n, ways);
			return ways;
		}
		
	}
	

}
