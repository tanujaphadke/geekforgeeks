package java8features;

import linkedlist.lect5_6.LLNode;
import linkedlist.lect5_6.PopulateLinkedList;

public class Palindrome {
//https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/

    //3-->4 --> 5-->6--> 5-->4 -->3
    //3-->4 --> 5-->6-->6--> 5-->4 -->3
    //3-->4 --> 5-->6--> 5-->4 -->3 -->4-->5-->6-->7

    static class PalindromeStatus {
        public LLNode headtoCompareWith;
        public boolean isPalindrome;

        public PalindromeStatus(LLNode nextHeadToCheck, boolean isPalindrome) {
            this.headtoCompareWith = nextHeadToCheck;
            this.isPalindrome = isPalindrome;
        }
    }

    //3-->4 --> 5-->6--> 5-->4 -->3
    public static PalindromeStatus checkPalindrome(LLNode head, LLNode node) {
        if (node == null) {
            PalindromeStatus palindromeStatus = new PalindromeStatus(head, true);
            return palindromeStatus;
        }

        PalindromeStatus palindromeStatus = checkPalindrome(head, node.next);
        if (!palindromeStatus.isPalindrome) return new PalindromeStatus(null, false);
        if (palindromeStatus.headtoCompareWith == null) return palindromeStatus;
        if (palindromeStatus.headtoCompareWith.val == node.val) {
            if (palindromeStatus.headtoCompareWith.next == node ||
                    palindromeStatus.headtoCompareWith.next.next == node) {
                return new PalindromeStatus(null, true);
            } //else
            PalindromeStatus ps = new PalindromeStatus(palindromeStatus.headtoCompareWith.next, true);
            return ps;
        }
        return new PalindromeStatus(null, false);
    }


    public static boolean isPalindrome(LLNode node) {
        if (node == null) return true;
        PalindromeStatus pal = checkPalindrome(node, node);
        return pal.isPalindrome;
    }

    static LLNode oddPalindromeHEAD = PopulateLinkedList.populateOddLengthPalindrome();
    static LLNode evenPalindromeHEAD = PopulateLinkedList.populateEvenLengthPalindrome();

    public static void main(String args[]) {
        System.out.println("For odd Length 3-->4 --> 5-->6--> 5-->4 -->3  " + isPalindrome(oddPalindromeHEAD));
        System.out.println("For even length 3-->4 --> 5-->6-->6--> 5-->4 -->3  " + isPalindrome(evenPalindromeHEAD));
        System.out.println("For nonPalindromLinkedList  "
                + isPalindrome(PopulateLinkedList.populateLL(false)));

    }


}
