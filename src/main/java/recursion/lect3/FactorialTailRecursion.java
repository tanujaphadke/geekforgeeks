package recursion.lect3;

//http://www.geeksforgeeks.org/tail-recursion/
public class FactorialTailRecursion {

    public static void main(String args[]) {
        System.out.println(fact(4));
    }

    public static int fact(int n) {
        return factorial(n, 1);
    }

    public static int factorial(int n, int ans) {
        if (n == 0) {
            return ans;
        }
        return factorial(n - 1, ans * n);
    }

    public static int factirialTradational(int n) {
        if (n == 0) return 1;
        int x = n * factirialTradational(n - 1);
        return x;
    }
}
/*
 f(5,1)
 	f(4, 1*5)
 		f(3, 1*5*4)
 			f(2, 1*5*4*3)
 				f(1, 1*5*4*3*2)
 					f(0, 1*5*4*3*2*1)
 */
//4,1
//3,14
//2, 143
//1, 1432
//0, 1432   408 439 7144	