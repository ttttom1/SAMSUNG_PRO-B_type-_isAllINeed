import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int n; 
    static int[][] grid;
    static int[][] dp;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
            Arrays.fill(dp[i], -1);
        }
        // Please write your code here.

        int maxPath = 0;
        for (int i = 0 ; i< n;i++) {
            for(int j  =0; j <  n;j++) {
                maxPath = Math.max(maxPath,  findMaxPath(i, j));
            }
        }
        System.out.print(maxPath);
    }

    static int findMaxPath(int x, int y) {
        if(dp[x][y] != -1){
            return dp[x][y];
        }

        dp[x][y] = 1;
        for (int d = 0 ;  d < 4; d++) {
            int nx =x + dr[d];
            int ny = y + dc[d];

            if(nx >= 0 && nx< n&& ny >= 0 && ny < n) {
                if(grid[x][y] < grid[nx][ny]) {
                    dp[x][y] = Math.max(dp[x][y], 1  + findMaxPath(nx,ny));
                }
            }
        }
        return dp[x][y];
    }
}