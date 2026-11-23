import java.util.*;
import java.io.*;

class Meeting implements Comparable<Meeting>{
    int startAt, endAt;
    Meeting (int startAt, int endAt) {
        this.startAt = startAt;
        this.endAt= endAt;
    }

    @Override
    public int compareTo(Meeting o) {
        if (this.endAt != o.endAt) {
            return Integer.compare(this.endAt, o.endAt);
        }
        return Integer.compare(this.startAt, o.startAt);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        // input
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        for (int i = 1; i <= N ; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int startAt = Integer.parseInt(st.nextToken());
            int endAt = Integer.parseInt(st.nextToken());

            pq.add(new Meeting(startAt, endAt));
        }

        //하나씩 뽑아서 완성 최댓 값까지
        int lastEnd = 0;
        int res = 0;
        while(!pq.isEmpty()) {
            Meeting curr = pq.poll();

            if (lastEnd <= curr.startAt) {
                res++;
                lastEnd = curr.endAt;
            }
        }
        System.out.println(res);
    }
}
