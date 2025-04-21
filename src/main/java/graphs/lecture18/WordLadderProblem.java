package graphs.lecture18;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
/* aaa ( L1) / \ \ \ \ axz aba aba ( L2) /auw \ \ / abd abc ( L3) \ aaa abc
 http://www.crazyforcode.com/word-ladder-problem/ */
/* This question is about transforming one word into a second word. You are given a start word, end word,
and dictionary of all valid words. The goal is to transform the start word into the end word by
iteratively changing one character at a time. Every generated word must exist in the dictionary of valid words.
The output is the minimum set of required transformations between the start and end words.
Examples: start = "aaa" end = "abc" all_words = ["aaa", "abb", "bbb", "aba", "acc", "abc", "ccc", "aad"]
output: ["aaa", "aba", "abc"] start = "aaa" end = "cac"
all_words = ["aaa", "aba", "cba", "cbc", "cac", "cdc", "bbb", "ccc"]
output: ["aaa", "aba", "cba", "cbc", "cac"] cat -> cot -> cog ->dog
 Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 Output: [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
 aaa - - - - - - - - - - - - .... - - - 26 b - - - - - - - - - - -25 - - - 24 aaa ( L1) / \ \ \ \ axz aba aba ( L2)
  /auw \ \ / abd abc ( L3) \ aaa abc */

class WordNde {
    String word;
    char[] charsOfWord;
    int distance;
    WordNde parent;

    public WordNde(String word, int distance, WordNde parent) {
        this.word = word;
        this.distance = distance;
        this.charsOfWord = word.toCharArray();
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "WordNde [word=" + word + ", distance=" + distance + "]";
    }

    WordNde(String word) {
        this(word, 0, null);
    }
}

public class WordLadderProblem {
    private final String[] allWords = {"pop", "hop", "top", "hap", "tap", "aad"};
    private final List<String> allWordsDictionaryList = new ArrayList<String>(Arrays.asList(allWords));

    public String getpath(String source, String target) {
        //source = pop target = tap
        allWordsDictionaryList.remove(source);
        List<String> dictCopy = new CopyOnWriteArrayList<String>(allWordsDictionaryList);
        Collections.copy(dictCopy, allWordsDictionaryList);

        WordNde foundWordNode = null;
        Queue<WordNde> queue = new LinkedList<WordNde>();
        WordNde sourceWordNode = new WordNde(source);
        queue.add(sourceWordNode);

        Iterator<String> iter = dictCopy.iterator();
        while (!queue.isEmpty()) {
            WordNde peekedWord = queue.poll();
//            if (peekedWord.word.equalsIgnoreCase(target)) {
//                foundWordNode = peekedWord;
//                break;
//            }
            iter = dictCopy.iterator(); //reinitialize the Iterator else it will return false;
            while (iter.hasNext()) {
                String theDictWord = iter.next();
               if (isOneCharDifferent(peekedWord.word, theDictWord)) {
                    //iter.remove(); //Would throw UnsupportedOperationException
                    allWordsDictionaryList.remove(theDictWord);

                    WordNde theDictWordNode = new WordNde(theDictWord, peekedWord.distance + 1, peekedWord);
                    if (theDictWordNode.word.equalsIgnoreCase(target)) {
                        foundWordNode = theDictWordNode;
                        break;
                    }
                    queue.add(theDictWordNode);
                }
            }
            //REset the COPY List
            dictCopy = new CopyOnWriteArrayList<String>(allWordsDictionaryList);
            Collections.copy(dictCopy, allWordsDictionaryList);


            if (foundWordNode != null) {
                break;
            }
        } // Print the foundwordNode
        StringBuilder path = new StringBuilder();
        do {
            path.insert(0, "-->" + foundWordNode.word);
            foundWordNode = foundWordNode.parent;
        }while (foundWordNode != null);
        return path.toString();
    }

    public boolean isOneCharDifferent(String in, String dicWord) {
        char[] inarray = in.toCharArray();
        char[] dictArray = dicWord.toCharArray();
        if (inarray.length - dictArray.length > 1)
            return false; // we can have axd and axdw with differing lengths but, only 1 char different
        int differentCount = 0;
        if (inarray.length == dictArray.length) {
            for (int i = 0; i < inarray.length; i++) {
                if (inarray[i] != dictArray[i]) {
                    differentCount++;
                }
                if (differentCount > 1) return false;
            }
        } else {
            if (inarray.length < dictArray.length) {
                for (int i = 0; i < inarray.length; i++) {
                    if (inarray[i] != dictArray[i]) {
                        differentCount++;
                    }
                    if (differentCount == 1) return false;
                }
            } else {
                for (int i = 0; i < dictArray.length; i++) {
                    if (inarray[i] != dictArray[i]) {
                        differentCount++;
                    }
                    if (differentCount == 1) return false;
                }
            }
        }
        return true;
    }

    public static void main(String args[]) {
        WordLadderProblem prob = new WordLadderProblem();
        String path = prob.getpath("pop", "tap");
        System.out.println(path);
    }

    // GEEKofGeeks solution Returns length of shortest chain to reach 'target' from 'start' using minimum number of adjacent moves. D is dictionary
    static int shortestChainLen(String start, String target, Set<String> D) {

        // If the target String is not present in the dictionary
        if (!D.contains(target))
            return 0;

        // To store the current chain length
        // and the length of the words
        int level = 0, wordlength = start.length();

        // Push the starting word into the queue
        Queue<String> Q = new LinkedList<>();
        Q.add(start);

        // While the queue is non-empty
        while (!Q.isEmpty()) {

            // Increment the chain length
            ++level;

            // Current size of the queue
            int sizeofQ = Q.size();

            // Since the queue is being updated while it is being traversed so only the elements which were already
            // present in the queue before the start of this loop will be traversed for now
            for (int i = 0; i < sizeofQ; ++i) {

                // Remove the first word from the queue
                char[] word = Q.peek().toCharArray();
                Q.remove();

                // For every character of the word
                for (int pos = 0; pos < wordlength; ++pos) {

                    // Retain the original character
                    // at the current position
                    char orig_char = word[pos];

                    // Replace the current character with
                    // every possible lowercase alphabet
                    for (char c = 'a'; c <= 'z'; ++c) {
                        word[pos] = c;

                        // If the new word is equal to the target word
                        if (String.valueOf(word).equals(target))
                            return level + 1;

                        // Remove the word from the set if it is found in it
                        if (!D.contains(String.valueOf(word)))
                            continue;
                        D.remove(String.valueOf(word));

                        // And push the newly generated word which will be a part of the chain
                        Q.add(String.valueOf(word));
                    }

                    // Restore the original character
                    // at the current position
                    word[pos] = orig_char;
                }
            }
        }

        return 0;
    }
}


 

