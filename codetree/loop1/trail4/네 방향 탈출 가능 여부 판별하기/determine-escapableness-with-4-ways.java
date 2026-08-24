import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Queue;


class Pair {
        int r,c;
        Pair(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }

public class Main {

    

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = { 0, 0,-1, 1};
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        visited[0][0] = true;
        q.offer(new Pair(0,0));
        simulate();
        if (!visited[n-1][m-1]) System.out.println(0);
        else System.out.println(1);
    }

    static void simulate() {
        while(!q.isEmpty()) {
            Pair currV = q.poll();

            int r = currV.r;
            int c = currV.c;
            
            for (int i= 0;  i < 4;i++)  {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!inRange(nr,nc))  continue;

             

                if(!visited[nr][nc] && grid[nr][nc]  == 1) {
                    visited[nr][nc] = true;
                    q.offer(new Pair(nr,nc));
                }
            }
        }
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >=  0 && c < m;
    }
}