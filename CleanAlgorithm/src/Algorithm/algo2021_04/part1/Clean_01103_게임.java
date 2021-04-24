package Algorithm.algo2021_04.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/// 12432kb,	88ms
public class Clean_01103_게임 {
    static int N, M;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static boolean[][] visit;
    static int[][] cache;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int answer;

    public static void main(String[] args) throws IOException {
        answer = 0;
        input();
        solve(0, 0);
        answer = answer != -1 ? cache[0][0] : -1;
        System.out.println(answer);
    }


    private static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = StoI();
        M = StoI();
        map = new int[N][M];
        visit = new boolean[N][M];
        cache = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == 'H') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = c - '0';
                }
            }
        }
    }

    private static int solve(int y, int x) {
        if (cache[y][x] != 0) {
            return cache[y][x];
        }
        int localMax = 1;
        int moveDist = map[y][x];
        for (int d = 0; d < 4; d++) {
            int jy = y + dy[d] * moveDist;
            int ix = x + dx[d] * moveDist;
            if (isOut(jy, ix) || map[jy][ix] == 0)
                continue;
            if (visit[jy][ix]) {
                answer = -1;
                return 0;
            }
            visit[jy][ix] = true;
            localMax = Math.max(localMax, solve(jy, ix) + 1);
            visit[jy][ix] = false;
        }
        return cache[y][x] = localMax;
    }

    private static boolean isOut(int y, int x) {
        return y >= N || y < 0 || x >= M || x < 0;
    }

    private static Integer StoI() {
        return Integer.parseInt(st.nextToken());
    }
}
