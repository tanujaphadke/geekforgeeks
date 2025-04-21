package recursion.lect4;

/*
https://www.geeksforgeeks.org/find-number-of-islands/
https://en.wikipedia.org/wiki/Connected-component_labeling
http://www.ideserve.co.in/learn/number-of-clusters-of-1s
http://www.ideserve.co.in/learn/number-of-clusters-of-1s#algorithmVisualization
http://www.ideserve.co.in/learn/number-of-clusters-of-1s#algorithmVisualization
http://javabypatel.blogspot.in/2016/08/find-number-of-islands-using-dfs.html
https://www.youtube.com/watch?v=dA1FYFlvckY
https://www.youtube.com/watch?v=W8VuDt0eb5c
https://www.quora.com/What-is-an-efficient-algorithm-to-find-an-island-of-connected-1s-in-a-matrix-of-0s-and-1s

 */
public class CountIsland {
    private static int MAX_ROW;
    private static int MAX_COL;

    public static int countIsland(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int found = restAllConnectedIslands(grid, i, j);
                    count = count + found;
                }
            }
        }
        return count;
    }

    /**
     * we can find area and then use this area to calculate if an island exists or not.
     * Since we only need to count the islands we need not caculate area. we can only reset all the
     * connected islands and return 1. The only objective of calling this method is that we see all the
     * connected islands and reset them to 1 so that they are not counted again.
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public static int restAllConnectedIslands(int[][] grid, int i, int j) {
        int area = 1;

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;//Reset islands to 1

        restAllConnectedIslands(grid, i + 1, j);//Below
        restAllConnectedIslands(grid, i - 1, j); //Above
        restAllConnectedIslands(grid, i, j - 1); //left
        restAllConnectedIslands(grid, i, j + 1); //right

        //area = area + areaAbove + areaBelow + areaLeft + areaRight;
        return 1;
    }


    public static void main(String args[]) {
        int M[][] = {{0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1}
        };
        int grid[][] = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };


        int count = countIsland(grid);
        System.out.println("Anser " + count);
    }
}
