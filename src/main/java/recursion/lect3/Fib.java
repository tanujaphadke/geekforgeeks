package recursion.lect3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://www.mathsisfun.com/numbers/fibonacci-sequence.html
//http://www.geeksforgeeks.org/tail-recursion/
public class Fib {


    public static void main(String args[]) {
        int n = 10;
        System.out.println(fibItr(n));
        System.out.println(fibo(5));
        System.out.println(fiboPrint(5));

    }

    public static List<Integer> printNFibNumber(int n, List<Integer> fibNumbersSoFar) {
        if (n == 0) {
            List<Integer> fibNos = new ArrayList<Integer>();
            fibNos.add(0);
            return fibNos;
        }
        if (n == 1) {
            //fibNos.addAll(fibNumbersSoFar);//fibNos.add(fn_1 + fn_2);
            return fibNumbersSoFar;
        }
        int fn_1 = fibNumbersSoFar.get(fibNumbersSoFar.size() - 1);
        int fn_2 = fibNumbersSoFar.get(fibNumbersSoFar.size() - 2);
        fibNumbersSoFar.add(fn_1 + fn_2);
        List<Integer> x = printNFibNumber(n - 1, fibNumbersSoFar);
        return x;
    }

    /**
     * Printing n numbers by returning values and not passing array argument
     *
     * @param n
     * @param fn_2
     * @param fn_1
     * @return
     */
    public static List<Integer> returnFibNumberTillN(int n, int fn_2, int fn_1) {
        List<Integer> fibNumbersSoFar = new ArrayList<>();
        //fibNumbersSoFar.add(fn_2); fibNumbersSoFar.add(fn_1);
        if (n == 0) {
            List<Integer> fibNos = new ArrayList<Integer>();
            fibNos.add(0);
            return fibNos;
        }
        if (n == 1) {
            return fibNumbersSoFar;
        }
        fibNumbersSoFar.add(fn_1 + fn_2);
        List<Integer> x = returnFibNumberTillN(n - 1, fn_1, fn_1 + fn_2);
        x.addAll(0, fibNumbersSoFar); //0, 1, 2
        return x;
    }

    /*
        Prints first n fib numbers
     */
    public static Integer[] fiboPrint(int n) {
        List<Integer> fibNumbersSoFar = new ArrayList<>();
        fibNumbersSoFar.add(0);//f(n-1)
        fibNumbersSoFar.add(1); //fn-2
        List<Integer> fibNos = printNFibNumber(n, fibNumbersSoFar);

        List<Integer> fibNos2 = returnFibNumberTillN(5, 0, 1);
        fibNos2.add(0, 0);
        fibNos2.add(1, 1);

        Integer[] x = new Integer[fibNos.size()];
        return fibNos.toArray(x);
    }


    /*
    Fn = Fn-1 + Fn-2
    0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ……..
    */
    // A tail recursive function to
    // calculate n th fibnacci number
    public static int nthFibNumber(int n, int fn_2, int fn_1) {
        if (n == 0) {
            return fn_2;
        }
        if (n == 1) {
            return fn_1;
        }
        int ans = fn_2 + fn_1;
        int x = nthFibNumber(n - 1, fn_1, ans);
        return x;
    }


    public static int fibo(int n) {
        if (n == 0) return nthFibNumber(n, 0, 0);
        return nthFibNumber(n, 0, 1);
    }

//
//    fib 5 = fib ( 5, 0, 1 )
//                ans = 1 ;
//                fib( 4, 1, 1 )
//                    ans = 2
//                    fib( 3, 1, 2 )
//                            ans = 3
//                            fib(2, 2, 3)
//                                    ans = 5
//                                    fib(1, 3, 5)
//
//


    static int fibItr(int n) {
        int fn_2 = 0, fn_1 = 1, fi = 0;
        if (n == 0)
            return 0;
        if (n == 1) return 1;
        for (int i = 2; i <= n; i++) {
            fi = fn_2 + fn_1;
            fn_2 = fn_1;
            fn_1 = fi;
        }
        return fi;
    }

    //What is space complexity?
    //Little more efficinet because we are not making function calls
    //All U need is to store last 2 values only. LIke in the case of Itr. so Itr is better
    // First calculate 2 then 3.. n
    public static int fibBU(int x) {
        int fib[] = new int[x + 1]; // Store results
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < x + 1; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[x];
    }
    public  static Map<Integer,Integer> memo = new HashMap<>();
    // Instead of HashMap we can use Array also and look up by index in constant time
    static {
        memo.put(0,0);memo.put(1,1);
    }

    //Using memoization https://www.youtube.com/watch?v=OQ5jsbhAv_M
    //This too approached in a top down manner till we reach the bottom, After that we start
    // going up
    public static int fibMemo(int n){
        Integer f = memo.get(n);
        if ( f!= null ) return f;
        f = fibMemo(n-1) + fibMemo(n-2);
        memo.put(n,f);
        return f;

    }

}




