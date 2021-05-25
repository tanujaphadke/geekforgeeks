package recursion.lect3;

import java.util.LinkedList;
import java.util.List;

public class LargestIsland {
	public static boolean visited[][] = { { false, false, false, false, false }, { false, false, false, false, false },
			{ false, false, false, false, false }, { false, false, false, false, false } };
	public static int max_size = 0 ;
	public static int B[][] = { 
			{ 1, 1, 1, 1, 0 }, 
			{ 1, 1, 0, 1, 0 }, 
			{ 1, 1, 0, 0, 0 }, 
			{ 0, 0, 0, 1, 1 } };
	public static int B1[][] = { 
			{ 1, 1, 1, 0}, 
			{ 1, 1, 0, 1}, 
			{ 1, 0, 0, 0}, 
			{ 0, 0, 0, 1} };
	public static int B2[][] = { 
			{ 1, 1, 0}, 
			{ 1, 0, 0}, 
			{ 0, 0, 0} };

	public static void main(String args[]) {
		int count = 0 ;
		for (int i = 0; i < B.length; i++)
			for (int j = 0; j < B[0].length; j++) {
				if (!visited[i][j] && B[i][j] == 1) {
					int size= findSizeLargestRegion(i,j);
					System.out.println("TANUJA is " + size);
					max_size = Math.max(max_size,size );
					
				}
		}
		System.out.println("The max size is " + max_size);
		//RESET VISITED
		resetVisited();
		for (int i = 0; i < B.length; i++)
			for (int j = 0; j < B[0].length; j++) {
				if (!visited[i][j] && B[i][j] == 1) {
					markVisited(i,j);
					count ++;
				}
			}
		System.out.println("Count of island " + count);
	}
	
	public static int findSizeLargestRegion(int i, int j ) {
		if (!visited[i][j]) {
			int size = 1;
			visited[i][j] = true; 
			int positions[][] = getAdj1PositionsThatAreNotVisited(i,j);
			for (int[] eachPostion : positions ) {
				size = size + findSizeLargestRegion(eachPostion[0], eachPostion[1]);
			}
			return  size;
		}
		return 0;
		}
	/**
	 * Instead of counting the size of regions just use it to make the vertices visited. 
	 * s0 that they will not appear in the next DFS
	 * @param i
	 * @param j
	 */
	public static void markVisited(int i, int j ) {
		if (!visited[i][j]) {
			int size = 1;
			visited[i][j] = true; 
			int positions[][] = getAdj1PositionsThatAreNotVisited(i,j);
			for (int[] eachPostion : positions ) {
				markVisited(eachPostion[0], eachPostion[1]);
			}
		}
	}
	
	@Deprecated
	public static int findSizeLargestRegionWithDebugMEssages(int i, int j ) {
		if (!visited[i][j]) {
			System.out.print("\n\nCALLING : for :  " + i + ", " + j +"--->");
			int size = 1;
			visited[i][j] = true; 
			int positions[][] = getAdj1PositionsThatAreNotVisited(i,j);
			printpositions(positions);
			for (int[] eachPostion : positions ) {
				size = size + findSizeLargestRegion(eachPostion[0], eachPostion[1]);
			}
			System.out.println("Returning size for i,j " + i + "," +  j +  "," + size);
			return  size;
		}
		return 0;
		}
		
	
	public static void printpositions(int[] position) {
				System.out.print("("+ position[0] + "," + position[1] + ")     ");
		
	}
	public static void printpositions(int[][] positions) {
		System.out.println();
		for (int i = 0; i < positions.length; i++) {
				System.out.print("("+ positions[i][0] + "," + positions[i][1] + ")     ");
			}
		
	}
	
	// (i and j) is in matrix range
	static boolean isSafe(int i, int j) {
		if (i >= 0 && i < B.length && j >= 0 && j < B[0].length) {
			return !visited[i][j];
		}
		return false;
	}

	public static void resetVisited() {
		for (int r = 0; r < B.length; r++)
			for (int c = 0; c < B[0].length; c++) {
				visited[r][c] = false;
			}
	}

	public static int[][] getAdj1PositionsThatAreNotVisited(int i, int j) {
		int pos[][] = { { i, j + 1 }, { i, j - 1 }, { i - 1, j }, { i + 1, j }, { i - 1, j - 1 }, { i - 1, j + 1 },
				{ i + 1, j - 1 }, { i + 1, j + 1 } };
		List<int[]> poss = new LinkedList<int[]>();
		//here you are check and call the safepositions only
		for (int pi =0 ; pi < pos.length; pi++) {
				if (isSafe(pos[pi][0], pos[pi][1] ) ) {
					int[] adj = {pos[pi][0], pos[pi][1]};
					if (B[pos[pi][0]][pos[pi][1]] == 1) //if there is a 1 at this position only then add it to adjList
						poss.add(adj);
					
				}
			}	
        int[][] arr = new int[poss.size()][2];
        return poss.toArray(arr);
	}

}
