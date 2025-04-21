package recursion.lect4;

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
                    int area = findArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public static int findArea(int[][] grid, int i, int j) {
        int area = 1; //Since there is at least one cell the area is at least 1

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;

        int areaAbove = findArea(grid, i + 1, j);//Below
        int areaBelow = findArea(grid, i - 1, j); //Above
        int areaLeft = findArea(grid, i, j - 1); //left
        int areaRight = findArea(grid, i, j + 1); //right

        area = area + areaAbove + areaBelow + areaLeft + areaRight;
        return area;
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
        System.out.println("Answer " + maxArea);
    }
}
