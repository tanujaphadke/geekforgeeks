package tries.lecture17;


public class TrieNode {

    private char c;
    public TrieNode[] child = new TrieNode[26];
    public boolean isWord; // false by default.

    public TrieNode(char c) {
        // All children are null;
        this.c = c;
    }
    public void openGate(char c) {
        // Find indx for the specified character.
        // For character 'a' index = 0; for character 'b' index = 1.. and do on
        int indx = (int) c - (int) 'a';
        child[indx] = new TrieNode(c);
    }

    // Is there a path or is the gate at index corrosponding to c open ?
    public boolean isGateOpen(char c) {
        int indx = (int) c - (int) 'a';
        return (child[indx] != null);
    }

    public TrieNode getChild(char k) {
        int indx = (int) k - (int) 'a';
        return child[indx];
    }

}

