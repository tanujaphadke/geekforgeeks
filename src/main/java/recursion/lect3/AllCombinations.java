package recursion.lect3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Java program to Print all
// combinations of points that 
// can compose a given number
//https://www.geeksforgeeks.org/print-all-combinations-of-points-that-can-compose-a-given-number/
class AllCombinations {

    private static int MAX_POINT = 3;

    public static List<List<Integer>> returnListOfCompositions(int n) {
        if (n <= 0) {
            List<List<Integer>> comp = new ArrayList<>();
            List<Integer> cs = new ArrayList<>();
            comp.add(cs);
            return comp;
        }
        if (n == 1) {
            List<List<Integer>> comp = new ArrayList<>();
            List<Integer> cs = new ArrayList<>(); cs.add(1);
            comp.add(cs);
            return comp;
        }

        List<List<Integer>> listCompsForN_1 = returnListOfCompositions(n - 1);
        for ( List<Integer> eachComposition : listCompsForN_1) {
            eachComposition.add(0,1);
        }
        if (n >= 2) {
            List<List<Integer>> listCompsForN_2 = returnListOfCompositions(n - 2);
            for ( List<Integer> eachComposition : listCompsForN_2) {
                eachComposition.add(0,2);
            }
            listCompsForN_1.addAll(listCompsForN_2);
        }
        if (n >= 3) {
            List<List<Integer>> listCompsForN_3 = returnListOfCompositions(n - 3);
            for ( List<Integer> eachComposition : listCompsForN_3) {
                eachComposition.add(0,3);
            }
            listCompsForN_1.addAll(listCompsForN_3);
        }

        return listCompsForN_1;
    }


    public static List<List<Integer>> returnListOfCompositionsLoop(int n) {
        if (n <= 0) {
            List<List<Integer>> comp = new ArrayList<>();
            List<Integer> cs = new ArrayList<>();
            comp.add(cs);
            return comp;
        }
        if (n == 1) {
            List<List<Integer>> comp = new ArrayList<>();
            List<Integer> cs = new ArrayList<>(); cs.add(1);
            comp.add(cs);
            return comp;
        }
        List<List<Integer>> listCompsForN = new ArrayList<>();
        for( int i = 1 ; i <= MAX_POINT ; i ++ ){
            if (n >=i) {
                List<List<Integer>> listCompsForN_i = returnListOfCompositionsLoop(n - i);
                for (List<Integer> eachComposition : listCompsForN_i) {
                    eachComposition.add(0, i);
                }
                listCompsForN.addAll(listCompsForN_i);
            }
        }
        return listCompsForN;
    }


    // Function prints all
    static void printCompositions(int arr[], int n, int i) {
        int MAX_POINT = 3;
        if (n == 0) {
            printArray(arr, i);
        } else if (n > 0) {
            for (int k = 1; k <= MAX_POINT; k++) {
                arr[i] = k;
                printCompositions(arr, n - k, i + 1);
            }
        }
    }

    // Utility function to print array arr[]
    static void printArray(int arr[], int m) {
        for (int i = 0; i < m; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver program
    public static void main(String[] args) {
        int n = 3;
        int size = 100;
        int[] arr = new int[size];
        System.out.println("Different compositions formed by 1, 2 and 3 of " + n + " are");
        //printCompositions(arr, n, 0);
        List<List<Integer>> compositions =  returnListOfCompositionsLoop(n);
        for ( List<Integer> each : compositions){
            System.out.println(Arrays.toString(each.toArray()));
        }

    }
}

// Contributed by Pramod Kumar