/* import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int sumA;
        int countA;

        State(int sumA, int countA) {
            this.sumA = sumA;
            this.countA = countA;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        int totalSum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            totalSum += arr[i];
        }

        Queue<State> queue = new ArrayDeque<>();

        // 0번째 원소를 A에 넣는 경우 vs B에 넣는 경우
        queue.add(new State(arr[0], 1));
        queue.add(new State(0, 0));

        for (int i = 1; i < n; i++) {
            int size = queue.size();
            int val = arr[i];

            // 이번 단계(i번째 원소 처리 후)에서 등장한 (sumA, countA) 중복 방지용
            boolean[][] nextVisited = new boolean[totalSum + 1][n + 1];

            for (int q = 0; q < size; q++) {
                State cur = queue.poll();

                // 1) arr[i]를 그룹 B에 넣는 경우 (sumA, countA 유지)
                if (!nextVisited[cur.sumA][cur.countA]) {
                    nextVisited[cur.sumA][cur.countA] = true;
                    queue.add(new State(cur.sumA, cur.countA));
                }

                // 2) arr[i]를 그룹 A에 넣는 경우 (sumA + val, countA + 1)
                int nextSumA = cur.sumA + val;
                int nextCountA = cur.countA + 1;
                if (nextSumA <= totalSum && nextCountA <= n && !nextVisited[nextSumA][nextCountA]) {
                    nextVisited[nextSumA][nextCountA] = true;
                    queue.add(new State(nextSumA, nextCountA));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            State s = queue.poll();
            // 각 그룹이 최소 1개 이상의 원소를 가져야 함
            if (s.countA >= 1 && s.countA <= n - 1) {
                int sumB = totalSum - s.sumA;
                ans = Math.min(ans, Math.abs(s.sumA - sumB));
            }
        }

        System.out.println(ans);
    }
}

*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        int totalSum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            totalSum += arr[i];
        }

        int half = totalSum / 2;
        final int INF = 1_000_000_000;

        // minCnt[s]: 합 s를 만들 때 필요한 최소 원소 개수
        // maxCnt[s]: 합 s를 만들 때 만들 수 있는 최대 원소 개수
        int[] minCnt = new int[totalSum + 1];
        int[] maxCnt = new int[totalSum + 1];
        Arrays.fill(minCnt, INF);
        Arrays.fill(maxCnt, -INF);

        minCnt[0] = 0;
        maxCnt[0] = 0;

        for (int val : arr) {
            for (int s = totalSum; s >= val; s--) {
                int prev = s - val;
                if (minCnt[prev] != INF) {
                    minCnt[s] = Math.min(minCnt[s], minCnt[prev] + 1);
                    maxCnt[s] = Math.max(maxCnt[s], maxCnt[prev] + 1);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int s = 0; s <= totalSum; s++) {
            // 합 s를 만드는 방법 중, 그룹 A의 원소 개수가 1 이상 (n - 1) 이하인 경우가 있는지 확인
            if (maxCnt[s] >= 1 && minCnt[s] <= n - 1) {
                int diff = Math.abs(totalSum - 2 * s);
                if (diff < ans) {
                    ans = diff;
                }
            }
        }

        System.out.println(ans);
    }
}