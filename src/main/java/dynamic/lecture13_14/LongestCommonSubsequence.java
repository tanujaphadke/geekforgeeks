package dynamic.lecture13_14;

public class LongestCommonSubsequence {

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    //Recursion
    int lcs(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1])
            return 1 + lcs(X, Y, m - 1, n - 1);
        else
            return max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
    }

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcsBottomUp(char[] X, char[] Y, int m, int n) {
        int L[][] = new int[m + 1][n + 1];

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0; // The first row and first column will be made 0
                else if (X[i - 1] == Y[j - 1])
                    //We go one row up and one column up --> This is the count of lcs after removing
                    //the matching characters.
                    L[i][j] = 1 + L[i - 1][j - 1] ;
                else
                    //We go one row up and column is same --> This means we are including the last character of Y
                    //We go one column up  and row is same --> This means we are including the last character of X
                    L[i][j] = max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }


    /* Utility function to get max of 2 integers */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " " +
                lcs.lcs(X, Y, m, n));
    }
}
