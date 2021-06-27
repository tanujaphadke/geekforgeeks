package trees.lecture12;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.*;

public class AllPathsProblems {
    /*
                1
            /     \
          3        -1
        /   \     /   \
       2     1   4     5
            /   / \     \
           1   1   2     6
           The method should print or return paths such that they are always "downwards" and can begin and end at
           any node .  ( -1, 4) is a valid path but (4, -1, 5) is not a valid path

     */
    public static Map<Integer, List<List<Integer>>> getAllPathsFromEveryNodeToAnyNode(TreeNode root, int level) {
        if (root == null) {
            Map<Integer, List<List<Integer>>> answer = new HashMap<>();
            return answer;
        }
        Map<Integer, List<List<Integer>>> lAnswer = getAllPathsFromEveryNodeToAnyNode(root.left, level + 1);
        //add left answer to answer
        //add root to the answer.
        //concanate root with leftAnswer(which is for level + 1 ) and then add this to the answer
        List<List<Integer>> allPaths = getListoflist(root);
        List<List<Integer>> pathAtThislevelWithRootAdded = addRootToPathFromNextLevel(root, lAnswer, level);
        allPaths.addAll(0,pathAtThislevelWithRootAdded);
        List<List<Integer>> lExistingPaths = lAnswer.get(level);
        if (lExistingPaths != null) allPaths.addAll(0,lExistingPaths);
        lAnswer.put(level, allPaths);


        Map<Integer, List<List<Integer>>> rAnswer = getAllPathsFromEveryNodeToAnyNode(root.right, level + 1);
        //add right answer with root to answer
        //merge the two maps and return
        List<List<Integer>> allPathsR = new ArrayList<>();//getLol(root);
        List<List<Integer>> pathAtThislevelWithRootAddedR = addRootToPathFromNextLevel(root, rAnswer, level);
        List<List<Integer>> eExistingPaths = rAnswer.get(level);
        allPathsR.addAll(0,pathAtThislevelWithRootAddedR);
        if (eExistingPaths != null) allPathsR.addAll(0,eExistingPaths);
        rAnswer.put(level, allPathsR);

        Map<Integer, List<List<Integer>>> answer = mergeResultMaps(rAnswer, lAnswer);
        return answer;
    }

    /*
        Find all  paths in a Binary tree that end in a leaf node
        Note that the paths should not terminate at intermediate node.They can start from intermediate nodes.
        */
    public static Map<Integer, List<List<Integer>>> getAllPathsFromEveryNodeToLeafNode(TreeNode root, int level) {
        if (root == null) {
            Map<Integer, List<List<Integer>>> answer = new HashMap<>();
            return answer;
        }
        Map<Integer, List<List<Integer>>> lAnswer = getAllPathsFromEveryNodeToLeafNode(root.left, level + 1);
        Map<Integer, List<List<Integer>>> rAnswer = getAllPathsFromEveryNodeToLeafNode(root.right, level + 1);
        if (lAnswer.isEmpty() && rAnswer.isEmpty()) {
        //Means this is leaf node. From leaf to leaf. It is a valid path so we must add it.
            Map<Integer, List<List<Integer>>> pathMapAtLeafLevel = getPathMapAtLeafLevel(root, level);
            return pathMapAtLeafLevel;
        }
        if (lAnswer.isEmpty() || rAnswer.isEmpty()) {
            Map<Integer, List<List<Integer>>> ans = lAnswer.isEmpty() ? deepCopyMap(rAnswer) : deepCopyMap(lAnswer);
            // Means that either the rPath or the lPaths is non-empty so ans will never be empty
            List<List<Integer>> pathAtThislevelWithRootAdded = addRootToPathFromNextLevel(root, ans, level);
            List<List<Integer>> existing = ans.get(level);
            if (existing != null) {
                existing.addAll(0,pathAtThislevelWithRootAdded);
            } else {
                ans.put(level, pathAtThislevelWithRootAdded);
            }
            return ans;
        } else { //Both the answers are non-empty.
            List<List<Integer>> leftPathsWithRootAdded = addRootToPathFromNextLevel(root, lAnswer, level);
            List<List<Integer>> rightPathsWithRootAdded = addRootToPathFromNextLevel(root, rAnswer, level);

            leftPathsWithRootAdded.addAll(rightPathsWithRootAdded); //all paths

            Map<Integer, List<List<Integer>>> answer = mergeResultMaps(lAnswer, rAnswer);

            List<List<Integer>> existing = answer.get(level);
            if (existing != null) {
                existing.addAll(leftPathsWithRootAdded);
            } else {
                answer.put(level, leftPathsWithRootAdded);
            }
            return answer;
        }

    }


