package Algorithm.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Clean_09079_동전게임 {

    static int min;
    static boolean[][] map;
    static boolean[][] temp;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int N = SToI(st.nextToken());
        for (int i = 0; i < N; i++) {
            init();

            solve(0, 0, 0);

            min = min == Integer.MAX_VALUE ? -1 : min;
            sb.append(min).append("\n");
        }

        System.out.println(sb.toString());

    }

    private static int SToI(String s) {
        return Integer.parseInt(s);
    }

    private static void init() throws IOException {
        map = new boolean[3][3];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                if (st.nextToken().equals("H"))
                    map[i][j] = true;
            }
        }
    }

    private static void solve(int start, int cnt, int sum) {
        if (start == 8) {
            if (isSame(sum)) {
                min = Math.min(min, cnt);
            }
            return;
        }

        solve(start + 1, cnt, sum);
        solve(start + 1, cnt + 1, sum + (1 << start));

    }

    private static boolean isSame(int sum) {
        temp = new boolean[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(map[i], 0, temp[i], 0, 3);

        for (int i = 0; i < 8; i++) {
            if ((sum & (1 << i)) != 0) {
                if (i < 3) {
                    for (int x = 0; x < 3; x++)
                        temp[i][x] = !temp[i][x];
                } else if (i < 6) {
                    for (int y = 0; y < 3; y++)
                        temp[y][i - 3] = !temp[y][i - 3];
                } else if (i == 6) {
                    for (int c = 0; c < 3; c++)
                        temp[c][c] = !temp[c][c];
                } else {
                    for (int c = 0; c < 3; c++)
                        temp[c][2 - c] = !temp[c][2 - c];
                }
            }
        }
        boolean first = temp[0][0];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (first != temp[y][x])
                    return false;
            }
        }
        return true;
    }
}
