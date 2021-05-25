package arrays.lect2;

import java.util.*;

public class CountPairsThatAddToASum {

    public static void main(String[] args) {
        int x = 6; //10 ; // get the value from args
        int arr[] = {1, 5, 7, -1, 5};//{ 2,7,9,4,6,1,8,3 };
        getPairsCount(new int[]{1, 1, 1, 1}, 2);
    }


    // Returns number of pairs in arr[0..n-1] with sum equal
    // to 'sum'
    static int getPairsCount(int[] arr, int sum) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Store counts of all elements in map hm
        for (int i = 0; i < arr.length; i++) {
            if (hm.get(arr[i]) == null) {
                hm.put(Integer.valueOf(arr[i]), Integer.valueOf(1));
            } else {
                hm.put(Integer.valueOf(arr[i]), hm.get(arr[i]) + 1);
            }

        }
        int count = 0;
        // iterate through each element and increment the
        for (int i = 0; i < arr.length; i++) {
            int secondNum = sum - arr[i];
            Integer secondNumCount = hm.get(secondNum);
            if (secondNumCount != null)
                count = count + secondNumCount;
            // if (arr[i], arr[i]) pair satisfies the
            // condition, then we need to ensure that the
            // count is decreased by one such that the
            // (arr[i], arr[i]) pair is not considered
            if (secondNum == arr[i])
                count--;
        }

        // return the half of twice_count
        return count / 2;
    }


    // Function to find a pair in an array with a given sum using sorting
    public static void findPairUsingSorting(int[] A, int sum) {
        // sort the array in ascending order
        Arrays.sort(A);

        // maintain two indices pointing to endpoints of the array
        int low = 0;
        int high = A.length - 1;

        // reduce the search space `A[low…high]` at each iteration of the loop

        // loop till the search space is exhausted
        while (low < high) {
            // sum found
            if (A[low] + A[high] == sum) {
                System.out.println("Pair found (" + A[low] + ", " + A[high] + ")");
            }

            // increment `low` index if the total is less than the desired sum;
            // decrement `high` index if the total is more than the desired sum
            if (A[low] + A[high] < sum) {
                low++;
            } else {
                high--;
            }
        }

        // we reach here if the pair is not found
        System.out.println("Pair not found");
    }

    // Given an array A[] and a number x, check for pair in A[] with sum as x
    public static void printPairs(int arr[], int x) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int each : arr) {
            int val = x - each; //This value should be present in the array if sun has to be x
            hashMap.put(each, val);
        }
        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();

        for (int i = 0; i < arr.length; i++) {
            int requiredSecondNumber = hashMap.get(arr[i]);
            boolean isRequiredSecondNumberPresentInArray = hashMap.get(requiredSecondNumber) != null ? true : false;
            if (isRequiredSecondNumberPresentInArray) {
                System.out.println("Pairs: first: " + arr[i] + " requiredSecondNumber: " + requiredSecondNumber);
                //Since this pair is printed once so
            }
        }

    }


    //This will not give all pairs in case of ( 1,1,1,1) . it will give only 3 pairs, the answer should be 6 pairs.
    //WRONG answer
    public static void printPairsUsingSet(int[] numbers, int sum) {
        if (numbers.length < 2) {
            return;
        }
        Set set = new HashSet(numbers.length);
        for (int value : numbers) {
            int target = sum - value;
            // if target number is not in set then add
            if (!set.contains(target)) {
                set.add(value);
            } else {
                System.out.printf("(%d, %d) %n", value, target);
            }
        }
    }

//javarevisited.blogspot.com/2014/08/how-to-find-all-pairs-in-array-of-integers-whose-sum-equal-given-number-java.html#ixzz6uyG90nq3

    public static void populateHash(int[] arr, Map<Integer, Integer> hm) {

        for (int i = 0; i < arr.length; i++) {
            if (hm.get(arr[i]) == null)
                hm.put(arr[i], 0);
            else {
                hm.put(arr[i], hm.get(arr[i]) + 1);
            }
        }

    }

}

