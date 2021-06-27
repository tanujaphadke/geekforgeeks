package trees.lecture11;

import trees.lecture8.Node4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SpiralOrderTraversal {

    // Function to print Spiral order traversal of given binary tree
    public static void spiralOrderTraversalCopied(Node4 root)
    {
        if (root == null) {
            return;
        }

        // create an empty double ended queue and enqueue root Node4
        Deque<Node4> deque = new ArrayDeque<>();	  // or use deque
        deque.addFirst(root);

        // flag used to differentiate between odd or even level
        boolean flag = false;

        // run till deque is not empty
        while (!deque.isEmpty()) {
            // calculate number of Node4s in current level
            int Node4Count = deque.size();

            // print left to right
            if (flag) {
                // process each Node4 of current level and enqueue their
                // non-empty left and right child to deque
                while (Node4Count > 0) {
                    // pop from front if flag is true
                    Node4 curr = deque.pollFirst();
                    System.out.print(curr.value + " ");

                    // push left child to end followed by right child if flag is true

                    if (curr.left != null) {
                        deque.addLast(curr.left);
                    }

                    if (curr.right != null) {
                        deque.addLast(curr.right);
                    }

                    Node4Count--;
                }
            }

            // print right to left
            else {
                // process each Node4 of current level and enqueue their
                // non-empty right and left child to queue
                while (Node4Count > 0) {
                    // Important - pop from back if flag is false
                    Node4 curr = deque.pollLast();
                    System.out.print(curr.value + " ");   // print front Node4

                    // Important - push right child to front followed by left
                    // child if flag is false

                    if (curr.right != null) {
                        deque.addFirst(curr.right);
                    }

                    if (curr.left != null) {
                        deque.addFirst(curr.left);
                    }

                    Node4Count--;
                }
            }

            // flip the flag for next level
            flag = !flag;
            System.out.println();
        }
    }


    //This one has duplicate code.Then I realized that the show  Node4 is always shown from the first Node4
    //as in the previous round it is insertedin the way it is su[[pse to be displayed.
    @Deprecated
    public static void spiralOLD(List<Node4> Node4List, boolean pickChildrenFromleft) {
        if (Node4List.size() == 0) return;

        List<Node4> children = new ArrayList<>();
        if (pickChildrenFromleft) {
            for (int i = 1; i <= Node4List.size(); i++) {
                Node4 parentNode4 = Node4List.get(Node4List.size() - i);
                Node4 showNode4 = Node4List.get(i - 1);
                System.out.print(showNode4.value + "   ");
                if (parentNode4.left != null) children.add(parentNode4.left);
                if (parentNode4.right != null) children.add(parentNode4.right);

            }
        } else {
            for (int i = 1; i <= Node4List.size(); i++) {
                Node4 parentNode4 = Node4List.get(Node4List.size() - i);
                Node4 showNode4 = Node4List.get(i - 1);
                System.out.print(showNode4.value + "   ");
                if (parentNode4.right != null) children.add(parentNode4.right);
                if (parentNode4.left != null) children.add(parentNode4.left);
            }
        }
        if (pickChildrenFromleft) {
            //This level was formed by picking children from left so for next recursion , we will pick children from right
            spiralOLD(children, false);
        } else { //Now pick children from left
            spiralOLD(children, true);

        }

    }


    public static void spiral(List<Node4> Node4List, boolean pickChildrenFromleft) {
        if (Node4List.size() == 0) return;

        List<Node4> children = new ArrayList<>();
        for (int i = 1; i <= Node4List.size(); i++) {
            Node4 parentNode4 = Node4List.get(Node4List.size() - i);
            Node4 showNode4 = Node4List.get(i - 1);
            System.out.print(showNode4.value + "   ");
            if (pickChildrenFromleft) {
                if (parentNode4.left != null) children.add(parentNode4.left);
                if (parentNode4.right != null) children.add(parentNode4.right);
            } else {
                if (parentNode4.right != null) children.add(parentNode4.right);
                if (parentNode4.left != null) children.add(parentNode4.left);
            }
        }
        if (pickChildrenFromleft) { //If in this iteration i have picked children from the left then for the next call I
            //have to pick children from theright so pass false.
            //This level was formed by picking children from left so for next recursion , we will pick children from right
            spiral(children, false);
        } else { //Now pick children from left
            spiral(children, true);

        }

    }

    public static void spiralOrderTraversal(Node4 root) {
        List Node4sAtLevel = new ArrayList<>();
        Node4sAtLevel.add(root);
        spiral(Node4sAtLevel, false);
    }

    public static void main(String args[]) {
        Node4 root = new Node4(15);
        root.left = new Node4(10);
        root.right = new Node4(20);
        root.left.left = new Node4(8);
        root.left.right = new Node4(12);
        root.right.left = new Node4(16);
        root.right.right = new Node4(25);

        spiralOrderTraversal(root);
    }
}
