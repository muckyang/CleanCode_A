package Algorithm.algo2021_04.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Clean_05015_ls {
    static BufferedReader br;
    static String pattern;
    static int N;
    static Node trie;

    private static class Node {
        char now;
        HashSet<Node> nodeHash = new HashSet<>();
        boolean isEnd = false;

        public Node(char now) {
            this.now = now;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pattern = br.readLine();
        trie = new Node('\u0000');

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            add(trie, s);
        }
    }

    private static void add(Node trie, String s) {
        Node next = new Node(s.charAt(0));
        trie.nodeHash.add(next);
        if (s.length() == 1) {
            trie.isEnd = true;
            return;
        }
        add(next, s.substring(1, s.length() - 1));
    }

}
