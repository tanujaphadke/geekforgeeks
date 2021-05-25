package recursion.lect3;

public class RecMultiply {
    // 8 X 7 = 7 + 7 + 7 +.. 7
    public static int multiply(int a, int b) {
        if (a == 1) return b;

        int ans = b + multiply(a - 1, b);
        return ans;

    }


    // 8 X 7 = 7 + 7 + 7 +.. 7
    // a even  8 /2 = 4   5 X 7 ==> 7/2 = 3  7 X 5  = 3 X 5  X 2 + 5
    static int product(int a, int b) {
        int larger = a > b ? a : b;
        int smaller = a == larger ? b : a;
        return productHelper(smaller, larger);
    }

    //  5 X 7 ==> 7/2 = 3  7 X 5  = 3 X 5  X 2 + 5
    // Add the larger element smaller numer of times
    // add the 7 , 5 number of times.
    static int productHelper(int smaller, int larger) {
        if (smaller == 1) return larger;
        if (smaller == 0) return 0;
        int s = smaller >> 1;
        int halfproduct = productHelper(s, larger);
        if (smaller % 2 == 0) {
            int ans = halfproduct + halfproduct;
            return ans;
        }
        int ans = larger + halfproduct + halfproduct;
        return ans;
    }

    public static void main(String args[]) {

        System.out.println(multiply(8, 7));
        System.out.println(product(13, 7));
        System.out.println(9 >> 1);

    }
}
