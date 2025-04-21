package dailycode;
/*
This problem was asked by Google.

In linear algebra, a Toeplitz matrix is one in which the elements on any given diagonal from top left to bottom right are identical.

Here is an example:

1 2 3 4 8
5 1 2 3 4
4 5 1 2 3
7 4 5 1 2
Write a program to determine whether a given input is a Toeplitz matrix.


 */
public class Toeplitz {
    public static void main(String args[]) {
        int[][] A = {
                {1, 2, 3, 4, 8},
                {5, 1, 2, 3, 4},
                {4, 5, 1, 2, 3},
                {7, 4, 5, 1, 2}};
       System.out.println( isTolplitz(A));
    }


    public static boolean isTolplitz(int[][] A){
        int ROWS = A.length;
        int  COLS = A[0].length;
        int k=0;
        for ( int c = 0 ; c < COLS ; c++ ) {
            int elem = A[k][c];
            for ( int r = 0 ; r < ROWS & r+c < COLS; r ++ ) {
                if ( elem == A[r] [ r + c ] )
                    continue; // 0, 0 , 1, 1 ; 2, 2  ; 3, 3
                else return false;
            }
        }
        return true;
    }
}
