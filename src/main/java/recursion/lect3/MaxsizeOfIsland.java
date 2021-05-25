package recursion.lect3;

/**
 * https://www.youtube.com/watch?v=W8VuDt0eb5c
 * https://leetcode.com/problems/max-area-of-island/
 */
public class MaxsizeOfIsland {

    public static int maxAreaofIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int area = findMaxArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public static int findMaxArea(int[][] grid, int i, int j) {
        int count = 1;

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;

        int countAbove = findMaxArea(grid, i + 1, j);//Below
        int countBelow = findMaxArea(grid, i - 1, j); //Above
        int countLeft = findMaxArea(grid, i, j - 1); //left
        int countRight = findMaxArea(grid, i, j + 1); //right

        count = count + countAbove + countBelow + countLeft + countRight;
        return count;
    }


    public static void main(String args[]) {
        int M[][] = {{0, 0, 1, 1, 0},
                    {1, 0, 1, 1, 0},
                    {0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1}
        };
        int grid[][] = {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };


        int maxArea = maxAreaofIsland(grid);
        System.out.println("Anser " + maxArea);
    }
}
