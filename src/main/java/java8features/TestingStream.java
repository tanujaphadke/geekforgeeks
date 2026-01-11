package java8features;

import java.util.*;
import java.util.stream.Collectors;

public class TestingStream {

    public  static void main ( String[]args){

        System.out.println("Characters to remove " + canBeMadeInPalindrome("abcde" ));
       // secondLargest();
    }

//    Given a string S consisting of lowercase English alphabets, find the minimum number of characters required to be
//    removed such that the characters of the string could be rearranged to form a palindrome.
//    Examples:
//    Input: S = “ababccca”
//    Output: 1
//    Explanation:
//    Remove the occurrence of ‘c’ from the string. Therefore, the modified string is “ababcca”, which can be rearranged
//    to form the palindromic string “cababac”.
//    Therefore, only one removal is required.
//Input: S = abcde
//Output: 4

//            Input: S = abcde
//  aa aa     ababccca                ababcca   cababac
 //  aa x aa
 // wssw
 //
//    Output: 4
    // d a l a d      d a d
    public static int canBeMadeInPalindrome (String  s ){
        char[] chars = s.toCharArray();
        Map<Character, Integer> charCounts = new HashMap<>();
        int oddOccuranceCount = 0;
        for (char c : chars) {
            Integer count = charCounts.get(c);
            if (count == null) {
                charCounts.put(c, 1);
            } else {
                charCounts.put(c, count + 1);
            }
        }
        // ababccca   //a b c d e
        for ( Map.Entry<Character, Integer> each : charCounts.entrySet() ){
            if ( each.getValue() % 2 != 0 ) {
                oddOccuranceCount = oddOccuranceCount + 1 ;
            }
            else  ;
        }

//        if ( chars.length % 2 == 0 ) {
//            //int notevenCount = 0 ;
//            for ( Map.Entry<Character, Integer> each : charCounts.entrySet() ){
//                if ( each.getValue() % 2 == 0 ) ;
//                else ;//isPalindrome = false ;
//            }
//        } else { //it is odd number of characters a b c X c B a
            //int oddOccuranceCount = 0 ; int notEvenCount = 0 ;
//            for ( Map.Entry<Character, Integer> each : charCounts.entrySet() ){
//                if ( each.getValue() % 2 != 0 ) {
//                    oddOccuranceCount = oddOccuranceCount + 1 ;
//                }
//                else  ;
//            }
            //if  ( oddOccuranceCount > 1) isPalindrome = false ;
        //}
        return oddOccuranceCount -1 ;
    }




    public static int secondLargest (){
        //Find second largest element in a list of integers using streams.
        List< Integer > nums = Arrays.asList(1, 17, 54, 14, 14, 33, 45, -11);
        nums.sort((a1, a2) -> a1.compareTo(a2));

        nums.stream().forEach( x-> System.out.println(x));

        List< Integer >  sortedNums = nums.stream().sorted().collect(Collectors.toList());


        int sescondLargest = nums.get(nums.size() - 2);
        System.out.println(sescondLargest);
        //BElow is the correct solution to find the second largest.
        List< Integer > numbers = Arrays.asList(1, 17, 54, 14, 14, 33, 45, -11);
        Optional<Integer> secondLargest = numbers.stream()
                .distinct() // Remove duplicates
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .skip(1) // Skip the largest element
                .findFirst(); // Find the second largest

        if (secondLargest.isPresent()) {
            System.out.println("The second largest element is: " + secondLargest.get());
        } else {
            System.out.println("Could not find a second largest element (list might be too small or empty).");
        }
       return sescondLargest;
    }


}
