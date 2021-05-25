package recursion.lect3;

/*
You are given an N by M matrix of 0s and 1s. Starting from the top left corner, how many ways are there to reach the bottom right corner?

You can only move right and down. 0 represents an empty space while 1 represents a wall you cannot walk through.

For example, given the following matrix:

[[0, 0, 1],
 [0, 0, 1],
 [1, 0, 0]]
Return two, as there are only two ways to get to the bottom right:

Right, down, down, right
Down, right, down, right
The top left corner and bottom right corner will always be 0.


 */
public class NumberofWays {
    private static int m;
    private static int n;

    public int numWays(int M[][], int r, int c) {
        if (r == m-1 && c == n-1) return 1; //1
        //Find coordinate to the right of element present at rth row and cth column.
        int right[] = (c + 1 < n && M[r][c + 1] == 0) ? new int[]{r, c + 1} : null;
        int down[] = (r + 1 < m && M[r + 1][c] == 0) ? new int[]{r + 1, c} : null;
        int ways = 0;
        if (right != null)
            ways = ways + numWays(M, r, right[1]);
        if (down != null)
            ways = ways + numWays(M, down[0], c);
        return ways;
    }

    public void numberOfWaysToBottomRight(int[][] M) {
        System.out.println(numWays(M, 0, 0));

    }

    public static void main(String args[]) {
        int M[][] = {{0, 0, 1},
                     {0, 0, 1},
                     {1, 0, 0}};
        m = M.length;
        n = M[0].length; //m rows and n columns;
        NumberofWays now = new NumberofWays();
        now.numberOfWaysToBottomRight(M);

    }


}
