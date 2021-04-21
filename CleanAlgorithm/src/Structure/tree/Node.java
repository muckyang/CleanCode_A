package Structure.tree;

public class Node {
    String name;
    boolean visit = false;
    Node right = null;
    Node left = null;

    public Node(String name) {
        this.name = name;
    }
}
