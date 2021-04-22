package Algorithm.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Clean_15732_도토리숨기기 {

    static int N, K, D;
    static PriorityQueue<Dotori> pq;

    static int maxIndex;

    static BufferedReader br;

    private static class Dotori implements Comparable<Dotori>{
        int nowIdx;
        int dist;
        int end;

        public Dotori(int nowIdx,int dist, int end) {
            this.nowIdx = nowIdx;
            this.dist= dist;
            this.end = end;
        }

        public int compareTo(Dotori d){
            return this.nowIdx - d.nowIdx;
        }
    }
    public static void main(String[] args) throws IOException {
        init();

        solve();

        System.out.println(maxIndex);
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pq= new PriorityQueue<>();
        N = StoI(st.nextToken());
        K = StoI(st.nextToken());
        D = StoI(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = StoI(st.nextToken());
            int end = StoI(st.nextToken());
            int dist = StoI(st.nextToken());
            pq.add(new Dotori(start,dist,end));
        }
    }

    private static void solve() {
        while(!pq.isEmpty()){
            Dotori dotori = pq.poll();
            D--;
            if(D ==0) {
                maxIndex = dotori.nowIdx;
                return;
            }
            int next = dotori.nowIdx+dotori.dist;
            if(next <= dotori.end ) {
                dotori.nowIdx= next;
                pq.add(dotori);
            }
        }
    }

    private static int StoI(String s) {
        return Integer.parseInt(s);
    }
}
