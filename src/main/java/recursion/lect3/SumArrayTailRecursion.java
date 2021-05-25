package recursion.lect3;
//http://www.geeksforgeeks.org/tail-recursion/
/*
 Input : A[] = {1, 8, 9}
Output : 18

Input : A[] = {2, 55, 1, 7}
Output : 65

 */
public class SumArrayTailRecursion {

	public static void main(String args[]) {
		int[] A = {2, 55, 1, 7};
		System.out.println(sumArray(A,A.length-1, 0));
	}

	public static int sumArray(int[] A, int idx,  int ans) {
		if (idx == -1) {
			return ans;
		}
		return sumArray(A, idx-1, ans + A[idx]);
	}
}
/*
 sumArray({2, 55, 1, 7}, 3, 0)
 	sumArray({2, 55, 1, 7}, 2, 0+7)
 		sumArray({2, 55, 1, 7}, 1, 0+7+1)
 			sumArray({2, 55, 1, 7}, 0, 0+7+1+55)
 				sumArray({2, 55, 1, 7}, -1, 0+7+1+55+2)
 					
 */
//4,1
//3,14
//2, 143
//1, 1432
//0, 1432   408 439 7144	