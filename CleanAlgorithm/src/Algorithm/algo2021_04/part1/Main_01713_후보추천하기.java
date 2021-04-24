package Algorithm.algo2021_04.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//12968kb, 128ms
public class Main_01713_후보추천하기 {

    static PriorityQueue<Student> pq;
    static StringTokenizer st;

    static class Student implements Comparable<Student> {
        int idx;
        int count;
        int time;

        @Override
        public int compareTo(Student o) {
            if (this.count == o.count) {
                return o.time - this.time;
            }
            return o.count - this.count;
        }

        public Student(int idx, int count, int time) {
            this.idx = idx;
            this.count = count;
            this.time = time;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        int N = StoI();
        st = new StringTokenizer(br.readLine());
        int R = StoI();
        st = new StringTokenizer(br.readLine());

        ConcurrentHashMap<Integer, Student> hm = new ConcurrentHashMap<>();
        for (int i = 0; i < R; i++) {
            Integer next = StoI();
            Student newObj = new Student(next, 1, i);

            if (hm.size() == N && !hm.containsKey(next)) {
                LinkedList<Student> list = new LinkedList<>(hm.values());
                Collections.sort(list);
                Student remove =list.removeLast();
                hm.remove(remove.idx);
                hm.put(next, newObj);
            } else if (hm.containsKey(next)) {//갱신
                hm.put(next, new Student(next, hm.get(next).count + 1, hm.get(next).time ));
            } else {
                hm.put(next, newObj);
            }
        }

        LinkedList<Integer> result = new LinkedList<>();
        for (Student student : hm.values()) {
            result.add(student.idx);
        }
        Collections.sort(result);

        for (Integer idx : result)
            sb.append(idx).append(" ");
        System.out.println(sb.toString());
    }

    private static Integer StoI() {
        return Integer.parseInt(st.nextToken());
    }
}
