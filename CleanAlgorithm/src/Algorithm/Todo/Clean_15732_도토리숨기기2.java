package Algorithm.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Clean_15732_도토리숨기기2 {

    static int N, K, D;
    static ArrayList<Dotori> list;
    static boolean[] visit;

    static int maxIndex;
    static int min, max;
    static BufferedReader br;

    public static class Dotori {
        int start;
        int dist;
        int end;

        public Dotori(int nowIdx, int dist, int end) {
            this.start = nowIdx;
            this.dist = dist;
            this.end = end;
        }

        public int compareTo(Dotori d) {
            return this.start - d.start;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        if (N == 1)
            System.out.println(1);
        else {
            solve(N/2);
            System.out.println(maxIndex);
        }
    }

    public static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = StoI(st.nextToken());
        K = StoI(st.nextToken());
        D = StoI(st.nextToken());
        list = new ArrayList<>();
        visit = new boolean[N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = StoI(st.nextToken());
            int end = StoI(st.nextToken());
            int dist = StoI(st.nextToken());
            list.add(new Dotori(start, dist, end));
        }
        min = 1;
        max = N;
    }

    public static void solve(int now) {
        if (visit[now])// end rule
            return;
        int next;
        if (canAll(now)) {
            min = now;
            next = (min+ max)/2+1;
        } else {
            max = now;
            next = (min+ max)/2-1;
        }
        visit[now] = true;
        solve(next);
    }

    public static boolean canAll(int index) {
        int sum = 0;
        int localMax = 0;
        for (Dotori each : list) {
            if (each.start <= index) {
                int endMax = Math.min(index, each.end);
                int range = endMax - each.start;
                int cnt = range / each.dist + 1;
                localMax = Math.max(localMax, (cnt-1) * each.dist + each.start);
                sum += cnt;
            }
        }
        if (D > sum) { //dotori nam a
            return true;
        } else {
//            System.out.println(index + " " + sum);
            if(D==sum)
                maxIndex = Math.max(maxIndex, localMax);
            return false;
        }

    }

    public static int StoI(String s) {
        return Integer.parseInt(s);
    }
}
