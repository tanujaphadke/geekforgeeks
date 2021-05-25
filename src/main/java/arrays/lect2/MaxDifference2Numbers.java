package arrays.lect2;

//https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
public class MaxDifference2Numbers {

    public static int findMaxDifferenceBrute(int[] arr) {
        int maxDiff = arr[1] - arr[0];
        int i, j;
        for (i = 0; i < arr.length; i++) {
            for (j = i + 1; j < arr.length; j++) {
                if (arr[j] - arr[i] > maxDiff)
                    maxDiff = arr[j] - arr[i];
            }
        }
        return maxDiff;
    }

    public static int maxDiff(int arr[]) {
        int minElementSoFar = arr[0];
        int maxDiff = Integer.MIN_VALUE; // Let it to -infinity

        for (int i  = 1; i < arr.length; i++) {
            int currentElement = arr[i];
            int currentDiff = currentElement - minElementSoFar;
            if ( currentDiff > maxDiff)
                maxDiff = currentDiff;
            if (currentElement < minElementSoFar)
                minElementSoFar = currentElement;
        }
        return maxDiff;
    }

    /**
     * This prints the maxDiff Pair
     * @param arr
     * @return
     */
    public static int[] maxDiffPrintPair(int arr[]) {
        int minElementSoFar = arr[0];
        int [] maxDiffPair = new int[2];
        int maxDiff = Integer.MIN_VALUE; // Let it to -infinity

        for (int i  = 1; i < arr.length; i++) {
            int currentElement = arr[i];
            int currentDiff = currentElement - minElementSoFar;
            if ( currentDiff > maxDiff) {
                maxDiff = currentDiff;
                maxDiffPair[0] = minElementSoFar;
                maxDiffPair[1] = currentElement;
            }
            if (currentElement < minElementSoFar) {
                minElementSoFar = currentElement;
            }
        }
        return maxDiffPair;
    }
    public static void main(String args[]) {
        int maxDiff = maxDiff( new int[] {2, 3, 10, 6, 4, 8, 1});
        int [] maxDiffPair = maxDiffPrintPair( new int[] {2, 3, 10, 6, 4, 8, 1});
    }
}
