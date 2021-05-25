package recursion.lect3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/*
Fnal
https://leetcode.com/problems/longest-string-chain/
 * S * https://leetcode.com/problems/longest-string-chain/
 * See hint on leetcode
 * Given a list of valid words words, and a string s,
 * return the length of the longest chain of diminishing words that can be formed by
 * starting at s and removing single letters and still make valid words.
 * <p>
 * Example 1
 * Input
 * <p>
 * words = ["pies", "prices", "pries", "prying", "coffee", "mug", "pool", "type"]
 * s = "prices"
 * Output
 * <p>
 * 3
 * Explanation
 * <p>
 * We can make the chain, starting from "prices", "prices" -> "pries" -> "pies".
 *
 * https://blog.cancobanoglu.net/2016/09/18/interview-questions-string-chain/
 */
public class OneLessDiminishingword {

    static String[] wordsArray;
    static Set<String> wordSet;
    static boolean wordInSet(String word) {
        return wordSet.contains(word) ? true : false;
    }

    public int lengthOfWordLessBy1Char(String word, String[] words) {
        if (word == null || word.isEmpty() || !wordSet.contains(word)) return 0;
        int maxLength = 0;
        for (int i = 0; i < word.length(); i++) {
            String a = word.substring(0, i);
            String b = word.substring(i + 1);
            String wordLessBy1 =  a + b;
            int x = lengthOfWordLessBy1Char(wordLessBy1, words);
            maxLength = Math.max(maxLength, x);

        }
//        for (int i = 0; i < word.length(); i++) {
//            String wordLessBy1 = word.substring(0, i) + word.substring(i + 1);
//            maxLength = Math.max(maxLength, lengthOfWordLessBy1Char(wordLessBy1, words));
//        }
        return 1 + maxLength;
    }

    public int solve(String[] words, String s) {
        wordsArray = words;
        wordSet = new HashSet<>(Arrays.asList(wordsArray));
        int length = lengthOfWordLessBy1Char(s, words);
        //we need to count the word too
        return length;
    }

    public static void main(String args[]) {
        OneLessDiminishingword o = new OneLessDiminishingword();
        //wordsArray = new String[]{"lii", "limit", "limi", "li", "coffee", "jug", "pool", "type"};
        wordsArray = new String[] {"pies", "prices", "pries", "prying", "coffee", "mug", "pool", "type"};

        //o.solve(wordsArray, "limit");
        o.solve(wordsArray, "prices");
    }
}
