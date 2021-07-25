package dynamic.lecture16;

import java.util.ArrayList;
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
                    //TBD:
                    solutions.addAll(changes);
                }
            }
        }
        return solutions;
    }

    static long countWays( int amount, int denominations[]) {
        int[] solution =  new int[amount +1 ];
        solution[0] = 1 ;
        for (int coin : denominations){
            for ( int i =1 ; i <solution.length; i++){
                if ( i >= coin ) {
                    solution[i] = solution[i] + solution[i - coin ];
                 }
            }
        }
        return solution[amount];
    }

    public static int countWaysSpaceOptimized(int S[],  int amount) {
        // table[i] will be storing the number of solutions for
        // value i. We need n+1 rows as the table is constructed
        // in bottom up manner using the base case (n = 0)
        int table[] = new int[amount + 1];

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[] values
        // after the index greater than or equal to the value of the
        // picked coin
        for (int i = 0; i < S.length; i++)
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
        int amount = 4;
        System.out.println(countWaysSpaceOptimized(arr, amount));


        System.out.println(" Count Ways for " + countWays(5, new int[]{1,2,5}));
    }
}
