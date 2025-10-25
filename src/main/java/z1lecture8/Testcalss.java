package z1lecture8;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Testcalss {

    //Write a method that checks if s1 is substring of s2 and returns number of occurances if found
    //else return -1

    public static void main ( String args[]){
       int occurances =  checkSubstring("this is a good book", "is");
       System.out.println("The result is " + occurances);
       occurances =  checkSubstringWords("this is a good book", "is");
       System.out.println("The result with distinct words is " + occurances);
    }

    public static int checkSubstring(String sourceStr, String lookFor){
        String tmp = sourceStr; int count = 0; //This is a good book , is
        while (true) {
            if (tmp.indexOf(lookFor) != -1) {
                tmp = tmp.replaceFirst(lookFor, "");
                count = count + 1;

            } else {
                 break;
            }
        }
        return  count;
    }

    public static int checkSubstringWords(String sourceStr, String lookFor){
        StringTokenizer sb = new StringTokenizer(sourceStr);
        String [] splittdString = sourceStr.split(" "); //Replace it with regular expression for any number of spaces
        Map<String, Integer> wordMap = new HashMap<>();
        for ( String ech : splittdString){
            if ( wordMap.get(ech) != null )  {
                wordMap.put(ech, wordMap.get(ech)  + 1 );
            }else{
                wordMap.put(ech,1 );

            }
        }
        return  wordMap.get(lookFor) == null ? 0 : wordMap.get(lookFor) ;
    }
}
