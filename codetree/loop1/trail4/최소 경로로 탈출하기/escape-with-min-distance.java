import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, m;
    static int[][] a, dist;
    static boolean[][] visited;
    static Queue<Pair> q = new ArrayDeque<>();

    static int[] dr = {-1,1, 0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][m];
        visited = new boolean[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = sc.nextInt();
        // Please write your code here.
        push(0,0,0);
        bfs();
        System.out.println(dist[n-1][m-1] - 1);
    }

    static void bfs() {
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;

            for (int d = 0 ; d< 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if( nr >= 0 && nr <n && nc >= 0 && nc < m && !visited[nr][nc] && a[nr][nc] == 1) {
                    push(nr,nc,dist[cr][cc]);
                }
            }
        }
    }

    static void push(int r, int c, int s) {
        q.offer(new Pair(r,c));
        dist[r][c] = s + a[r][c];
        visited[r][c] = true;
    }
}