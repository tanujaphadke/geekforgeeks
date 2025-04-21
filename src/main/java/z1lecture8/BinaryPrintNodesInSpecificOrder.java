package z1lecture8;

//https://www.techiedelight.com/print-Node4s-binary-tree-specific-order/

import java.util.ArrayList;
import java.util.List;


public class BinaryPrintNodesInSpecificOrder {

    public static void printAlternate(List<Node4> Node4List) {
        if (Node4List == null || Node4List.size() == 0) return;

        for ( int i = 0 ; i < Math.ceil(Node4List.size()/2) ;i ++){
            Node4 showLeftmost = Node4List.get(i);
            Node4 showRightmost = Node4List.get(Node4List.size() -i -1);
            System.out.print(showLeftmost.value + " "  + showRightmost.value + " ");
        }

        List<Node4> children = new ArrayList<>();
        for (Node4 each : Node4List) {
            if (each.left != null) children.add(each.left);
            if (each.right != null) children.add(each.right);
        }
        printAlternate(children);
    }

    public static void printAlternateLeftandRight(Node4 Node4) {

    }

    public static void main(String[] args) {
        Node4 root = new Node4(1);
        root.left = new Node4(2);
        root.right = new Node4(3);
        root.left.left = new Node4(4);
        root.left.right = new Node4(5);
        root.right.left = new Node4(6);
        root.right.right = new Node4(7);
        root.left.left.left = new Node4(8);
        root.left.left.right = new Node4(9);
        root.left.right.left = new Node4(10);
        root.left.right.right = new Node4(11);
        root.right.left.left = new Node4(12);
        root.right.left.right = new Node4(13);
        root.right.right.left = new Node4(14);
        root.right.right.right = new Node4(15);
        List lst= new ArrayList<>(); lst.add(root);
        printAlternate(lst);
    }
}
