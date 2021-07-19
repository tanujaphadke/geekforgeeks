package dynamic.lecture16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinExchange {

    int N = 10;
    int[] s = {2, 5, 3, 6};

    public static List<List<Integer>> recursicePrintAllPossibleWaysToExchangeCoin(int n, List<Integer> s) {
        if (s.isEmpty()) {
            List<Integer> solution = new ArrayList<>();
            List<List<Integer>> solutions = new ArrayList<>();
            solutions.add(solution);
            return solutions;
        }
        if (s.size() == 1 && s.get(0) == n) {
            List<Integer> solution = new ArrayList<>();
            solution.add(s.get(0));
            List<List<Integer>> solutions = new ArrayList<>();
            solutions.add(solution);
            return solutions;
        }

        List<Integer> newdenominationLst = new ArrayList<>();
        List<List<Integer>> solutions = new ArrayList<>();
        for (int denomination : s) {
            if (denomination == n) {
                //we found one solution
                List<Integer> solution = new ArrayList<>();
                solution.add(denomination);
                solutions.add(solution);
            }
            if (denomination < n) {
                //Means that this can be a valid denomination to take
                int newN = n - denomination; //means we want to take that denomination
                for (int denom : s) {
                    if (denom > newN) continue; // a denomination more that number can never return that number
                    newdenominationLst.add(denomination);
                }
                if (newdenominationLst.isEmpty()) continue; //weans no solution
                List<List<Integer>> changes = recursicePrintAllPossibleWaysToExchangeCoin(newN, newdenominationLst);
                if (changes != null || !changes.isEmpty()) {
                    for (List<Integer> change : changes) {
                        change.add(0, denomination);
                    }
                    //We should not add changes to this list if it already exists. Since order does not matter
                    //we may end up in a situation where we have {2,3} and {3,2}
                    solutions.addAll(changes);
                }
            }
        }
        return solutions;
    }

    static long countWaysSpace(int S[], int lengthDenominations, int amount) {
        //Time complexity of this function: O(mn)
        //Space Complexity of this function: O(n)

        // table[i] will be storing the number of solutions for value i. We need n+1 rows as the table is
        // constructed in bottom up manner using the base case (n = 0)
        long[] solution = new long[amount + 1];

        // Initialize all table values as 0
        Arrays.fill(solution, 0);   //O(n)

        // Base case (If given value is 0)
        solution[0] = 1;

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int i = 0; i < lengthDenominations; i++)
            for (int j = S[i]; j <= amount; j++)
                solution[j] += solution[j - S[i]];

        return solution[amount];
    }

    public static int countWaysSpaceOptimized(int S[], int lengthDenominations, int amount) {
        // table[i] will be storing the number of solutions for
        // value i. We need n+1 rows as the table is constructed
        // in bottom up manner using the base case (n = 0)
        int table[] = new int[amount + 1];

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[] values
        // after the index greater than or equal to the value of the
        // picked coin
        for (int i = 0; i < lengthDenominations; i++)
            for (int j = S[i]; j <= amount; j++)
                table[j] += table[j - S[i]];

        return table[amount];
    }

    public static void main(String args[]) {
        List<Integer> A = new ArrayList<>();
        A.add(2);
        A.add(3);
        A.add(5);
        List<List<Integer>> solutions = recursicePrintAllPossibleWaysToExchangeCoin(5, A);
        solutions.stream().forEach(l -> {
            System.out.println();
            l.stream().forEach(c -> System.out.print(c + " "));
        });


        int arr[] = {1, 2, 3};
        int lengthDenominations = arr.length;
        int amount = 4;
        System.out.println(countWaysSpace(arr, lengthDenominations, amount));
    }
}
