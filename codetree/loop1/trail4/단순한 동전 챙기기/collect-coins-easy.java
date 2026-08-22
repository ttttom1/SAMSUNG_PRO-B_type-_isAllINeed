import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Point {
    int num, r, c;
    Point(int num, int r, int c) {
        this.num = num;
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N;
    static Point start, end;
    static ArrayList<Point> coins = new ArrayList<>();
    static ArrayList<Point> selected = new ArrayList<>();
    static int minDist = Integer.MAX_VALUE;

    // 맨해튼 거리 계산
    static int getDist(Point p1, Point p2) {
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }

    // 동전 3개를 고르는 백트래킹 (조합)
    static void findCombination(int idx, int cnt) {
        if (cnt == 3) {
            // S -> 동전1 -> 동전2 -> 동전3 -> E 거리 합산
            int totalDist = getDist(start, selected.get(0))
                          + getDist(selected.get(0), selected.get(1))
                          + getDist(selected.get(1), selected.get(2))
                          + getDist(selected.get(2), end);

            minDist = Math.min(minDist, totalDist);
            return;
        }

        if (idx == coins.size()) return;

        // 현재 동전 선택
        selected.add(coins.get(idx));
        findCombination(idx + 1, cnt + 1);
        selected.remove(selected.size() - 1);

        // 현재 동전 미선택
        findCombination(idx + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String row = sc.next();
            for (int j = 0; j < N; j++) {
                char ch = row.charAt(j);
                if (ch == 'S') {
                    start = new Point(0, i, j);
                } else if (ch == 'E') {
                    end = new Point(0, i, j);
                } else if (ch >= '1' && ch <= '9') {
                    coins.add(new Point(ch - '0', i, j));
                }
            }
        }

        // 동전 번호 오름차순 정렬
        Collections.sort(coins, (a, b) -> a.num - b.num);

        findCombination(0, 0);

        // 3개 이상 수집이 불가능한 경우 -1 출력
        if (minDist == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minDist);
        }
    }
}