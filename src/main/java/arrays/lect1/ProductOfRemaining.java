package arrays.lect1;

/**
 //https://www.interviewcake.com/question/java/product-of-other-numbers
 Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i. Do this without using division.

 Note: The length of the array is guaranteed to be greater than 1.

 Example 1
 Input

 nums = [1, 2, 3, 4, 5]
 Output

 [120, 60, 40, 30, 24]
 Explanation

 120 = 2 * 3 * 4 * 5, 60 = 1 * 3 * 4 * 5, and so on.

 Example 2
 Input

 nums = [3, 2, 1]
 Output

 [2, 3, 6]
 */
public class ProductOfRemaining {

    public static int[] findProductOfRemaining1(int[] nums) {
        // Write your code here
        int products[] = new int[nums.length];
        products [0] = 1;
        products[1] = nums[0];
        for ( int i = 2 ; i < nums.length ; i ++ ){
            products[i] = products[i-1]*  nums[i -1 ];
        }
        //at this point i = n
        int rProducts[] = new int[nums.length];
        rProducts[nums.length-1] = 1 ; //products[n-1];
        for ( int k = nums.length -1  ; k > 0 ; k--) {
            rProducts[k-1] = rProducts[k]*  nums[k];
        }
        for ( int j = 0 ; j <nums.length ; j ++){
            products[j] = products[j]*rProducts[j];
        }

        return products;
    }

    //{1, 2, 3, 4, 5}
    public static int[] findProductOfRemaining(int[] nums ){
        int [] products   = new int[nums.length]; // This array will come from left calculation
        products [0] = 1;
        products[1] = nums[0];
        for ( int i = 2 ; i < nums.length ; i ++ ){
            products[i] = products[i-1]*  nums[i -1 ];
        }

        int temp = 1; int n = nums.length; //size of array
        for ( int i = n-1 ; i > -1; i -- ){
            products [i] = products[i] * temp;
            temp = temp * nums[i];

        }
        return products;
    }

    public  static void main(String args[]){
        findProductOfRemaining1 (new int[] {1, 2, 3, 4, 5}); // 2 arrays

        findProductOfRemaining (new int[] {1, 2, 3, 4, 5}); //temp variable
    }
}
