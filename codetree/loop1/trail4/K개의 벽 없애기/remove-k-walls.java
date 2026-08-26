import java.util.*;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n,k, r1,c1,r2,c2;
    static int[][] grid;
    static int[][] times;
    static boolean[][] visited;
    static ArrayList<Pair> walls = new ArrayList<>();
    static ArrayList<Pair> selected = new ArrayList<>();
    static int minTime= Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        times = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1) {
                    walls.add(new Pair(i,j));
                }
            }
        }
        r1 = sc.nextInt()-1;
        c1 = sc.nextInt()-1;
        r2 = sc.nextInt()-1;
        c2 = sc.nextInt()-1;
        
        // Please write your code here.
        // 벽 선택
        selectWalls(0, 0);

        //제거 후 bfs 
        if (minTime == Integer.MAX_VALUE) {
            System.out.print(-1);
        }else {
            System.out.print(minTime);
        }
    }
    static void selectWalls(int cnt, int idx) {
        if (cnt == k) {
            removeAndBfs();
            return;
        }

        for (int i = idx; i < walls.size();i++) {
            selected.add(walls.get(i));
            selectWalls(cnt + 1,  i + 1);
            selected.remove(selected.size() -1);
        }
    }
    static void removeAndBfs() {
        for (Pair p : selected) {
            int cr = p.r;
            int cc = p.c;

            grid[cr][cc] = 0;
        }

        bfs();

        for (Pair p: selected) {
            int cr = p.r;
            int cc = p.c;
            grid[cr][cc] = 1;
        }
    }

    static void bfs() {
        Queue<Pair> q = new ArrayDeque<>();
        visited = new boolean[n][n];
        q.offer(new Pair(r1, c1));
        visited[r1][c1] = true;
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;
            int[] dr = {-1,1,0,0};
            int[] dc = {0,0,-1,1};
            for (int i = 0;i < 4;i++) {
                int nr= cr + dr[i];
                int nc = cc + dc[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if(!visited[nr][nc] && grid[nr][nc] == 0) {
                        if(nr == r2 && nc == c2) {
                            minTime = Math.min(minTime,times[cr][cc] + 1);
                            return;
                        }
                        
                        visited[nr][nc] = true;
                        times[nr][nc] = times[cr][cc] + 1;
                        q.offer(new Pair(nr,nc));
                    }
                }
            }
        }
    }
}