import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

class Pair {
    int  r,  c;
    Pair(int  r,int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n,m;
    static int[][] grid;
    static boolean[][] outside;
    static boolean[][] visited;
    static int removed = 0,sec = 0,totalBingha =0;
    static Queue<Pair> q = new ArrayDeque<>();

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static void remove() {
        //grid[i][j] == 1 이면 사방 탐색 하나라도 outside == true 이면,gird[i][j] = 0;
        for (int i = 0; i< n;i++) {
            for (int  j=  0; j <m;j++) {
                if(grid[i][j] == 1) {
                    for(int d = 0 ; d < 4;d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];

                        if (inRange(ni,nj) && outside[ni][nj]) {
                            grid[i][j] = 0;
                            removed++;
                            
                            break;
                        }
                    }
                    
                }
            }
        }
        totalBingha -= removed;
        //outside 가 true 이면
    }

    static void findOutside() {
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            for (int d = 0 ; d < 4 ;d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if(canGo(nr,nc)) {
                    q.offer(new Pair(nr,nc));
                    outside[nr][nc] =  true;
                    visited[nr][nc] =  true;
                }
            }

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 1) {
                    totalBingha++;
                }
            }
        // Please write your code here.
        while(totalBingha > 0) {
            visited = new boolean[n][m];
            outside = new boolean[n][m];
            visited[0][0] = true;
            outside[0][0] = true;
            q.offer(new Pair(0,0));
            findOutside();
            removed= 0;
            remove();
            sec++;
        }

        System.out.println(sec + " " + removed);

    }

    static boolean inRange(int r,int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    static boolean canGo(int r, int c) {    //바깥물 찾기 전용
        return inRange(r,c) && !visited[r][c] && grid[r][c] == 0;
    }
}