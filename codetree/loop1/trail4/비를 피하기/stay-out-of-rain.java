import java.util.*;

class Pair {
    int r,c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, h, m;
    static int[][] a, dist;
    static boolean[][] visited;
    static ArrayList<Pair> people = new ArrayList<>();
    static Queue<Pair> q = new ArrayDeque<>();
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        h = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][n];
        dist = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                if (a[i][j] == 3) people.add(new Pair(i,j));
            }

        for (Pair p : people)  {
            visited[p.r][p.c] = true;
            q.offer(p);
        }

        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;

            for (int d = 0 ; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (!visited[nr][nc] && a[nr][nc] != 1) {

                        dist[nr][nc] = dist[cr][cc] + 1;
                        visited[nr][nc] = true;
                        q.offer(new Pair(nr,nc));
                    }
                }
            }
        }
        //print
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(a[i][j] == 2) {
                    if(dist[i][j] == 0) System.out.print(-1 + " ");
                    else {
                        System.out.print(dist[i][j] + " ");
                    }
                }
                else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
}