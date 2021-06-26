package trees.lecture12;

import trees.lecture8.PopulateTree;
import trees.lecture8.TreeNode;

import java.util.*;

class Path {
    List<Integer> nodesList = new ArrayList<>();

    public void add(Integer val) {
        nodesList.add(val);// if val == null?
    }
}

public class AllPathsProblems {
    /*
                1
            /     \
          3        -1
        /   \     /   \
       2     1   4     5
            /   / \     \
           1   1   2     6
This works and gives correct Answer
     */
    public static Map<Integer, List<List<Integer>>> getAllPathsFromEveryNodeToAnyNode(TreeNode root, int level) {
        if (root == null) {
            Map<Integer, List<List<Integer>>> answer = new HashMap<>();
            return answer;
        }
        Map<Integer, List<List<Integer>>> lAnswer = getAllPathsFromEveryNodeToAnyNode(root.left, level + 1);
        //add left answer to answer
        //add root to the answer.
        //add leftAnswer for level + 1 with root to the answer
        List<List<Integer>> allPaths = getLol(root);
        List<List<Integer>> pathAtThislevelWithRootAdded = addRootToPathFromNextLevel(root, level, lAnswer);
        List<List<Integer>> p = lAnswer.get(level);
        allPaths.addAll(pathAtThislevelWithRootAdded);
        if (p != null ) allPaths.addAll(p);
        lAnswer.put(level, allPaths);


        Map<Integer, List<List<Integer>>> rAnswer = getAllPathsFromEveryNodeToAnyNode(root.right, level + 1);
        //add right answer with root to answer
        //merge the two maps and return
        List<List<Integer>> allPathsR = new ArrayList<>();//getLol(root);
        List<List<Integer>> pathAtThislevelWithRootAddedR = addRootToPathFromNextLevel(root, level, rAnswer);
        List<List<Integer>> pr = rAnswer.get(level);
        allPathsR.addAll(pathAtThislevelWithRootAddedR);
        if (pr != null) allPathsR.addAll(pr);
        rAnswer.put(level, allPathsR);

        Map<Integer, List<List<Integer>>> answer = mergeResultMaps(rAnswer, lAnswer);
        return  answer;
    }

    private static List<List<Integer>>  getLol(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> paths = new ArrayList<>();
        paths.add(root.val);
        allPaths.add(paths);return allPaths;
    }

    @Deprecated
    //This does NOT WORK
    public static void getAllPathsToAnyDownwardNode(TreeNode root, List<List<Integer>> previousResult) {
        if (root == null) {
            return;
        }
        List<Integer> path = getPathWithRoot(root);
        List<List<Integer>> currentResult = new ArrayList<>(); //copyList(previousResult);
        currentResult.add(path);
        List<List<Integer>> prevCopy = copyListOfList(previousResult);
        for (List<Integer> each : prevCopy) {
            each.addAll(path);
        }
        currentResult.addAll(prevCopy);
        System.out.print(" Current " + Arrays.toString(currentResult.toArray()) + "\t\t");
        //For each path in result add current node initially just 1 will bethere  1, 3, (!,3)
        getAllPathsToAnyDownwardNode(root.left, currentResult);
        getAllPathsToAnyDownwardNode(root.right, currentResult);
    }

    private static List<Integer> getPathWithRoot(TreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        return path;
    }

