package Algorithm.Todo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//Fail
public class Programmers_2020_KAKAO_Intern_동굴탐험2 {


    public static void main(String[] args) {
        int n = 9;
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{4,1},{8,7},{6,5}};
        System.out.println(solution(n, path, order));
    }

    static boolean[] isLocked;
    static Node[] nodes;

    private static class Node {
        int now;
        LinkedList<Node> link = new LinkedList<>();

        public Node(int now) {
            this.now = now;
        }
    }

    private static boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        isLocked = new boolean[n];
        nodes = new Node[n];

        LinkedList<Integer> [] lists = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
            lists[i] = new LinkedList<>();
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
            lists[key].add(lock);
        }

        Queue<Node> q = new LinkedList<>();
        HashMap<Integer,Node> qq = new HashMap<>();
        q.add(nodes[0]);

        boolean[] visit = new boolean[n];
        visit[0] = true;
        while (!q.isEmpty()) {
            Node p = q.poll();
            visit[p.now] = true;
            for (Node ea : p.link) {
                if(visit[ea.now])
                    continue;
                if(isLocked[ea.now]){
                    qq.put(ea.now,ea);
                    continue;
                }

                q.add(ea);
                if(lists[ea.now].size()>0) {
                    for(Integer idx : lists[ea.now]) {
                        if(isLocked[idx]) {
                            isLocked[idx] = false;
                            if (qq.containsKey(idx)) {
                                q.add(qq.remove(idx));
                            }
                        }
                    }
                }
            }
        }
        for(boolean b :visit){
            if(!b)
                return false;
        }
        return qq.isEmpty();
    }
}
