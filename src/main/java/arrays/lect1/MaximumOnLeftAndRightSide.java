package arrays.lect1;

/**
 *
 */
public class MaximumOnLeftAndRightSide {

    private static int[] A = {1, 2, 1, 3, 1, 2, 1, 4, 1, 0, 0, 2, 1, 4};
    // int[] B = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 0};

    public static void main(String args[]) {
        int[] maxleftArray = maxLeft(A);
        int[] maxRightArray = maxRight(A);
        System.out.println(maxleftArray);
        System.out.println(maxRightArray);
    }

    // A = {1, 2, 1, 3, 1, 2, 1, 4, 1, 0, 0, 2, 1, 4};
    // B = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 0};
    public static int[] maxLeft(int[] A) {
        int n = A.length; //number of elements in A
        //initialize array to store the maxLeft and maxRight for each index of Array A
        int maxLeft[] = new int[n];
        maxLeft[0] = 0; //since there is no element to the left of array

        for (int i = 1; i < n; i++)
            maxLeft[i] = Math.max(maxLeft[i - 1], A[i - 1]);
        return maxLeft;
    }


    // A = {1, 2, 1, 3, 1, 2, 1, 4, 1, 0, 0, 2, 1, 4};
    //B = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0}
    public static int[] maxRight(int[] A) {
        int n = A.length; //number of elements in A
        //initialize array to store the maxLeft and maxRight for each index of Array A
        int maxRight[] = new int[n];
        maxRight[n - 1] = 0; //since there is no element to the right of array
        // A initialize it to min value
        //filling the maxRight list
        for (int i = n - 2; i >= 0; i--)
            maxRight[i] = Math.max(maxRight[i + 1], A[i + 1]);
        return maxRight;
    }

}
