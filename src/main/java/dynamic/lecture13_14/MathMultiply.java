package dynamic.lecture13_14;

//Multiplies two matrices 
public class MathMultiply {
	static final int m = 4;// no of rows
	static final int n = 3;// no of columns
	static final int p = 4;// no of columns
	int A[][] = new int[m][n];
	int B[][] = new int[n][p];

	private static final int [][] matmul(int A[][], int B[][]) {
		
		int colsB = B[0].length;
		int rowsB = B.length;
		int colsA = rowsB;//Both will be same
		int rowsA = A.length;
		
		int C[][] = new int [rowsA][colsB]; //resulting matrix will have m rows and p columns 

		for ( int i = 0 ;i < rowsA;i++) {
			for ( int j = 0 ;j < colsB; j++) {
				//C[i][j] is already 0 ; from line 18
				for ( int k = i ; k < colsA; k ++ ) {
					C[i][j] = C[i][j] + A[i][k]*B[k][j];
				}	
			}
		}
		
		return C;
		
	}

}
