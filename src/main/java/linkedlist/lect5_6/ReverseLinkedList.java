package linkedlist.lect5_6;


public class ReverseLinkedList {
    public static LLNode HEAD = PopulateLinkedList.populateLL(false);

    /**
     * Uses node.next to check for nulls.
     *
     * @param node
     * @return
     */
    public static LLNode reverseL(LLNode node) {
        if (node == null || node.next == null) {
            return node;//Returns null or the last node which is to become the head
        }
        LLNode headNode = reverseL(node.next);
        if (node.next != null) {
            node.next.next = node;
            node.next = null;
        }
        return headNode;
    }


    public static LLNode reverseL2(LLNode node) {
        if (node == null) return node;//Returns node which can be null or the last node
        LLNode headNode = reverseL(node.next);
        if (headNode == null) {
            return node;
        }
        node.next.next = node; //Need not do node.next null check
        node.next = null;
        return headNode;
    }

    public static void main(String args[]) {
        System.out.println("OriginalList = " + HEAD);
        System.out.println(reverseL(HEAD));
        //Need to reset the list since the aboove call has changed it
        HEAD = PopulateLinkedList.populateLL(false);
        System.out.println(reverseL2(HEAD));


    }
}
