package Algorithm.Todo;

import java.util.*;

//Fail
public class Programmers_2020_KAKAO_Intern_동굴탐험 {


    public static void main(String[] args) {
        int n = 9;
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{4,1},{8,7},{6,5}};
        System.out.println(solution(n, path, order));
    }

    static boolean[] isLocked;
    static Node[] nodes;
    static HashMap<Integer, Integer> hm;

    public static class Node {
        int now;
        LinkedList<Node> link = new LinkedList<>();

        public Node(int now) {
            this.now = now;
        }
    }

    public static boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        isLocked = new boolean[n];
        hm = new HashMap<>();
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] eachPath : path) {
            int from = eachPath[0];
            int to = eachPath[1];
            nodes[from].link.add(nodes[to]);
            nodes[to].link.add(nodes[from]);
        }
        for (int[] ints : order) {
            int key = ints[0];
            int lock = ints[1];
            isLocked[lock] = true;
            hm.put(key, lock);
        }

        Queue<Node> q = new LinkedList<>();
        HashMap<Integer,Node> qq = new HashMap<>();
        q.add(nodes[0]);
        int depth = 0;
        boolean[] visit = new boolean[n];
        visit[0] = true;
        int[] cntCheck = new int[n];
        while (!q.isEmpty()) {
            Node p = q.poll();

            if(isLocked[p.now]){
                if(depth == cntCheck[p.now])
                    return false;
                qq.put(p.now,p);
                continue;
            }

            for (Node ea : p.link) {
                depth++;
                Arrays.fill(cntCheck,depth);
                if(visit[ea.now])
                    continue;
                visit[ea.now] = true;
                if(hm.containsKey(ea.now)){
                    int idx = hm.remove(ea.now);
                    isLocked[idx]= false;
                    if(qq.containsKey(idx)){
                        q.add(qq.remove(idx));
                    }
                }
                q.add(ea);
            }
        }
        return true;
    }
}
