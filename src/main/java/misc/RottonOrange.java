package geekforgeeks.misc;

//https://leetcode.com/problems/rotting-oranges/
import java.util.HashSet;
import java.util.Set;

public class RottonOrange {
	class Solution {
		
		private Set<String> rotten = new HashSet<String>();//No getter and setter so safe

		/**
		 * This method will rot adjacent oranges and return a Set of coordinates of
		 * rotton oranges
		 */
		public Set<String> rotOranges(int[][] grid, Set<String> rottonCoordinates) {

			int MAX_I = grid.length - 1;
			int MAX_J = grid[0].length - 1; // max number of columns
			Set<String> adj = new HashSet<>(); // This willbe of size 4
			for (String eachCoordinate : rottonCoordinates) {
				String[] ij = eachCoordinate.split(":");
				int i = Integer.valueOf(ij[0]);
				int j = Integer.valueOf(ij[1]);
				int[] right, left, top, bottom;
				right = left = top = bottom = new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE };
				if (j + 1 <= MAX_J)
					right = new int[] { i, j + 1 }; // right cell
				if (j - 1 >= 0)
					left = new int[] { i, j - 1 };
				if (i - 1 > -0)
					top = new int[] { i - 1, j };
				if (i + 1 <= MAX_I)
					bottom = new int[] { i + 1, j };
				rotThis(grid, right);
				rotThis(grid, left);
				rotThis(grid, top);
				rotThis(grid, bottom);
			}

			return null;

		}

		private void rotThis(int[][] grid, int[] coordinate) {
			if (coordinate[0] == Integer.MIN_VALUE || coordinate[1] == Integer.MIN_VALUE)
				return; // our of bound Indexes, Invalid.
			if (grid[coordinate[0]][coordinate[1]] != 0 || grid[coordinate[0]][coordinate[1]] != 2) {
				// If the positin in grid is not empty or rotton already then rot this
				grid[coordinate[0]][coordinate[1]] = 2;
			}
		}

		public Set<String> getRottonCoordinates(int[][] grid) {
			Set<String> rotton = new HashSet();
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == 2) {
						rotton.add(i + ":" + j);
					}
				}
			}
			return rotton;

		}

		public int orangesRotting(int[][] grid) {
			// DO some boundary error checks here
			int minute = 1; // start with 1st minute
			int previousRottenSize = -1;
			int currentRottenSize = 0;
			rotten = getRottonCoordinates(grid);
			currentRottenSize = rotten.size();

			while (minute > 0) {
				previousRottenSize = currentRottenSize;

				// This is first minute
				for (String echCoordinate : rotten) {
					rotOranges(grid, rotten);
				}
				//Instead of going over n X m grid again and again , we can keep adding elements to the rotton set
				// by declaring rotton as global or pass rotten to alter it ( not good practice to pass ) 
				rotten = getRottonCoordinates(grid);
				currentRottenSize = rotten.size();
				// When all the oranges are rotton or no more oranges can rot
				// Then the lenth of the rottonOranges array will not increase anymore
				// so we break at that point
				if (previousRottenSize == currentRottenSize) {
					break;
				}
				// start the next minute
				minute = minute + 1;
			}

			int minimumMinutes = minute - 1; // This can be positive or negative
			return minimumMinutes;
		}
	}

	public static void main(String args[]) {
		Integer x = Integer.MIN_VALUE;
	}

}
