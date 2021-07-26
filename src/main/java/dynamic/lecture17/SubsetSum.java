package dynamic.lecture17;

public class SubsetSum {
/*
dp[i][C] = dp[i – 1][C – arr[i]] + dp[i – 1][C]
Here, dp[i][C] stores the number of subsets of the sub-array arr[0 - i ] such that their sum is equal to C.
Thus, the recurrence is very trivial as there are only two choices
Include the ith element in the subset : means now in the remaining subset we will not have ith element and the remaining sum will be C - arr[i]
Exclude the ith element in the subset : means now in the remaining subset we will not have ith element and the remaining sum will be C
 */
    static int subsetSumTabular(int a[], int sum) {

        // Initializing the matrix
        int tab[][] = new int[a.length + 1][sum + 1];

        // Initializing the first value of matrix
        tab[0][0] = 1;
        for (int i = 1; i <= sum; i++)
            tab[0][i] = 0;
        for (int i = 1; i <= a.length; i++)
            tab[i][0] = 1;

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= sum; j++) {
                // If the value is greater than the sum
                if (a[i - 1] > j)
                    tab[i][j] = tab[i - 1][j];

                else {
                    tab[i][j] = tab[i - 1][j] + tab[i - 1][j - a[i - 1]];
                }
            }
        }

        return tab[a.length][sum];
    }

    // Recursive function to return the count
    // of subsets with sum equal to the given value
    /*
    Recursive Call: If the base case is not satisfied, then call the function twice. Once by including the element at index ‘i’ and once by excluding the element at index i. Find the count for both these cases and then return the final count.
	count = subsetSum(arr, i + 1, sum - arr[i], count);
    count = subsetSum(arr,  i + 1, sum, count);
     */
    static int subsetSumRecursive(int arr[],  int i, int sum, int count) {
        // The recursion is stopped at N-th level
        // where all the subsets of the given array
        // have been checked
        if (i == arr.length) {

            // Incrementing the count if sum is
            // equal to 0 and returning the count
            if (sum == 0) {
                count++;
            }
            return count;
        }

        // Recursively calling the function for two cases
        // Either the element can be counted in the subset
        // If the element is counted, then the remaining sum
        // to be checked is sum - the selected element
        // If the element is not included, then the remaining sum
        // to be checked is the total sum
        count = subsetSumRecursive(arr, i + 1, sum - arr[i], count);
        count = subsetSumRecursive(arr, i + 1, sum, count);
        return count;
    }


    // Driver Code
    public static void main(String[] args) {
//        int n = 4;
//        int arr[] = {3, 3, 3, 3};
//        int sum = 6;

        int arr[] = {1, 2, 3, 4, 5};
        int sum = 10;

        System.out.println("Number of ways using Tabular method " + subsetSumTabular(arr, sum));


        System.out.print("Number of ways using recursion " + subsetSumRecursive( arr, 0, sum, 0));
    }
}
