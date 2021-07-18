package dynamic.lecture13_14;

public class Rodcutting {

    static int cutRod(int[] price, int n) {
        if (n <= 0) {
            return 0;
        } else {
            int max_val = Integer.MIN_VALUE;

            for (int i = 0; i < n; ++i) {
                max_val = Math.max(max_val, price[i] + cutRod(price, n - i - 1));
            }

            return max_val;
        }
    }

    static int cutRodDP(int[] price, int n) {
        int[] optimalPrice = new int[n + 1];
        optimalPrice[0] = 0;

        for (int i = 1; i <= n; ++i) {
            int max_val = Integer.MIN_VALUE;

            for (int j = 0; j < i; ++j) {
                max_val = Math.max(max_val, price[j] + optimalPrice[i - j - 1]);
            }

            optimalPrice[i] = max_val;
        }

        return optimalPrice[n];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 8, 9};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " + cutRod(arr, size));
    }
}


