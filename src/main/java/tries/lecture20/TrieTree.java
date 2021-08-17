package tries.lecture20;
//https://www.youtube.com/watch?v=zIjfhVPRZCg

//Good https://www.youtube.com/watch?v=NKr6gWcXkIM&spfreload=5
//https://www.youtube.com/watch?v=NKr6gWcXkIM
//https://www.youtube.com/watch?v=TRg9DQFu0kU
//https://www.geeksforgeeks.org/boggle-set-2-using-trie/
//https://www.youtube.com/watch?v=giiaIofn31A --> Leetcode implemntation

public class TrieTree {
	//Root node which does not map to any character. it just represents value as null
	private static TrieNode root = new TrieNode('\0');

	public static void main(String args[]) {
		String[] words = { "cat", "cats", "ant", "cot" }; // "cow", "cot", "cans", "ant", "anter" };
		char c = 'c';
		System.out.println("A: " + (int) 'a' + " C: " + (int) c);
		TrieNode root = insertTrie(words);
		searchWord("lat");
	}

	public static boolean searchWord(String word) {
		boolean found = searchChar(word, root);
		System.out.println("FOUND " + found);
		return found;

	}

	public static TrieNode searchWordAndReturn(String word) {
		TrieNode found = searchCharAndReturnLastNode(word, root);
		System.out.println("FOUND " + found);
		return found;

	}

	/*
	Returns true if there is a path. Does not check if this word also makes a valid path.
	 */
	public static boolean searchPath(String word) {
		boolean found = searchChar(word, root);
		System.out.println("FOUND " + found);
		return found;

	}

	public static boolean searchChar(String word, TrieNode node) {
		char k = word.charAt(0);
		if (node.isGateOpen(k)) {
			boolean found = true;
			TrieNode child = node.getChild(k);
			if (word.substring(1).isEmpty() && child.isWord) {
				return true;
			}
			else {
				return searchChar(word.substring(1), child);
			}
		} else {
			return false;
		}
	}

	/**
	 * Searches the word and returns the TrieNode that contains the last character of the word;
	 * @param word
	 * @param node
	 * @return
	 */
	public static TrieNode searchCharAndReturnLastNode(String word, TrieNode node) {
		char k = word.charAt(0);
		if (node.isGateOpen(k)) {
			TrieNode child = node.getChild(k);
			if (word.substring(1).isEmpty() && child.isWord) {
				return child;
			}
			else {
				return searchCharAndReturnLastNode(word.substring(1), child);
			}
		} else {
			return null;
		}
	}

	public static TrieNode insertTrie(String[] words) {
		for (String word : words) {
			insertTrieRec(word, root);
		}
		System.out.println();
		return root;

	}

	public static void insertTrieRec(String word, TrieNode currentNode) {
		if (word.isEmpty()) {
			return;
		}
		char k = word.charAt(0);
		if (currentNode.isGateOpen(k)) {
			insertTrieRec(word.substring(1), currentNode.getChild(k));
		} else {
			currentNode.openGate(k);
			String remainingWord = word.substring(1);
			TrieNode nextNode = currentNode.getChild(k); // give the node that was populated after opening gate
			if (remainingWord.isEmpty()) {
				nextNode.isWord = true;
			} else {
				insertTrieRec(remainingWord, nextNode);
			}
		}
	}


}
