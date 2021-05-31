package linkedlist.lect5_6;

//https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
public class LinkedListPalindrome {

    //3-->4 --> 5-->6--> 5-->4 -->3
    //3-->4 --> 5-->6-->6--> 5-->4 -->3
    //3-->4 --> 5-->6--> 5-->4 -->3 -->4-->5-->6-->7

    static class LLPalindrome {
        public LLNode nextHeadToCheck;
        public boolean isPalindrome;

        public LLPalindrome(LLNode nextHeadToCheck, boolean isPalindrome) {
            this.nextHeadToCheck = nextHeadToCheck;
            this.isPalindrome = isPalindrome;
        }
    }

    //3-->4 --> 5-->6--> 5-->4 -->3
    public static LLPalindrome checkPalindrome(LLNode head, LLNode node) {
        if (head == null || node == null) {
            LLPalindrome pal = new LLPalindrome(head, true);
            return pal;
        }
        LLPalindrome retPal = checkPalindrome(head, node.next);
        if (!retPal.isPalindrome) {
            return retPal; //Propogage the result in the return path
        }
        LLNode compareWithThisHead = retPal.nextHeadToCheck;
        if ( retPal.isPalindrome && compareWithThisHead == null ) {
            return retPal;
        }
        //Even case will have head.next == node.3-->4 --> 5-->6-->6--> 5-->4 -->3
        if ((compareWithThisHead.next == node && compareWithThisHead.val == node.val) ||
                //Odd case //3-->4 --> 5-->6---> 5-->4 -->3
                (compareWithThisHead == node )) {

            LLPalindrome llPalindrome = new LLPalindrome(null, true);
            return llPalindrome;
        } else if (compareWithThisHead.val == node.val) {//Keep advancing the head
            LLPalindrome pal = new LLPalindrome(compareWithThisHead.next, true);
            return pal;
        } else { //Palindrome Found
            LLPalindrome pal = new LLPalindrome(null, false);
            return pal;
        }
    }

    public static boolean isPalindrome(LLNode node) {
        LLPalindrome pal = checkPalindrome(node, node);
        return pal.isPalindrome;
    }

    static class PalindromeChecker {
        public boolean IsPalindrome;
        public LLNode headToCompareWith;

        public PalindromeChecker(boolean found, LLNode headToCompareWith) {
            this.IsPalindrome = found;
            this.headToCompareWith = headToCompareWith;
        }
    }

    /**
     * This checks each node two times
     * @param head
     * @param node
     * @return 3-->4 --> 5-->6--> 5-->4 -->3
     */
    public static PalindromeChecker isPalindromDoubleComparisions(LLNode head, LLNode node) {
        if (node == null) return new PalindromeChecker(true, head); //The node to compare with;
        PalindromeChecker palindromeChecker = isPalindromDoubleComparisions(head, node.next);

        if (palindromeChecker.IsPalindrome) {
            if (palindromeChecker.headToCompareWith.val == node.val) {
                return new PalindromeChecker(true, palindromeChecker.headToCompareWith.next);
            }
            return new PalindromeChecker(false, null); //else return null ;means not a palindrom
        }
        return palindromeChecker;

    }


    public static boolean isPalindrom2(LLNode head) {
        if (head == null || head.next == null) return true;
        boolean isp = isPalindromDoubleComparisions(head, head).IsPalindrome;
        return isp;
    }

    static LLNode oddPalindromeHEAD = PopulateLinkedList.populateOddLengthPalindrome();
    static LLNode evenPalindromeHEAD = PopulateLinkedList.populateEvenLengthPalindrome();

    public static void main(String args[]) {
        System.out.println("For odd Length 3-->4 --> 5-->6--> 5-->4 -->3  " + isPalindrome(oddPalindromeHEAD));
        System.out.println("For even length 3-->4 --> 5-->6-->6--> 5-->4 -->3  " + isPalindrome(evenPalindromeHEAD));
        System.out.println("For nonPalindromLinkedList 3-->4 --> 5-->6-->6--> 5-->4 -->3  " + isPalindrome(PopulateLinkedList.populateLL(false)));

        System.out.println("Other way For odd Length 3-->4 --> 5-->6--> 5-->4 -->3  " + isPalindrom2(oddPalindromeHEAD));
        System.out.println("Other way For odd Length 3-->4 --> 5-->6--> 5-->4 -->3  " + isPalindrom2(evenPalindromeHEAD));
        System.out.println("Other way For odd Length   " + isPalindrom2(PopulateLinkedList.populateLL(false)));

    }


}
