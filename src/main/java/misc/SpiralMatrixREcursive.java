package geekforgeeks.misc;

public class SpiralMatrixREcursive {

	public static void printSpiral(int[][] A) {
		int maxRow = A.length;
		int maxCol = A[0].length;
		int startRow = 0;
		int startCol = 0;
		while (true) {
			int row = startRow;
			int col = startCol;
			while ( col < maxCol) {
				print(A[row][col++]);
			}
			while (row < maxRow -1 ) {
				print(A[++row][col - 1]);

			}
			col= col -1;
			while(col > startCol ) {
				print ( A[ row][ --col]);
			}
			while( row > startRow +1 ) {
				print ( A[ --row][ col ]);
			}

			startRow = startRow + 1;
			startCol = startCol + 1;

			maxRow = maxRow - 1;
			maxCol = maxCol - 1;

			if (maxRow < 0 || maxCol < 0)
				break;

		}

	}

	public static void print(int x) {
		System.out.print(x + "  ");
	}

	public static void main ( String args[]) {
		
		int[][] matrix0 = new int[] []{ {1,2,3}, 
									   {4,5,6},
									   {7,8,9} };
									   
									   int[][] matrix = new int[] []{ {1,2,3,4}, 
										   {4,5,6,5},
										   {7,8,9,6},
										   {17,18,19,16}};

		printSpiral(matrix);
	}

}
