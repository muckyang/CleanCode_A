package Algorithm.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//fAIL 문제 파악 다시
public class Clean_2533_사회망서비스 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static PriorityQueue<People> pq;
    static LinkedList<Integer>[] list;
    static boolean[] visit;

    static int notLinkCnt, answer;

    private static class People implements Comparable<People> {
        int idx;
        int leafCnt;

        public People(int idx, int leafCnt) {
            this.idx = idx;
            this.leafCnt = leafCnt;
        }

        @Override
        public int compareTo(People o) {
            return o.leafCnt - this.leafCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(answer);
    }

    private static void solve() {
        while (!pq.isEmpty()) {
            int pCnt = 0;
            People p = pq.poll();
            int index = p.idx;
            if (!visit[index]) {
                pCnt++;
                visit[index] = true;
            }
            for (Integer eachIndex : list[index]) {
                if(!visit[eachIndex]){
                    pCnt++;
                    visit[eachIndex] =true;
                }
            }
            answer++;
            notLinkCnt-=pCnt;
            if(notLinkCnt == 0)
                return;
        }
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = StoI();
        visit = new boolean[N + 1];
        notLinkCnt = N;
        answer = 0;
        list = new LinkedList[N + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i < N + 1; i++)
            list[i] = new LinkedList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = StoI();
            int to = StoI();
            list[from].add(to);
            list[to].add(from);
        }
        for (int i = 1; i <= N; i++) {
            int leafCnt = 0;
            for (Integer node : list[i]) {
                if (list[node].size() == 1)
                    leafCnt++;
            }
            pq.add(new People(i, leafCnt));
        }
    }

    private static Integer StoI() {
        return Integer.parseInt(st.nextToken());
    }
}
