package tries.lecture20;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/boggle-set-2-using-trie/
//https://leetcode.com/problems/word-search-ii/description/
 class Boggle {

	public static char[][] B = { 
			{ 'o', 'a', 'a', 'n' }, 
			{ 'e', 't', 'a', 'e' },
			{ 'i', 'h', 'k', 'r' },
			{ 'i', 'f', 'l', 'v' } }; // 4 X4 board.
	public static boolean[][] visited = { { false, false, false, false }, { false, false, false, false },
			{ false, false, false, false }, { false, false, false, false }, }; // 4 X4 board.
	static String words[] = { "oath", "pea", "eat", "rain" };
	static List<String> boardWords = new ArrayList<String>();

	public static void main(String args[]) {
		TrieTree trieTree = new TrieTree();
		TrieNode root = trieTree.insertTrie(words);
		for (int i = 0; i < B.length; i++) { // rows
			for (int j = 0; j < B[i].length; j++) {
				final char charOnBoard = B[i][j];
				process(root, i, j, charOnBoard, "");
				resetVisited();
			}
		}
		boardWords.stream().forEach(w -> System.out.println(w));

	}

	private static void process(TrieNode node, int i, int j, char charOnBoard, String word) {
		boolean foundCharInTrie = node.isGateOpen(charOnBoard);
		if (foundCharInTrie && node.isWord) {
			boardWords.add(word + charOnBoard);
			return;
		}
		if (foundCharInTrie) {
			visited[i][j] = true;
			int[][] adjCharPositions = getAdjCharsPositions(i, j);
			TrieNode next = node.getChild(charOnBoard);

			for (int[] eachPosition : adjCharPositions) {
				if (isSafe(eachPosition[0], eachPosition[1])) {// Check if the index in within array range.
					char adjChar = B[eachPosition[0]][eachPosition[1]];
					// pick up only these each.
					process(next, eachPosition[0], eachPosition[1], adjChar, word + charOnBoard);
				}
			}

		}
	}

	// function to check that current location
	// (i and j) is in matrix range
	static boolean isSafe(int i, int j) {
		if (i >= 0 && i < B.length && j >= 0 && j < B[0].length) {
			return !visited[i][j];
		}
		return false;
	}

	public static void resetVisited() {
		for ( int r=0; r< B.length ; r++)
			for ( int c = 0 ; c <B[0].length ; c ++ ) {
				visited[r][c] = false;
			}
	}

	public static int[][] getAdjCharsPositions(int i, int j) {
		int pos[][] = { { i, j + 1 }, { i, j - 1 }, { i - 1, j }, { i + 1, j }, { i - 1, j - 1 }, { i - 1, j + 1 },
				{ i + 1, j - 1 }, { i + 1, j + 1 } };

		// Check that the visited array for each of this is not true.
		// only then return the adj positions
		return pos;
	}


}
