package linkedlist.lect7;

import linkedlist.lect5_6.LLNode;

//Given a linked list of size N. The task is to reverse every k nodes (where k is an input to the function)
// in the linked list.
public class ReverseInKSlots {

    private static int k = 3;
    private static LLNode head;

//    class NodeSet {
//        LLNode currentTail; //From the list already prcessed
//        LLNode nextTail;
//        LLNode currentHead; //From the list already processed
//
//        public NodeSet(LLNode currentTail, LLNode nextTail, LLNode currentHead) {
//            this.currentTail = currentTail;
//            this.nextTail = nextTail;
//            this.currentHead = currentHead;
//        }
//    }
//
//
//    //1 , 2 3 4 5 6 7 8 9 10 11
//    public NodeSet reverseInKGroups(LLNode node, int pos) { //1,0    2,1 , 3,2
//        if (node == null) return null;
//        pos++;
//        LLNode nextTail = null;
//        if (pos == k || node.next == null) {
//            nextTail = node.next; //   4
//            return new NodeSet(null, nextTail, node);
//        }
//        NodeSet currentNodeSet = reverseInKGroups(node.next, pos); //2, 1     3,2
//        node.next.next = node;
//        node.next = null; //This is required else it goes in an inf loop. I dont know why.
//        currentNodeSet.currentTail = node;
//        return currentNodeSet;
//    }
//
//    public NodeSet kReverse(LLNode head) {
//        NodeSet currentKgroupNodeSet = reverseInKGroups(head, 0); //  1<-- 2 <--3 ,4 <--5 <--6. 7 PT = 1, PH = 3  nT = 4, PH = 6 nt = null;
//        if (currentKgroupNodeSet.nextTail == null) return currentKgroupNodeSet;
//        NodeSet nextKgroupnodeSet = kReverse(currentKgroupNodeSet.nextTail);
//        currentKgroupNodeSet.currentTail.next = nextKgroupnodeSet.currentHead;
//        return currentKgroupNodeSet;
//    }
//
//    @Deprecated
//    //1 , 2 3 4 5 6 7 8 9 10 11
//    public LLNode reverseInK(LLNode node, int pos) { //1,0    2,1 , 3,2
//        if (node == null) return null;
//        pos++;
//        LLNode nextTail = null;
//        if (pos == k || node.next == null) {
//            LLNode currentTail = node;
//            nextTail = node.next; //   4
//            return nextTail;
//        }
//        nextTail = reverseInK(node.next, pos); //2, 1     3,2
//        node.next.next = node;
//        LLNode prevTail = node;
//        return nextTail;
//    }


    class NodePointer {
        LLNode nextHead;
        LLNode currentHead;

        public NodePointer(LLNode currentHead, LLNode nextHead) {
            this.nextHead = nextHead;
            this.currentHead = currentHead;

        }
    }

    //1 , 2 3 4 5 6 7 8 9 10 11
    public NodePointer reverseNodeInGroupOfK(LLNode currentHead, int pos) { //1,0    2,1 , 3,2
        if (currentHead == null) return null; pos++;
        if (pos == k || currentHead.next == null) {
            LLNode nextHead = currentHead.next; //   4
            return new NodePointer(currentHead, nextHead);
        }
        NodePointer nodePointer = reverseNodeInGroupOfK(currentHead.next, pos); //2, 1     3,2
        currentHead.next.next = currentHead;
        currentHead.next = null; //This is required else it goes in an inf loop. I dont know why.
        return nodePointer;
    }
    public LLNode reverseNodeHelper(int k) { //1,0    2,1 , 3,2
        LLNode currentHead = head;
        NodePointer nodePointer = reverseNodeInGroupOfK(currentHead, 0); //means we got nextHead and currentHead
        LLNode prevTail = currentHead;
        currentHead = nodePointer.nextHead;
        LLNode HEAD = nodePointer.currentHead; // This is the HEAD of the whole linkedList which will be returned
        while (nodePointer != null) {
            nodePointer = reverseNodeInGroupOfK(currentHead, 0); //means we got nextHead and currentHead
            //Tail will be the original head that we are passing
            if (nodePointer != null) {
                prevTail.next = nodePointer.currentHead;
                prevTail = currentHead; //Since after reversal the Head will become tail for this group
                currentHead = nodePointer.nextHead;
            }

        }
        return HEAD;
    }

    public static void main(String args[]) {

        LLNode one = new LLNode(1);
        head = one;
        LLNode two = new LLNode(2);
        LLNode three = new LLNode(3);
        LLNode four = new LLNode(4);
        LLNode five = new LLNode(5);
        LLNode six = new LLNode(6);
        LLNode seven = new LLNode(7);
        LLNode eight = new LLNode(8);
        LLNode nine = new LLNode(9);
        LLNode ten = new LLNode(10);
        LLNode eleven = new LLNode(11);


        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;
        eight.next = nine;
        nine.next = ten;
        ten.next= eleven;

        System.out.println(head);
        ReverseInKSlots rk = new ReverseInKSlots();
//        System.out.println(rk.kReverse(head).currentHead);

        head = one;
        System.out.println(rk.reverseNodeHelper(3));
    }


}