    /*
    Find all  paths in a Binary tree that end in a leaf node
    Note that the paths should not terminate at intermediate node.
    */
    public static Map<Integer, List<List<Integer>>> getAllPathsFromEveryNodeToLeafNode(TreeNode root, int level) {
        if (root == null) {
            Map<Integer, List<List<Integer>>> answer = new HashMap<>();
            return answer;
        }
        Map<Integer, List<List<Integer>>> lAnswer = getAllPathsFromEveryNodeToLeafNode(root.left, level + 1);
        Map<Integer, List<List<Integer>>> rAnswer = getAllPathsFromEveryNodeToLeafNode(root.right, level + 1);

        if (lAnswer.isEmpty() || rAnswer.isEmpty()) {
            Map<Integer, List<List<Integer>>> ans = lAnswer.isEmpty() ? copy(rAnswer) : copy(lAnswer);
            if (ans.isEmpty()) {            //If both the lists are empty then answer can be empty as well
                Map<Integer, List<List<Integer>>> pathMapAtLeafLevel = getPathMapAtLeafLevel(root, level);
                return pathMapAtLeafLevel;
            }
            // Means that either the rPath or the lPaths is non-empty
            List<List<Integer>> pathAtThislevelWithRootAdded = addRootToPathFromNextLevel(root, level, ans);
            List<List<Integer>> existing = ans.get(level);
            if (existing != null) {
                existing.addAll(pathAtThislevelWithRootAdded);
            } else {
                ans.put(level, pathAtThislevelWithRootAdded);
            }
            return ans;
        } else { //lAnswer and rAnswer both are nonEmpty
            List<List<Integer>> leftPathsWithRootAdded = addRootToPathAtThisLevel(root, lAnswer, level);
            List<List<Integer>> rightPathsWithRootAdded = addRootToPathAtThisLevel(root, rAnswer, level);

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
        for (Map.Entry<Integer, List<List<Integer>>> entry : map1.entrySet()) {
            Integer key = entry.getKey();
            List<List<Integer>> listList1 = entry.getValue();
            List<List<Integer>> foundinMap2 = map2.get(key);
            if (foundinMap2 != null) {
                //Append to Map2
                listList1.addAll(foundinMap2);
            }
            answer.put(key, listList1);
        }

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

    private static List<List<Integer>> addRootToPathAtThisLevel(TreeNode root, Map<Integer, List<List<Integer>>> someMap,
                                                                int level) {
        Map<Integer, List<List<Integer>>> answerCopy = copy(someMap);
        List<List<Integer>> paths = answerCopy.get(level + 1);
        for (List<Integer> each : paths) {
            each.add(root.val);
        }
        return paths;
    }

    private static Map<Integer, List<List<Integer>>> getPathMapAtLeafLevel(TreeNode root, int level) {
        Map<Integer, List<List<Integer>>> pathsAtLevel = new HashMap<>();
        List<Integer> trail = new ArrayList<>();
        trail.add(root.val);
        List<List<Integer>> paths = new ArrayList<>();
        paths.add(trail);
        pathsAtLevel.put(level, paths);
        return pathsAtLevel;
    }

    private static List<List<Integer>> addRootToPathFromNextLevel(TreeNode root, int level, Map<Integer, List<List<Integer>>> ans) {
        List<List<Integer>> pathsAtlevel = ans.get(level + 1) ;
        if (pathsAtlevel != null ) {
            List<List<Integer>> copyPathsAtlevel = copyListOfList(pathsAtlevel);
            for (List<Integer> eachPath : copyPathsAtlevel) {
                eachPath.add(root.val);
            }
            return copyPathsAtlevel;
        }return  new ArrayList<>();
    }

    public static Map<Integer, List<List<Integer>>> copy(Map<Integer, List<List<Integer>>> someMap) {
        Map<Integer, List<List<Integer>>> answer = new HashMap<>();

        for (Map.Entry<Integer, List<List<Integer>>> eachEntry : someMap.entrySet()) {
            List<List<Integer>> eachListOfList = eachEntry.getValue();
            List<List<Integer>> copyListOfList = new ArrayList<>();
            for (List<Integer> echList : eachListOfList) {
                List<Integer> copyList = new ArrayList<>();
                for (Integer eachNode : echList) {
                    copyList.add(eachNode);
                }
                copyListOfList.add(copyList);
            }
            answer.put(eachEntry.getKey(), copyListOfList);
        }
        return answer;
    }

    public static List<List<Integer>> copyList(List<List<Integer>> someList) {
        List<List<Integer>> answer = new ArrayList<>();
        List<List<Integer>> copyListOfList = new ArrayList<>();
        for (List<Integer> echList : someList) {
            List<Integer> copyList = new ArrayList<>();
            for (Integer eachNode : echList) {
                copyList.add(eachNode);
            }
            copyListOfList.add(copyList);
        }
        return copyListOfList;
    }

    public static List<List<Integer>> copyListOfList(List<List<Integer>> someListOflist) {
        List<List<Integer>> eachListOfList = someListOflist;
        List<List<Integer>> copyListOfList = new ArrayList<>();
        for (List<Integer> echList : eachListOfList) {
            List<Integer> copyList = new ArrayList<>();
            for (Integer eachNode : echList) {
                copyList.add(eachNode);
            }
            copyListOfList.add(copyList);
        }
        return copyListOfList;
    }

    public static void main(String args[]) {
        TreeNode root = PopulateTree.populateTreeForKPathSum();
       // Map<Integer, List<List<Integer>>> answer = getAllPathsFromEveryNodeToLeafNode(root, 0);

        Map<Integer, List<List<Integer>>> answer = getAllPathsFromEveryNodeToAnyNode(root, 0);
        for (Map.Entry<Integer, List<List<Integer>>> eachEntry : answer.entrySet()) {
            List<List<Integer>> echListOfList = eachEntry.getValue();
            System.out.println(" Level : " + eachEntry.getKey());
            for (List<Integer> eachList : echListOfList) {
                System.out.print(" \t\t");
                for (Integer ech : eachList) {
                    System.out.print(" " + ech);
                }
                System.out.println("");
            }
        }


       // getAllPathsToAnyDownwardNode(root, new ArrayList<>());

    }


    /**
     * http://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
     * This does not WOKR
     */
    public Map<Integer, List<List<TreeNode>>> findPaths(TreeNode root, int level) {
        Map<Integer, List<List<TreeNode>>> hashMap = new HashMap<Integer, List<List<TreeNode>>>();

        if (root == null) {
            List<List<TreeNode>> list = new ArrayList<List<TreeNode>>();
            hashMap.put(level, list);
            return hashMap;
        }
        Map<Integer, List<List<TreeNode>>> lHashMap = findPaths(root.left, level + 1);

        List<List<TreeNode>> lTempPath = hashMap.get(level + 1);
        List<TreeNode> l = new ArrayList<TreeNode>();
        l.add(root);
        lTempPath.add(l);

        List<List<TreeNode>> rTempPath = hashMap.get(level + 1);
        List<TreeNode> r = new ArrayList<TreeNode>();
        r.add(root);
        rTempPath.add(r);

        return null;
    }

    /**
     * Find all K-sum paths in a B-tree
     * http://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
     */

    public void kSumPaths(TreeNode root) {

    }

}

    /*
    This method returns all path in a Binary Tree
    This is incorrect method
     */
    /*
    public static List<List<Integer>> getAllPaths(TreeNode root) {

        if (root == null) {
            List<List<Integer>> answer = new ArrayList();
            return answer;
        }
        List<List<Integer>> lAnswer = getAllPaths(root.left);
        List<List<Integer>> rAnswer = getAllPaths(root.right);

        if (lAnswer.isEmpty() || rAnswer.isEmpty()) {
            List<List<Integer>> ans = lAnswer.isEmpty() ? copy(rAnswer) : copy(lAnswer);
            //If both the lists are empty then answer can be empty as well
            if (ans.isEmpty()) {
                List<Integer> tailForLeaf = new ArrayList<>();
                tailForLeaf.add(root.val);
                ans.add(tailForLeaf);
                return ans;
            }
            List<List<Integer>> ansCopy = copy(ans);
            for (List<Integer> ech : ansCopy) {
                ech.add(root.val);
            }
            //We want to keep the original path found so far and also create new path
            //by adding root value to it.
            ans.addAll(ansCopy);
            return ans;
        } else { //lAnswer and rAnswer both are nonEmpty
            List<List<Integer>> lAnswerCopy = copy(lAnswer);
            for (List<Integer> eachLeft : lAnswerCopy) {
                eachLeft.add(root.val);
            }
            List<List<Integer>> rAnswerCopy = copy(lAnswer);
            for (List<Integer> eachRight : rAnswerCopy) {
                eachRight.add(root.val);
            }
            List<List<Integer>> answer = new ArrayList<>();
            answer.addAll(lAnswer);
            answer.addAll(lAnswerCopy);
            answer.addAll(rAnswer);
            answer.addAll(rAnswerCopy);
            return answer;
        }
    }*/
