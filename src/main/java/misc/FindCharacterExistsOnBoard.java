package misc;
/*

 Given a 2D board of characters and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or
vertically neighboring. The same letter cell may not be used more than once.
For example, given the following board:
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
exists(board, "ABCCED") returns true, exists(board, "SEE") returns true, exists(board, "ABCB") returns false.
 */

import java.util.*;


public class FindCharacterExistsOnBoard {


    static char[][] grid = new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
    };
    public List<List<int[]>> findAllPaths(String str, int row, int col) {
        if ((row == 0 && col == 0) && (str == null || str.length() == 0)) {
            return null;
        } else if (str.length() == 0) {
            return new ArrayList<List<int[]>>();
        } else {
            List<int[]> possiblePositions = new ArrayList();
            char lookFor = str.charAt(0);
            if (row + 1 < grid.length && (grid[row + 1][col] == str.charAt(0))) {
                int[] pos = new int[2];
                pos[0] = row + 1;
                pos[1] = col;
                possiblePositions.add(pos);
            }
            if (row - 1 > -1 && (grid[row - 1][col] == str.charAt(0))) {
                int[] pos = new int[2];
                pos[0] = row - 1;
                pos[1] = col;
                possiblePositions.add(pos);
            }

            if (col + 1 < grid[0].length && (grid[row][col + 1] == str.charAt(0))) {
                int[] pos = new int[2];
                pos[0] = row;
                pos[1] = col + 1;
                possiblePositions.add(pos);
            }

            if (col - 1 > -1 && (grid[row][col - 1] == str.charAt(0))) {
                int[] pos = new int[2];
                pos[0] = row;
                pos[1] = col - 1;
                possiblePositions.add(pos);
            }

            if (possiblePositions.size() == 0) return null;

            List<List<int[]>> allPaths = new ArrayList<List<int[]>>();

            for (int[] eachPos : possiblePositions) {
                List<List<int[]>> listOffoundPaths = findAllPaths(str.substring(1), eachPos[0], eachPos[1]);
                if (listOffoundPaths != null) { // this will happen only once when the DFS reaches the tail
                    if (  listOffoundPaths.isEmpty()){
                        int[] positionOfLastChar = new int[]{row, col};
                        List<int[]> x =  new ArrayList<>();
                        x.addAll(possiblePositions);
                        allPaths.add(0,x);
                        return allPaths;
                    }
                    for (List<int[]> eachPath : listOffoundPaths) {
                        eachPath.add(0, new int[]{row, col});
                    }
                    allPaths.addAll(0,listOffoundPaths);
                }
            }
            return allPaths;
        }
    }


    public boolean find(String str, int row, int col) {
        if ((row == 0 && col == 0) && (str == null || str.length() == 0)) {
            return false;
        } else if (str.length() == 0) {
            return true;
        } else {
            List<int[]> possiblePositions = new ArrayList();
            char lookFor = str.charAt(0);
            if (row + 1 < grid.length && (grid[row + 1][col] == lookFor)) {
                int[] pos = new int[2];
                pos[0] = row + 1;
                pos[1] = col;
                possiblePositions.add(pos);
            }
            if (row - 1 > -1 && (grid[row - 1][col] == lookFor)) {
                int[] pos = new int[2];
                pos[0] = row - 1;
                pos[1] = col;
                possiblePositions.add(pos);
            }
            if (col + 1 < grid[0].length && (grid[row][col + 1] == lookFor)) {
                int[] pos = new int[2];
                pos[0] = row;
                pos[1] = col + 1;
                possiblePositions.add(pos);
            }
            if (col - 1 > -1 && (grid[row][col - 1] == lookFor)) {
                int[] pos = new int[2];
                pos[0] = row;
                pos[1] = col - 1;
                possiblePositions.add(pos);
            }
            if (possiblePositions.size() == 0) return false;
            for (int[] eachPos : possiblePositions) {
                boolean result = find(str.substring(1), eachPos[0], eachPos[1]);
                if (result == true) return true;
            }

        }

        return false;
    }

    @Deprecated
    private void putPossiblePosition(int row, int col, char lookFor, Map<Character, List<int[]>> possiblePositions) {
        int[] coordinate = new int[2];
        coordinate[0] = row + 1;
        coordinate[1] = col;
        List<int[]> foundPositions = possiblePositions.get(lookFor);
        if (foundPositions == null) {
            List<int[]> positions = new ArrayList();
            positions.add(coordinate);
        } else {
            foundPositions.add(coordinate);
        }
    }

    /**
     * Finds all the possible positions of the first character of the String on the board
     *
     * @param str

     * @return
     */
    public boolean isStringOnBoard(String str) {
        char chars[] = str.toCharArray();
        char[] possibleChars = new char[4];
        List<List<int[]>> allPaths = new ArrayList<List<int[]>>();
        for (int r = 0; r < grid.length; r++) {// No of rows
            for (int c = 0; c < grid[0].length; c++) { // no of columns
                if (grid[r][c] == chars[0]) {
                    int[] position = {r, c};
                    List<List<int[]>> foundPaths = findAllPaths(str.substring(1), r, c);
                    if (foundPaths != null && !foundPaths.isEmpty()) {
                        int[] sourcePos = new int[2];
                        sourcePos[0] = r;
                        sourcePos[1] = c;
                        for (List<int[]> eachPath : foundPaths) {
                            eachPath.add(0, sourcePos);
                        }
                        allPaths.addAll(0, foundPaths);
                    }
                }
            }
        }
        if (allPaths != null && !allPaths.isEmpty()) {
            return true;
        }
        return false;
    }


    public static void main(String args[]) {
        String word = "ABCCED";

        FindCharacterExistsOnBoard f = new FindCharacterExistsOnBoard();
        f.isStringOnBoard(word);
    }
}

