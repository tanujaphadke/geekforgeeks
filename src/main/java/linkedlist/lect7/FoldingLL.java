package linkedlist.lect7;

import linkedlist.lect5_6.LLNode;

public class FoldingLL {
    public LLNode fold(LLNode next, LLNode head){
        if ( next == null) return null;
        LLNode returnHead = fold ( next.next, head);
        if ( returnHead == null ) {
            //Sum the the folded node
            next.val = next.val + head.val;
            LLNode nextHead = head.next;
            head.next = null ;
            return nextHead;

        }else {
            if ( returnHead == next ) {
                return next;
            }
            if ( next.next == null ){ //means means now this is the first half of the linked list that has
                //been processed. There were the returned heads from previous recursions
                return returnHead; //Since we have ot
            }
            next.val = next.val + returnHead.val;
            LLNode nextHead = returnHead.next;
            returnHead.next = null ;
            return nextHead;
        }


    }
    public LLNode solve(LLNode node) {
        LLNode head = node ;
        // Write your code here
        return ( fold(node,node));
    }

    public static void main ( String args[]){
        FoldingLL fl = new FoldingLL();
        LLNode first = new LLNode();first.val= 1;
        LLNode second = new LLNode(); second.val=2;
        LLNode third = new LLNode(); third.val=3;
        LLNode fourth = new LLNode(); fourth.val=4;
        LLNode fifth = new LLNode(); fifth.val=5;
        first.next=second;
        second.next=third;
        third.next=fourth;
        fourth.next=fifth;
        LLNode foldedHead = fl.solve(first);
        System.out.println(foldedHead);
    }
}
