import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//	19196KB , 172ms

public class Clean_03184_양 {
    static final int[] mY = {1, 0, -1, 0};
    static final int[] mX = {0, 1, 0, -1};
    static int R, C;
    static char[][] yard;
    static boolean[][] visited;

    static BufferedReader br;
    static StringTokenizer st;
    static Queue<Point> q;
    static Count res;

    public static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static class Count {
        int wolf;
        int sheep;
        public Count(int wolf, int sheep){
            this.wolf = wolf;
            this.sheep = sheep;
        }

    }

    public static void main(String[] args) throws IOException {

        initAndInput();

        BruteForce();

        System.out.println(res.sheep + " " + res.wolf);
    }


    private static void initAndInput() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        yard = new char[R][C];
        visited = new boolean[R][C];
        res = new Count(0,0);

        for (int r = 0; r < R; r++) {
            String yardLine = br.readLine();
            for (int c = 0; c < C; c++) {
                yard[r][c] = yardLine.charAt(c);
            }
        }
    }

    public static void BruteForce() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c]) {
                    CountSheepWolf(r, c);
//                    SheepVSWolf();
                }
            }
        }
    }

    private static void SheepVSWolf(Count count) {
        if (count.wolf < count.sheep)
            res.sheep += count.sheep;
        else
            res.wolf += count.wolf;
    }


    public static void CountSheepWolf(int r, int c) {
        //init
        Count areaCnt = new Count(0,0);
        q = new LinkedList<>();

        visited[r][c] = true;
        q.add(new Point(r, c));

        //start BFS
        while (!q.isEmpty()) {
            Point p = q.poll();
            char now = yard[p.y][p.x];

            if(now == 'v')
                areaCnt.wolf++;
            if(now == 'o')
                areaCnt.sheep++;

            for (int d = 0; d < 4; d++) {
                int nextY = p.y + mY[d];
                int nextX = p.x + mX[d];
                if (!isSafe(nextY, nextX) || visited[nextY][nextX] || yard[nextY][nextX] == '#')
                    continue;
                visited[nextY][nextX] = true;
                q.add(new Point(nextY, nextX));
            }
        }
        SheepVSWolf(areaCnt);
    }


    public static boolean isSafe(int y, int x) {
        if (y >= R || y < 0 || x < 0 || C <= x) {
            return false;
        } else {
            return true;
        }
    }



}
