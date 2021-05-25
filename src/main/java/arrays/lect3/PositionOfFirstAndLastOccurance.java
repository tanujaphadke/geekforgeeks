package arrays.lect3;

//https://www.youtube.com/watch?v=bU-q1OJ0KWw
/* https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Find First and Last Position of Element in Sorted Array
Medium
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.
 */
public class PositionOfFirstAndLastOccurance {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];

        result[0] = findStart(nums, target);
        result[1] = findEnd(nums, target);
        return result;

    }
    //{-99, -70, -69, -4, 1, 2, 3, 4, 8, 8, 8, 8, 8, 9, 10}
    public static int findStart(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        int index = -1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            if (nums[mid] == target) index = mid;
        }
        return index;
    }
    //{-99, -70, -69, -4, 1, 2, 3, 4, 8, 8, 8, 8, 8, 9, 10}
    public static int findEnd(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        int index = -1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            if (nums[mid] == target) index = mid;
        }
        return index;
    }


    public static void main(String args[]) {
        searchRange(new int[]{-99, -70, -69, -4, 1, 2, 3, 4, 8, 8, 8, 8, 8, 9, 10}, 8);

    }
}
