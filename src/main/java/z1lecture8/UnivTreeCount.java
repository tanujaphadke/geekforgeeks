package z1lecture8;

class NodeUnival {
    int value;
    NodeUnival left;
    NodeUnival right;

    NodeUnival(int value) {
        this.value = value;
    }

    NodeUnival(int value, NodeUnival left, NodeUnival right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    NodeUnival(NodeUnival left, NodeUnival right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

class Unival {
    Integer value;
    int count;

    public Unival(Integer value, int count) {
        this.value = value;
        this.count = count;
    }
}

public class UnivTreeCount {

    Unival getSubtreeNodeVaue(){
        return null;
    }
    static Unival getUnival(NodeUnival node) {
        if (node == null) return null;

        int currentNodeValue = node.value;
        if (node.left == null && node.right == null) return new Unival(currentNodeValue, 1);

        Unival leftUnival = node.left != null ? getUnival(node.left) : null;
        Unival rightUnival = node.right != null ? getUnival(node.right) : null;
        if (leftUnival == null && rightUnival != null) {
            if (rightUnival.value != null && rightUnival.value == currentNodeValue) {
                int count = rightUnival.count + 1;
                return new Unival(currentNodeValue, count);
            } else { //  means that (rightUnival.value == null || (rightUnival.value != null && rightUnival.value != currentNodeValue )){
                return new Unival(null, rightUnival.count);
            }
        } else if (rightUnival == null && leftUnival != null) {

        } else if (leftUnival != null && rightUnival != null) {

        } else { //left and right both are null
            Unival unival = new Unival(null, 1);
        }

        if ((leftUnival == null && (rightUnival.value != null && rightUnival.value == currentNodeValue) ||
                (rightUnival == null && (leftUnival.value != null && leftUnival.value == currentNodeValue)) ||
                (leftUnival.value == rightUnival.value && (rightUnival.value == currentNodeValue)))) {
            int count = leftUnival.count + rightUnival.count + 1;
            Unival unival = new Unival(currentNodeValue, count);
        } else if (leftUnival.value == rightUnival.value) {
            int count = leftUnival.count + rightUnival.count;
            Unival unival = new Unival(null, count);//since now the unival breaks , as root node does not have the same value
        } else {

        }

        if ((rightUnival == null) && (currentNodeValue == leftUnival.value)) {
            int count = leftUnival.count + 1;
            Unival currentUnival = new Unival(currentNodeValue, count);
            return currentUnival;
        }
        if ((leftUnival == null) && (currentNodeValue == rightUnival.value)) {
            int count = rightUnival.count + 1;
            Unival currentUnival = new Unival(currentNodeValue, count);
            return currentUnival;
        } else if (leftUnival == null && (rightUnival != null) && (currentNodeValue != rightUnival.value)) {
            int count = rightUnival.count != 0 ? rightUnival.count : 0;
            Unival currentUnival = new Unival(-1, count);
            return currentUnival;
        } else if (rightUnival == null && (leftUnival != null) && (currentNodeValue != leftUnival.value)) {
            int count = leftUnival.count != 0 ? leftUnival.count : 0;
            Unival currentUnival = new Unival(-1, count);
            return currentUnival;
        }
        if ((rightUnival != null) && (currentNodeValue == rightUnival.value)
                && (leftUnival != null) && (currentNodeValue == leftUnival.value)
        ) {
            int count = leftUnival.count + 1 + rightUnival.count;
            Unival currentUnival = new Unival(currentNodeValue, count);
            return currentUnival;
        } else { // ((rightUnival != null) && (currentNodeValue != rightUnival.value)
            //  && (leftUnival != null) && (currentNodeValue != leftUnival.value)){

            int lcount = leftUnival.count != 0 ? leftUnival.count : 0;
            int rcount = rightUnival.count != 0 ? rightUnival.count : 0;
            int count = lcount + rcount;
            Unival currentUnival = new Unival(-1, count);
            return currentUnival;

        }

    }


    static Unival getUnivalValue(NodeUnival node) {
        if (node == null) return null;

        int currentNodeValue = node.value;
        Unival rightUnival = node.right != null ? getUnivalValue(node.right) : null;
        Unival leftUnival = node.left != null ? getUnivalValue(node.left) : null;

        if (node.left == null && node.right == null) return new Unival(currentNodeValue, 1);
        if ((rightUnival == null) && (currentNodeValue == leftUnival.value)) {
            int count = leftUnival.count + 1;
            Unival currentUnival = new Unival(currentNodeValue, count);
            return currentUnival;
        }
        if ((leftUnival == null) && (currentNodeValue == rightUnival.value)) {
            int count = rightUnival.count + 1;
            Unival currentUnival = new Unival(currentNodeValue, count);
            return currentUnival;
        } else if (leftUnival == null && (rightUnival != null) && (currentNodeValue != rightUnival.value)) {
            int count = rightUnival.count != 0 ? rightUnival.count : 0;
            Unival currentUnival = new Unival(-1, count);
            return currentUnival;
        } else if (rightUnival == null && (leftUnival != null) && (currentNodeValue != leftUnival.value)) {
            int count = leftUnival.count != 0 ? leftUnival.count : 0;
            Unival currentUnival = new Unival(-1, count);
            return currentUnival;
        }
        if ((rightUnival != null) && (currentNodeValue == rightUnival.value)
                && (leftUnival != null) && (currentNodeValue == leftUnival.value)
        ) {
            int count = leftUnival.count + 1 + rightUnival.count;
            Unival currentUnival = new Unival(currentNodeValue, count);
            return currentUnival;
        } else { // ((rightUnival != null) && (currentNodeValue != rightUnival.value)
            //  && (leftUnival != null) && (currentNodeValue != leftUnival.value)){

            int lcount = leftUnival.count != 0 ? leftUnival.count : 0;
            int rcount = rightUnival.count != 0 ? rightUnival.count : 0;
            int count = lcount + rcount;
            Unival currentUnival = new Unival(-1, count);
            return currentUnival;

        }

    }

    static int getUnivCount(NodeUnival node) {

        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        if ((node.right != null && node.left != null)
                && (node.value == node.right.value && node.value == node.left.value)) {
            return 1;
        } else if (node.right == null && (node.value == node.left.value)) {
            return 1;
        } else { // (node.left == null && (node.value == node.right.value)) {
            return 1;
        }
    }


    public static void main(String args[]) {

        Integer x = null;
        System.out.println("xx ? " + (x == 9));
        NodeUnival rootNode = createTree();
        Unival unival = getUnivalValue(rootNode);
    }

    static NodeUnival createTree() {
        NodeUnival leafa = new NodeUnival(1);
        NodeUnival leafb = new NodeUnival(1);

        NodeUnival nodeOne1 = new NodeUnival(1, leafa, leafb);

        NodeUnival leafd = new NodeUnival(0);
        NodeUnival nodeZeroRight = new NodeUnival(0, nodeOne1, leafd);

        NodeUnival leafc = new NodeUnival(1);
        NodeUnival nodeZeroRoot = new NodeUnival(0, nodeZeroRight, leafc);


        return nodeZeroRoot;

    }
}
