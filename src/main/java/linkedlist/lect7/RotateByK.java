package linkedlist.lect7;

import linkedlist.lect5_6.LLNode;

/**
 Given a linked list and a positive integer k, rotate the list to the right by k places.

 For example, Given the linked list 1 → 2 → 3 → 4 → 5 → 6 and k = 2, it should become 5 → 6 → 1 → 2 → 3 → 4.

 Note: The linked list is guaranteed to have at least one element, and k is guaranteed to be less than or equal to the length of the list.


 */

public class RotateByK {
    public LLNode solve(LLNode node, int k) {
        // Write your code here
        LLNode root = node;
        LLNode first = node;
        LLNode second = node;
//        if k = 0 your second.next moves along with first.next until the end of the list
//
//
//        so it eventually becomes null when you return, you can handle that case with:

        if (k == 0) {
            return node;
        }

        for (int i = 0; i < k; i++) {
            first = first.next;
        }
        if ( first == null ) return root; //means k = size of list
        while (first.next != null) {
            second = second.next;
            first = first.next;
        }


        LLNode moveFrom = second.next; // start of the ll  has to be moved,
        second.next = null;
        first.next = root;

        return moveFrom;


    }

    public static void main(String args[]) {
        RotateByK r = new RotateByK();
        //1 → 2 → 3 → 4 → 5 → 6 → null
        LLNode first = new LLNode();
        first.val = 1;
        LLNode second = new LLNode();
        second.val = 2;
        LLNode third = new LLNode();
        third.val = 3;
        LLNode fourth = new LLNode();
        fourth.val = 4;
        LLNode fifth = new LLNode();
        fifth.val = 5;
        LLNode sixth = new LLNode();
        sixth.val = 6;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;

        LLNode ans =r.solve(first, 3);
        System.out.println(ans);
    }
}

