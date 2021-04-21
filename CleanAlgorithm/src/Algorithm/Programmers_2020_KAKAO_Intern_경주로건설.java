package Algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

//Complete
public class Programmers_2020_KAKAO_Intern_경주로건설 {
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int N, M;
    static PriorityQueue<Point> pq;

    public static class Point implements Comparable<Point> {
        int y;
        int x;
        int sum;
        int backD;

        @Override
        public int compareTo(Point o) {
            return this.sum - o.sum;
        }

        public Point(int y, int x, int sum, int backD) {
            this.y = y;
            this.x = x;
            this.sum = sum;
            this.backD = backD;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        N = board.length;
        M = board[0].length;
        pq = new PriorityQueue<>();
        int[][][] visit = new int[4][N][M];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }
        }
        visit[0][0][0] = visit[1][0][0] = visit[2][0][0] = visit[3][0][0] = 0;
        pq.add(new Point(0, 0, 0, -1));

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.y == N - 1 && p.x == M - 1){
                answer = Math.min(answer,p.sum);
            }
            for (int d = 0; d < 4; d++) {
                int jy = p.y + dy[d];
                int ix = p.x + dx[d];
                if (ix >= M || ix < 0 || jy >= N || jy < 0 || board[jy][ix] == 1)
                    continue;
                if (p.backD == -1 || p.backD == d) {
                    if(visit[d][jy][ix] > p.sum + 100) {
                        visit[d][jy][ix]= p.sum + 100;
                        pq.add(new Point(jy, ix, p.sum + 100, d));
                    }
                } else {
                    if(visit[d][jy][ix] > p.sum + 600) {
                        visit[d][jy][ix]= p.sum + 600;
                        pq.add(new Point(jy, ix, p.sum + 600, d));
                    }
                }
            }
        }
        return answer;
    }
}
