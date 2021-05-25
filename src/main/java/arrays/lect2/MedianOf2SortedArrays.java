package geekforgeeks.arrays.lect2;

//https://www.youtube.com/watch?v=yD7wV8SyPrc
public class MedianOf2SortedArrays {
    private static int A1[] = new int[6];
    private static int A2[] = new int[4];

    public static double findMedian() {
        int low = 0;
        int high = A1.length; //dize of A1 first array;
        int n1 = A1.length;
        int n2 = A2.length;
        int MIN_INF = Integer.MIN_VALUE;
        int INF = Integer.MAX_VALUE;
        int left1 = 0, right1 = 0, left2 = 0, right2 = 0;  double value = -0.0;
        while (low <= high) {
            int cut1 = (low + high) / 2; // middle of A1
            int cut2 = ((n1 + n2) / 2 - cut1);//remaining
            left1 = cut1 == 0 ? MIN_INF : A1[cut1 - 1];
            left2 = cut2 == 0 ? MIN_INF : A2[cut2 - 1];
            right1 = cut1 == n1 ? INF : A1[cut1];
            right2 = cut2 == n1 ? INF : A2[cut2];

            if (left1 > right2) {
                high = cut1 - 1;
            } else if (left2 > right1) {
                low = cut1 + 1;
            } else {
                //return the answer
               value = (n1 + n2) % 2 == 0 ? (Math.max(left1, left2) + Math.min(right1, right2)) / 2 :
                        Math.min(right1, right2);
            }
        }
        return value;

    }


    public static void main(String args[]) {
        int x = 5 / 2;
        System.out.println(x);
    }
}
