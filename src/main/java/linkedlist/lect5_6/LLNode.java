package linkedlist.lect5_6;

public class LLNode {//Linked List Node
    public int val;
    public LLNode next;

    public LLNode(int i) {
        this.val = i;
    }

    public LLNode() {
    }

        @Override
    public String toString() {
        return "LLNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
