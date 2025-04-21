package z1lecture8;
//https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/
//https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
//https://www.youtube.com/watch?v=PoBGyrIWisE


 class Node {
    int value;
    int depth; // distance from root node
    int distanceFromTail; // distance from tail node
    Node next;
    //boolean visited; //add a visited flag
    public Node(int val) {
        super();
        this.value = val;
    }

    public Node(int val, Node next) {
        super();
        this.value = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node [val=" + value + ", depth=" + depth + ", distanceFromTail="
                + distanceFromTail + ", next=" + next + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + depth;
        result = prime * result + distanceFromTail;
        result = prime * result + ((next == null) ? 0 : next.hashCode());
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (depth != other.depth)
            return false;
        if (distanceFromTail != other.distanceFromTail)
            return false;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next))
            return false;
        if (value != other.value)
            return false;
        return true;
    }



}

/**
 Preorder 1 2 4 8 9 10 11 5 3 6 7
 Inorder  8 4 10 9 11 2 5 1 6 3 7
 */
public class ConstructABinaryTree {

    public int constructTree(int preOrderArr[] , int inOrderArr[]){

        Node root = new Node(preOrderArr[0]);
        int idx = findElemInArray(inOrderArr, root.value);
        //Means that all the elements to the left of idx are LEft subtree and all the elements to the right
        // are right subtree
        return 0;
    }
    public  static void main(String args[]){

    }
    public int findElemInArray(int[] array , int findThis){
        for ( int i= 0 ; i < array.length ; i ++){
            if (findThis == array[i]){
                return i;
            }
        }
        return 0 ;
    }
}
