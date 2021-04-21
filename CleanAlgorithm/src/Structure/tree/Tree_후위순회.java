package Structure.tree;

import java.util.Queue;

//          A
//       B     C
//     D   E  F  G
public class Tree_후위순회 {
    static Node A = new Node("A");
    static Node B = new Node("B");
    static Node C = new Node("C");
    static Node D = new Node("D");
    static Node E = new Node("E");
    static Node F = new Node("F");
    static Node G = new Node("G");

    public static void main(String[] args) {
        init();
        traverse(A);
    }

    private static void traverse(Node now) {
        if (now.left != null) {
            traverse(now.left);
        }

        if (now.right != null) {
            traverse(now.right);
        }
        System.out.print(now.name + " ");
    }


    public static void init() {
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        C.right = G;
    }

}


