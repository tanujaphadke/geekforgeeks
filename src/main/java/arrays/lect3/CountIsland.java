package arrays.lect3;

import java.util.ArrayList;
import java.util.List;

public class CountIsland {
    private static int MAX_ROW;
    private static int MAX_COL;

    //Diagonal is not considered as a neighbor. If you want to include diagonals also then
    //those coordinates can be easily added.
    private List<int[]> getNeighboursWith1(int[][] matrix, int r, int c) {

        List<int[]> neighborsWith1 = new ArrayList<int[]>();
        int[] nonzero = new int[2];//store r , c
        matrix[r][c] = -1;
        if ((c + 1 < MAX_COL) && (matrix[r][c + 1] == 1)) {
            //since we have covered them in 1 island we dont want to cover them again
            //So lets change their value to something else. Changing it to non zero
            //is better since we know which ones were 1's
            matrix[r][c + 1] = -1;
            neighborsWith1.add(new int[]{r, c + 1});
        }
        if ((c - 1 > 0) && (matrix[r][c - 1] == 1)) {
            matrix[r][c - 1] = -1;
            neighborsWith1.add(new int[]{r, c - 1});
        }
        if ((r - 1 > 0) && (matrix[r - 1][c] == 1)) {
            matrix[r - 1][c] = -1;
            neighborsWith1.add(new int[]{r - 1, c});

        }
        if ((r + 1 < MAX_ROW) && (matrix[r + 1][c] == 1)) {
            matrix[r + 1][c] = -1;
            neighborsWith1.add(new int[]{r + 1, c});
        }
        return neighborsWith1;
    }

    //We just want to count island, so we can just return 1
    public int countIslands(int[][] matrix, int r, int c) {
        List<int[]> neighborsWith1 = getNeighboursWith1(matrix, r, c);
        int count = 0;
        if (neighborsWith1.size() != 0) {
            for (int[] eachCoordinate : neighborsWith1) {
                count = count + countIslands(matrix, eachCoordinate[0], eachCoordinate[1]);
            }
        } else {// (neighborsWith1.size() == 0) {
            return 1;
        }
        if (count > 0) return 1;
        return 0;
    }


    public int solve(int[][] matrix) {
        // Write your code here
         MAX_ROW = matrix.length;
         MAX_COL = matrix[0].length;
        int islandCount = 0;
        for (int r = 0; r < MAX_ROW; r++) {
            for (int c = 0; c < MAX_COL; c++) {
                if (matrix[r][c] == 1) {
                    islandCount += countIslands(matrix, r, c);
                }

            }
        }
        System.out.println(islandCount);
        return islandCount;
    }

    public static void main(String args[]) {
        int matrix[][] =
                {       {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
                };
        CountIsland ci = new CountIsland();
        ci.solve(matrix);
    }
}
