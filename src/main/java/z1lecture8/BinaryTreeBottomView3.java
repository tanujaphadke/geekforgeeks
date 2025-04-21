package z1lecture8;
//https://www.techiedelight.com/print-bottom-view-of-binary-tree/
//https://howtodoinjava.com/java-sorting-guide/

//This removes the need to pass in two maps. It passed in only ine map

import java.util.*;

//This method is better since we are not using any globals across recurssion.
public class BinaryTreeBottomView3 {

    //We will use a TreeMap since treeMap stores elements in sorted order by keys
    //https://howtodoinjava.com/java-sorting-guide/

    public static Map<Integer, Node4> bottomView3(List<Node4> parentNode4s,
                                                  Map<Node4, Integer> parentsDistanceFromRootMap) {
        if (parentNode4s == null || parentNode4s.size() == 0) return new TreeMap();

        List<Node4> children = new ArrayList<>();
        Map<Node4, Integer> childrensDistanceFromRootMap = new HashMap<>();

        for (Node4 parent : parentNode4s) {
            Integer distanceOfParentFromRoot = parentsDistanceFromRootMap.get(parent);

            if (parent.left != null) {
                children.add(parent.left);
                Integer leftDistanceFromRoot = distanceOfParentFromRoot - 1;
                //Replace if a Node4 already exists at that distance. Since we are looking from the bottom
                //we will add this Node4 by replacing via a put .
                // The previous Node4 will be overridden by this one.
                childrensDistanceFromRootMap.put(parent.left, leftDistanceFromRoot);
                //Note that if we were doing top view of hte tree then if a Node4 already exists at that distance
                //then we will not add that Node4. Since we are looking from the top.
            }
            if (parent.right != null) {
                children.add(parent.right);
                Integer righttDistanceFromRoot = distanceOfParentFromRoot + 1;
                childrensDistanceFromRootMap.put(parent.right, righttDistanceFromRoot);
            }
        }
        Map<Integer, Node4> distanceNode4 = bottomView3(children, childrensDistanceFromRootMap);

        for (Map.Entry<Node4, Integer> each : childrensDistanceFromRootMap.entrySet()) {
            //Means that if nothing exists at a distance of each.getKey() in the distance Node4 map
            //Then add it to the distance Node4 map. Else skip.
            if (distanceNode4.get(each.getValue()) == null) {
                distanceNode4.put(each.getValue(), each.getKey());
            }
        }
        return distanceNode4;

    }

    public static void printBottom3(Node4 root) {
        if (root == null) return;
        List<Node4> firstList = new ArrayList<>();
        firstList.add(root);
        Map<Node4, Integer> rootNode4DistanceMap = new HashMap<>();
        rootNode4DistanceMap.put(root, 0);

        Map<Integer, Node4> distanceNode4Map = new TreeMap<>();
        distanceNode4Map = bottomView3(firstList, rootNode4DistanceMap);
        // distanceNode4Map will have all the bottom view Node4.
        //This distanceNode4Map can be sorted in increasing order of distances to display the Node4s
        //Since this is a TreeMap  we need not sort it. It will be already sorted by distance which is key
        System.out.print(distanceNode4Map.values());

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


        printBottom3(root);
    }
}
