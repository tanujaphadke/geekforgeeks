package z1lecture8;
//https://www.techiedelight.com/print-bottom-view-of-binary-tree/
//https://howtodoinjava.com/java-sorting-guide/

import java.util.*;

public class BinaryTreeTopView2 {

    //We will use a TreeMap since treeMap stores elements in sorted order by keys
    //https://howtodoinjava.com/java-sorting-guide/
    private static Map<Integer, Node4> distanceNode4Map = new TreeMap<>();

    public static void topView(List<Node4> parentNode4s, Map<Node4, Integer> Node4DistanceMap) {
        if (parentNode4s == null || parentNode4s.size() == 0) return;

        List<Node4> children = new ArrayList<>();

        Map<Node4, Integer> ndMap = new HashMap<>();

        for (Node4 parent : parentNode4s) {
            Integer distanceOfParentFromRoot = Node4DistanceMap.get(parent);

            if (parent.left != null) {
                children.add(parent.left);
                Integer leftDistanceFromRoot = distanceOfParentFromRoot - 1;
                //Skip if a Node4 already exists at that distance. Since we are looking from the bottom
                // we will not add this Node4 since a Node4 at that distance already exists.
                if (distanceNode4Map.get(leftDistanceFromRoot) == null) {
                    distanceNode4Map.put(leftDistanceFromRoot, parent.left);
                } else ;//skip
                ndMap.put(parent.left, leftDistanceFromRoot);
                //Note that if we were doing top view of hte tree then if a Node4 already exists at that distance
                //then we will not add that Node4. Since we are looking from the top.
            }
            if (parent.right != null) {
                children.add(parent.right);
                Integer righttDistanceFromRoot = distanceOfParentFromRoot + 1;
                if (distanceNode4Map.get(righttDistanceFromRoot) == null) {
                    distanceNode4Map.put(righttDistanceFromRoot, parent.right);
                } else ;//skip
                ndMap.put(parent.right, righttDistanceFromRoot);
            }
        }

        topView(children,ndMap);

    }

    public static void printTopView(Node4 root) {
        if (root == null) return;
        List<Node4> firstList = new ArrayList<>();
        firstList.add(root);
        distanceNode4Map.put(0, root);
        Map<Node4, Integer> Node4DistanceMap = new HashMap<>();

        Node4DistanceMap.put(root, 0);
        topView(firstList, Node4DistanceMap);
        // distanceNode4Map will have all the bottom view Node4.
        //This distanceNode4Map can be sorted in increasing order of distances to display the Node4s
        //Since this is a TreeMap  we need not sort it. It will be already sorted by distance which is key
        for (Node4 ech : distanceNode4Map.values())
            System.out.print(ech.value + " ");

    }

    public static void main(String argsp[]) {
        Node4 root = new Node4(1);
        root.left = new Node4(2);
        root.right = new Node4(3);
        root.left.right = new Node4(4);
        root.right.left = new Node4(5);
        root.right.right = new Node4(6);
        root.right.left.left = new Node4(7);
        root.right.left.right = new Node4(8);


        printTopView(root);
    }
}
