package geekforgeeks.arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//https://www.quora.com/Given-an-array-having-positive-integers-how-do-I-find-a-continuous-subarray-which-sums-to-a-given-number
//https://www.careercup.com/question?id=6305076727513088
public class SubarrayWithGivenSum {
	// Continuous sequence against target number
	public static int[] nums = { 25, 5, 4, 7, 2, 11,7 }; // get from args
	public static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
	public static void main(String args[]) {
		Collection<List<Integer>> ans =   	findSum(20);
		Iterator<List<Integer>> itr = ans.iterator();
		while ( itr.hasNext() ){
			List<Integer> arr = itr.next();
			for (Integer ech : arr){
				System.out.print(" " + ech + " + " );
			}
			System.out.println();
		}

	}

	public static Collection<List<Integer>> findSum(int targetSum) {
		int sum = 0;
		int j = 0;
		for (int k = 0; k < nums.length; k++) {
			List<Integer> aList = new ArrayList<Integer>();
			sum = 0;
			for (int i = k; i < nums.length; i++) {
				System.out.println("Working with k = " + k);

				sum = sum + nums[i];
				aList.add(nums[i]);
				if (sum > targetSum) {
					aList.clear(); break;
				} else if (sum == targetSum) {
					System.out.println("FOUND");
					map.put(j, aList);
					j++;
					break;
				}
			}
		}
		return map.values();
	}
}
