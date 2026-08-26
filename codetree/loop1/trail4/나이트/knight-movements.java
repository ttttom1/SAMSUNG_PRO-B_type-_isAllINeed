import java.util.*;

class Pair {
    int r, c , cnt;
    Pair(int r,int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

public class Main {
    static int[][] dir = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static int n,r1,c1,r2,c2;
    static boolean[][] visited;
    static Queue<Pair> q = new ArrayDeque<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r1 = sc.nextInt()-1;
        c1 = sc.nextInt()-1;
        r2 = sc.nextInt()-1;
        c2 = sc.nextInt()-1;
        // Please write your code here.
        if (r1 == r2 && c1 == c2) {
            System.out.println(0);
            return;
        }
        
        visited = new boolean[n][n];
        visited[r1][c1] = true;
        q.offer(new Pair(r1,c1,0));
        bfs();
        
    }

    static void bfs () {
        while(!q.isEmpty() ) {
            Pair curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;
            int cCnt = curr.cnt;
            for (int d = 0; d < 8;d++) {
                int nr = cr + dir[d][0];
                int nc = cc + dir[d][1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (nr == r2 && nc == c2) {
                        System.out.print(cCnt+1);
                        return ;
                    }
                    if(!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new Pair(nr,nc,cCnt + 1));
                    }
                
                }
            }
        }
        System.out.println(-1);
    }
}