    /*
    Find all  paths in a Binary tree that end in a leaf node
    Note that the paths should not terminate at intermediate node.They can start from intermediate nodes.
    This is a little unclean implementation so deprecating this method
    */
    @Deprecated
    public static Map<Integer, List<List<Integer>>> getAllPathsFromEveryNodeToLeafNodeOld(TreeNode root, int level) {
        if (root == null) {
            Map<Integer, List<List<Integer>>> answer = new HashMap<>();
            return answer;
        }
        Map<Integer, List<List<Integer>>> lAnswer = getAllPathsFromEveryNodeToLeafNodeOld(root.left, level + 1);
        Map<Integer, List<List<Integer>>> rAnswer = getAllPathsFromEveryNodeToLeafNodeOld(root.right, level + 1);

        if (lAnswer.isEmpty() || rAnswer.isEmpty()) {
            Map<Integer, List<List<Integer>>> ans = lAnswer.isEmpty() ? deepCopyMap(rAnswer) : deepCopyMap(lAnswer);
            if (ans.isEmpty()) {            //If both the lists are empty then answer can be empty as well
                Map<Integer, List<List<Integer>>> pathMapAtLeafLevel = getPathMapAtLeafLevel(root, level);
                return pathMapAtLeafLevel;
            }
            // Means that either the rPath or the lPaths is non-empty
            List<List<Integer>> pathAtThislevelWithRootAdded = addRootToPathFromNextLevel(root, ans, level);
            List<List<Integer>> existing = ans.get(level);
            if (existing != null) {
                existing.addAll(0,pathAtThislevelWithRootAdded);
            } else {
                ans.put(level, pathAtThislevelWithRootAdded);
            }
            return ans;
        } else { //lAnswer and rAnswer both are nonEmpty
//            List<List<Integer>> leftPathsWithRootAdded = addRootToPathAtThisLevel(root, lAnswer, level);
//            List<List<Integer>> rightPathsWithRootAdded = addRootToPathAtThisLevel(root, rAnswer, level);

            List<List<Integer>> leftPathsWithRootAdded = addRootToPathFromNextLevel(root, lAnswer, level);
            List<List<Integer>> rightPathsWithRootAdded = addRootToPathFromNextLevel(root, rAnswer, level);

            leftPathsWithRootAdded.addAll(rightPathsWithRootAdded); //all paths

            Map<Integer, List<List<Integer>>> answer = mergeResultMaps(lAnswer, rAnswer);

            List<List<Integer>> existing = answer.get(level);
            if (existing != null) {
                existing.addAll(leftPathsWithRootAdded);
            } else {
                answer.put(level, leftPathsWithRootAdded);
            }
            return answer;
        }

    }

