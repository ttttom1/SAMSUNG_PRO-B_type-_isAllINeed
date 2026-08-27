import java.util.*;

class Pair {
    int r,c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static int n,k;
    static int[][] grid;
    static ArrayList<Pair> rotten = new ArrayList<>();
    static Queue<Pair> q = new ArrayDeque<>();
    static int[][] times;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        times = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 2) {
                    rotten.add(new Pair(i,j));
                }
                if(grid[i][j] == 0){
                    times[i][j] = -1;
                }
            }
        // Please write your code here.

        
        visited = new boolean[n][n];

        for(Pair p: rotten) {
            q.offer(p);
            visited[p.r][p.c] = true;
        }

        while(!q.isEmpty()) {
            Pair curr  = q.poll();
            int cr = curr.r;
            int cc = curr.c;
            int[] dr = {-1,1,0,0};
            int[] dc =  {0,0,-1,1};
            for (int d = 0 ; d < 4;  d++) {
                int nr = cr +dr[d];
                int nc = cc + dc[d];

                if( nr >=  0  && nr < n&& nc >= 0 && nc <n){
                    if(!visited[nr][nc] && grid[nr][nc] ==  1)  {
                        times[nr][nc] =  times[cr][cc]  + 1;
                        visited[nr][nc] =  true;
                        q.offer(new  Pair(nr,nc));
                    }
                    
                }
            }
        }

        for (int i= 0 ; i<n;i++) {
            for (int j =  0 ;  j < n;j++) {
                if (times[i][j] == 0 && grid[i][j] == 1) {
                    times[i][j] = -2;
                }
            }
        }
        for (int i= 0 ; i<n;i++) {
            for (int j =  0 ;  j < n;j++) {
                System.out.print(times[i][j] + " ");
            }
            System.out.println();
        }
    }
}