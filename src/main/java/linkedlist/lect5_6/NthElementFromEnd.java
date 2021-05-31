package linkedlist.lect5_6;

import static linkedlist.lect5_6.PopulateLinkedList.populateLL;

public class NthElementFromEnd {

    public static int i = 0;

    /**
     * This version uses a static class variable, like a global variable which it updates
     * in  between recursive calls. Using global scoped variables in recursion is a bad idea
     *
     * @param node
     * @param position
     * @return
     *     //1  --> 15 --> 12 -->21 --> 10 --> 66 --> 77);
     */
    public static LLNode findnthToLastUsingStatic(LLNode node, int position) {
        if (node == null) return null;
        else {
            LLNode returnedNode = findnthToLastUsingStatic(node.next, position);
            i = i + 1;
            if (i == position) {
                return node;// From here on we only return this value when we are going back in the recursion
            }
            return returnedNode;
        }
    }

    /**
     * The variable idxfromBegining will keep track of position from the begining
     * When the end of list is reached it is at linkedList.length position
     * THIS MEthOD DOES NOT WORK. For demonstration purpose only
     *
     * @param node
     * @param position
     * @param idxfromBegining
     * @return
     */
    @Deprecated
    public static LLNode findnthToLastHelper(LLNode node, int position, int idxfromBegining) {
        int positionFromBegining = -1;
        if (node == null) {
            positionFromBegining = (idxfromBegining - position);
            return null;
        } else {
            int indexOfNextNode = idxfromBegining++;
            findnthToLastHelper(node.next, position, indexOfNextNode);
            //When it reached end of recursion the index will be
            if (idxfromBegining == position) return node;//
            return null;
        }
        //This method does not work it is for demonstration purposes only.
    }

    static class NodePosition {
        LLNode node;
        int idxfromBegining;

        public NodePosition(LLNode node, int idxfromBegining) {
            this.node = node;
            this.idxfromBegining = idxfromBegining;
        }
    }
    //1  --> 15 --> 12 -->21 --> 10 --> 66 --> 77);
    public static NodePosition findnthFromEndRecursionHelper(LLNode node, int position, int indexOfNextNodeFromBegining) {
        if (node == null) {
            //Since at this point idxfromBegining is the length of the linkedlist
            NodePosition nodePosition = new NodePosition(null, indexOfNextNodeFromBegining - position);
            return nodePosition;
        } else {
            ++indexOfNextNodeFromBegining;
            NodePosition nodePosition = findnthFromEndRecursionHelper(node.next, position,indexOfNextNodeFromBegining );
            //When it reached end of recursion the index will be
            int positionOfCurrentNodeFromBegining = indexOfNextNodeFromBegining - 1;
            if ( positionOfCurrentNodeFromBegining == nodePosition.idxfromBegining ) {
                nodePosition.node = node ;
                return nodePosition;
            }
            return nodePosition;
        }
    }


    public static LLNode findnthToLast(LLNode node, int position) {
        //return  findnthToLastRecursionHelper(node, position, 0);//This does not work
        return findnthFromEndRecursionHelper(node, position, 0).node;

    }

    static LLNode HEAD = populateLL(false);

    public static void main(String args[]) {
        LLNode nthNode = findnthToLastUsingStatic(HEAD, 4);
        LLNode nthNode2 = findnthToLast(HEAD, 4);
        System.out.println("Node at positin 4 from the end using recursion = " + nthNode2);
        System.out.println("Node at positin 4 from the end using static = " + nthNode);

    }
}
