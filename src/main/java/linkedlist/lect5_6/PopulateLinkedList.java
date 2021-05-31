package linkedlist.lect5_6;

public class PopulateLinkedList {

    public static  LLNode populateOddLengthPalindrome(){
        //3-->4 --> 5-->6--> 5-->4 -->3
        //3-->4 --> 5-->6--> 5-->4 -->3 -->4-->5-->6-->7
        LLNode n1 = new LLNode(3);
        LLNode n2 = new LLNode(4);
        LLNode n3 = new LLNode(5);
        LLNode n4 = new LLNode(6);
        LLNode n5 = new LLNode(5);
        LLNode n6 = new LLNode(4);
        LLNode n7 = new LLNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        return n1;
    }

    public static  LLNode populateEvenLengthPalindrome(){
        //3-->4 --> 5-->6-->6--> 5-->4 -->3
        LLNode n1 = new LLNode(3);
        LLNode n2 = new LLNode(4);
        LLNode n3 = new LLNode(5);
        LLNode n4 = new LLNode(6);
        LLNode n5 = new LLNode(6);
        LLNode n6 = new LLNode(5);
        LLNode n7 = new LLNode(4);
        LLNode n8 = new LLNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        return n1;
    }
    /*
    Populates a linked list with either even or odd number of nodes depending on the boolean parameter
     */
   public static LLNode populateLL(boolean even) {
        LLNode n1 = new LLNode(1);
        LLNode n2 = new LLNode(15);
        LLNode n3 = new LLNode(12);
        LLNode n4 = new LLNode(21);
        LLNode n5 = new LLNode(10);
        LLNode n6 = new LLNode(66);
        LLNode n7 = new LLNode(77);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        if (!even) {
            n6.next = n7;
        }
        return n1; // head node
    }

    public static  LLNode  populateLoopedLL(){
        LLNode n1 = new LLNode(1);
        LLNode n2 = new LLNode(15);
        LLNode n3 = new LLNode(12);
        LLNode n4 = new LLNode(21);
        LLNode n5 = new LLNode(10);
        LLNode n6 = new LLNode(66);
        LLNode n7 = new LLNode(77);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next= n7;
        // 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
        //create  a loop in the linkedList
        // 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
        //					 ^ --------------------|

        n7.next = n4;
        return n1;

    }
}
