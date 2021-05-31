package linkedlist.lect5_6;

public class BasicLinkedListOperations {

    static LLNode HEAD = PopulateLinkedList.populateLL(false);

    public static void printLinkedList ( LLNode node){
        if ( node == null ) {
            return;
        };
        System.out.println(node.val);
        printLinkedList(node.next);
    }


    public static LLNode searchLinkedList ( LLNode node, Integer value){
        if ( node == null ) throw new RuntimeException("List is empty ");
        if(node.val == value) return node;
        else {
            return searchLinkedList(node.next, value);
        }
    }

    public static void deleteLinkedList1 ( LLNode node, Integer value){
        if ( node == null ) throw new RuntimeException("List is empty ");
        if(node.next.val == value) {
            node.next = node.next.next;
        }
        else {
            deleteLinkedList1(node.next, value);
        }
    }


    public static LLNode deleteLinkedList ( LLNode node, Integer value){
        if ( node == null ) throw new RuntimeException("List is empty ");
        if(node.val == value) {
            return node;
        }
        else {
            LLNode foudNode = deleteLinkedList(node.next, value);
            node.next = foudNode.next;
            foudNode.next = null; // not required but do this.
            return foudNode;//returns the deleted  node.
        }
    }


    public static void main(String args[]){
        // Make method calls to basic operations
        printLinkedList(HEAD);
        deleteLinkedList(HEAD,10);
        searchLinkedList(HEAD,21);

    }


}
