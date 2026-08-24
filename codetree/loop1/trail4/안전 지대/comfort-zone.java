import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int n;
    static int m;
    static int[][] grid;
    static boolean[][] visited;
    static int[] safeZoneCnt;
    static int[] ks;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = { 0, 0,-1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        safeZoneCnt = new int[101];

        visited = new boolean[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        
        for( int k = 1; k <= 100; k++) {
            visited = new boolean[n][m];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(grid[i][j] <= k) {
                        visited[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < n;i++) {
                for (int j = 0; j < m;j++) {

                    if(!visited[i][j]) {
                        safeZoneCnt[k] += 1;
                        visited[i][j] = true;
                        simulate(i,j);
                    
                    }
                }
            }
        }

        int maxIdx =1;
        int maxCnt = 0;
        for (int i = 0 ; i < 101; i++) {
            
            if (safeZoneCnt[i] > maxCnt) {
                maxIdx = i;
                maxCnt = safeZoneCnt[i];
            }
        }
        System.out.print(maxIdx + " " +maxCnt);
    }

    static void simulate(int r, int c) {
        
        for (int i= 0 ; i < 4; i++) {
                
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(inRange(nr,nc)) {
                if (!visited[nr][nc]){
                    visited[nr][nc] = true;
                    simulate(nr,nc);
                }
            }
        }
    }

    static boolean inRange(int r,int c) {
        return r>= 0 && r < n && c >= 0 && c < m;
    }
}