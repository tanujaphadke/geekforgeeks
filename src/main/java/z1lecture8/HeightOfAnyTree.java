package z1lecture8;
class NodeWithHeight {
    int height;

    NodeWithHeight[] childern;
}

//http://zparacha.com/calculate-height-of-any-tree-in-java-non-binary-tree
public class HeightOfAnyTree {

    public static int getHeight(NodeWithHeight node){

        if ( node == null || node.childern.length == 0 ) return 0; //node has no children

        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0 ; i < node.childern.length ; i ++ ){
            maxHeight = 1 + Math.max(maxHeight, getHeight(node.childern[i]));
        }
        return  maxHeight;
    }


    public static void main(String args[]){


    }
}
