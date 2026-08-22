import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Point {
    int num, r, c;
    Point (int num, int r, int c) {
        this.num = num;
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N;
    static ArrayList<Point> coins = new ArrayList<>();
    static ArrayList<Point> selected = new ArrayList<>();
    static int minMoves = Integer.MAX_VALUE;
    static Point start, end;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0 ; j < N;j++) {
                char chr = line.charAt(j);
                if (chr == 'S') {
                    start = new Point(0,i,j);
                } else if (chr == 'E') {
                    end = new Point(0,i,j);
                } else if (chr >='1' && chr <= '9') {
                    coins.add(new Point(chr - '0',i,j));
                }
            }
        }
        // Please write your code here.
        Collections.sort(coins,(a,b) -> a.num - b.num);
        findMinMoves(0, 0);

        if (minMoves == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minMoves);
        }

    }

    static void findMinMoves(int idx, int cnt) {
        if (cnt == 3) {
            int dist = getDist(start,selected.get(0)) + getDist(selected.get(0), selected.get(1)) +
            getDist(selected.get(1), selected.get(2))  + getDist(selected.get(2),end);
            minMoves = Math.min(minMoves,dist);
            return;
        }
        if (idx == coins.size()) return;

        selected.add(coins.get(idx));
        findMinMoves(idx+1, cnt+1);
        selected.remove(selected.size() - 1);

        findMinMoves(idx+1,cnt);
    }

    static int getDist(Point p1, Point p2) {
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }
}