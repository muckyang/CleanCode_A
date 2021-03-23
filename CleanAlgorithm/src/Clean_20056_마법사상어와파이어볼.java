import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Clean_20056_마법사상어와파이어볼 {

    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    static int N, M, K;
    static HashMap<Integer, FireStack> hm;
    static Queue<Fire> q;

    static BufferedReader br;
    static StringTokenizer st;

    public static class Fire {
        int y, x, m, s, d;

        public Fire(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public Fire(FireStack fs) {
            this(fs.y, fs.x, fs.m, fs.s, fs.d);
        }

        public void move() {
            y += (dy[d] * s);
            x += (dx[d] * s);
            minusCheck();
            y %= N;
            x %= N;
        }

        private void minusCheck() {
            if (y < 0) {
                y = (y * -1) % N;
                y = y * -1 + N;
            }
            if (x < 0) {
                x = (x * -1) % N;
                x = x * -1 + N;
            }
        }
    }

    public static class FireStack extends Fire {

        int fireCnt;
        int direction;

        public FireStack(Fire f) {
            super(f.y,f.x,f.m,f.s,f.d);
            this.fireCnt = 1;
            this.direction = f.d % 2;
        }

        public FireStack appendFire(Fire f) {
            this.m += f.m;
            this.s += f.s;
            this.fireCnt++;
            if (this.direction != -1 && this.direction != f.d % 2) {
                this.direction = -1;
            }
            return this;
        }
    }

    public static void main(String[] args) throws IOException {
        initAndInput();
        solve();

        MessSumPrint();
    }

    public static void initAndInput() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = StoI();
        M = StoI();
        K = StoI();

        q = new LinkedList<Fire>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = StoI() - 1;
            int x = StoI() - 1;
            int m = StoI();
            int s = StoI();
            int d = StoI();
            q.add(new Fire(y, x, m, s, d));
        }
    }

    private static int StoI() {
        return Integer.parseInt(st.nextToken());
    }

    public static void solve() {
        for (int k = 0; k < K; k++) {
            MoveFire();
            DivideAndAdd();
        }
    }

    public static void MoveFire() {
        hm = new HashMap<>();
        while (!q.isEmpty()) {
            Fire f = q.poll();
            if(f.m == 0)
                continue;
            f.move();
            int position = f.y * N + f.x;
            FireStack stack = hm.get(position);

            if (stack==null) {
                hm.put(position, new FireStack(f));
            } else {
                hm.put(position, stack.appendFire(f));
            }
        }
    }

    private static void DivideAndAdd() {
        for (Integer i : hm.keySet()) {
            FireStack fs = hm.get(i);
            AddAndBoom(fs);
        }
    }

    private static void AddAndBoom(FireStack fs) {
        if (fs.fireCnt == 1) {
            q.add(new Fire(fs));
        } else {
            int y= fs.y;
            int x= fs.x;
            int m= fs.m/5;
            int s= fs.s/fs.fireCnt;

            if (fs.direction == -1) {
                for (int d = 1; d < 8; d += 2) {
                    q.add(new Fire(y, x, m , s, d));
                }
            } else {
                for (int d = 0; d < 8; d += 2) {
                    q.add(new Fire(y, x, m , s,d));
                }
            }
        }
    }

    public static void MessSumPrint() {
        int MessSum = 0;
        while (!q.isEmpty()) {
            MessSum += q.poll().m;
        }
        System.out.println(MessSum);
    }
}