    private static Map<Integer, List<List<Integer>>>
    mergeResultMaps(Map<Integer, List<List<Integer>>> map1, Map<Integer, List<List<Integer>>> map2) {
        Map<Integer, List<List<Integer>>> answer = new HashMap<>();
        //Lets say these are the two keys in map1 and map2   1,2,3,4,5,6,7      2,3,4,10,11
        //After the iteration of the below for loop we will have
        //1,2,3,4,5,6,7 in the resulting map.But, not 10,11 in the resulting map.
        for (Map.Entry<Integer, List<List<Integer>>> entry : map1.entrySet()) {
            Integer key = entry.getKey();
            List<List<Integer>> listList1 = entry.getValue();
            List<List<Integer>> foundinMap2 = map2.get(key);
            if (foundinMap2 != null) {
                //Append to Map2
                listList1.addAll(0,foundinMap2);
            }
            answer.put(key, listList1);
        }
        //Loop to copy remaining elements of map2 10,11
        for (Map.Entry<Integer, List<List<Integer>>> entry : map2.entrySet()) {
            Integer key = entry.getKey();
            List<List<Integer>> listList2 = entry.getValue();
            List<List<Integer>> foundinMap1 = answer.get(key);
            if (foundinMap1 == null) { //Since we have already added common keys from map2 also in earler loop.
                // We will skip those and only add non-intersecting ones.
                answer.put(key, listList2);
            }
        }
        return answer;
    }
    /**
     * @param root
     * @return A list of List , given a tree node.
     */
    private static List<List<Integer>> getListoflist(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> path = new ArrayList(); path.add(0,root.val);
        allPaths.add(0,path);
        return allPaths;
    }


    private static List<List<Integer>> addRootToPathFromNextLevel(TreeNode root, Map<Integer, List<List<Integer>>> ans,
                                                                  int level) {
        List<List<Integer>> pathsAtlevel = ans.get(level + 1);
        if (pathsAtlevel != null) {
            List<List<Integer>> copyPathsAtlevel = deepCopyListOfList(pathsAtlevel);
            for (List<Integer> eachPath : copyPathsAtlevel) {
                eachPath.add(0,root.val);
            }
            return copyPathsAtlevel;
        }
        return new ArrayList<>();
    }

    private static Map<Integer, List<List<Integer>>> getPathMapAtLeafLevel(TreeNode root, int level) {
        Map<Integer, List<List<Integer>>> pathsAtLevel = new HashMap<>();
        List<Integer> pathAtLeaf = new ArrayList<>();
        pathAtLeaf.add(0,root.val);
        List<List<Integer>> paths = new ArrayList<>();
        paths.add(0,pathAtLeaf);
        pathsAtLevel.put(level, paths);
        return pathsAtLevel;
    }


    public static Map<Integer, List<List<Integer>>> deepCopyMap(Map<Integer, List<List<Integer>>> someMap) {
        Map<Integer, List<List<Integer>>> answer = new HashMap<>();

        for (Map.Entry<Integer, List<List<Integer>>> eachEntry : someMap.entrySet()) {
            List<List<Integer>> eachListOfList = eachEntry.getValue();
            List<List<Integer>> copyListOfList = new ArrayList<>();
            for (List<Integer> echList : eachListOfList) {
                List<Integer> copyList = new ArrayList<>(echList);
                copyListOfList.add(copyList);
            }
            answer.put(eachEntry.getKey(), copyListOfList);
        }
        return answer;
    }

    public static List<List<Integer>> deepCopyListOfList(List<List<Integer>> someListOflist) {
        List<List<Integer>> eachListOfList = someListOflist;
        List<List<Integer>> copyListOfList = new ArrayList<>();
        for (List<Integer> echList : eachListOfList) {
            List<Integer> copyList = new ArrayList<>(echList);
            copyListOfList.add(0,copyList);
        }
        return copyListOfList;
    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForKPathSum();
        //Map<Integer, List<List<Integer>>> answer = getAllPathsFromEveryNodeToLeafNode(root, 0);

        Map<Integer, List<List<Integer>>> answer = getAllPathsFromEveryNodeToAnyNode(root, 0);
        for (Map.Entry<Integer, List<List<Integer>>> eachEntry : answer.entrySet()) {
            List<List<Integer>> echListOfList = eachEntry.getValue();
            System.out.println(" Level : " + eachEntry.getKey());
            for (List<Integer> eachList : echListOfList) {
                System.out.print(" \t\t");
                for (Integer ech : eachList) {
                    System.out.print(" " + ech.toString());
                }
                System.out.println("");
            }
        }


        // getAllPathsToAnyDownwardNode(root, new ArrayList<>());

    }

    /**
     * Find all K-sum paths in a B-tree
     * http://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
     */

    public void kSumPaths(TreeNode root) {

    }
}