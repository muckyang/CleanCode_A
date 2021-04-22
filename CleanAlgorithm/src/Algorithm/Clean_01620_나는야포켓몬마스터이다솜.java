package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Clean_01620_나는야포켓몬마스터이다솜 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String,Integer> pokeList = new HashMap<>();
        String [] number = new String [N+1];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            pokeList.put(s,i+1);
            number[i+1] = s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (isAlpha(s.charAt(0))) {
                sb.append(pokeList.get(s)).append("\n");
            }else{
                sb.append(number[Integer.parseInt(s)]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static boolean isAlpha(char c) {
        return ('A' <= c) && (c <= 'Z');
    }
}
