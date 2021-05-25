package recursion.lect3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OnelessDminishWordLawrenceSOL {
    static String[] wordsArray;
    static Set<String> wordSet;

    static boolean wordInSet(String word) {
        return wordSet.contains(word) ? true : false;
    }

    public static int oneLessLengthHelper(String word, String[] words) {
        if (word == null || word.isEmpty() || !wordSet.contains(word)) return 0;
        int maxLength = 0;
        for (int i = 0; i < word.length(); i++) {
            String wordLessBy1 = word.substring(0, i) + word.substring(i + 1);
            maxLength = Math.max(maxLength, oneLessLengthHelper(wordLessBy1, words));
        }

        return 1 + maxLength;
    }

    public int solve(String[] words, String s) {
        wordsArray = words;
        wordSet = new HashSet<>(Arrays.asList(wordsArray));
        int length = oneLessLengthHelper(s, words);
        //we need to count the word too
        return length;
    }

}
