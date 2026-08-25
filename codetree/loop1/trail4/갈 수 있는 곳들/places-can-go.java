import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Queue;

class Pair {
    int r, c;
    Pair (int r, int  c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int n , k,cnt =0;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        for (int i = 0; i < k; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            cnt++;
            visited[r][c] = true;
            q.offer(new Pair(r,c));
        }
        // Please write your code here.
        simulate();

        System.out.println(cnt);
    }

    static void simulate() {

        int[] dr = new int[] {-1, 1, 0,0};
        int[] dc = new int[] { 0, 0,-1,1};

        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;

            for (int i = 0; i < 4;i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if (canGo(nr,nc)) {
                    cnt++;
                    visited[nr][nc] = true;
                    q.offer(new Pair(nr,nc));
                }
            }
        }
    }

    static boolean canGo(int r,int c) {
        return inRange(r,c) && !visited[r][c] && grid[r][c] == 0;
    }

    static boolean inRange(int r,int c) {
        return r >= 0 && r < n && c >= 0 && c< n;
    }
}