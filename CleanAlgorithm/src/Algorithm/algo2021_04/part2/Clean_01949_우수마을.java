package Algorithm.algo2021_04.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Clean_01949_우수마을 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static boolean [] visit;
    static int [] feed;
    static int [][] cache;
    static LinkedList<Integer>[] list;
    static int max;

    public static void main(String[] args)throws IOException {
        init();
        solve(1);
        max = Math.max(cache[0][1],cache[1][1]);
        System.out.println(max);
    }

    private static void solve(int town) {
        visit[town] = true;
        int rSum = 0;
        int bSum = 0;
        for(Integer each : list[town]){
            if(visit[each])
                continue;
            visit[each]=true;
            solve(each);
            rSum+= Math.max(cache[0][each],cache[1][each]);
            bSum+= cache[0][each];
        }
        cache[0][town] = rSum;
        cache[1][town] = feed[town]+bSum;

    }

    private static void init() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = StoI();
        visit = new boolean[N + 1];
        feed = new int[N + 1];
        cache = new int[2][N + 1]; // 0 비선택 , 1 선택
        max = 0;
        list = new LinkedList[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            list[i] = new LinkedList<>();
            feed[i] = StoI();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = StoI();
            int to = StoI();
            list[from].add(to);
            list[to].add(from);
        }
    }



    private static Integer StoI() {
        return Integer.parseInt(st.nextToken());
    }
}
