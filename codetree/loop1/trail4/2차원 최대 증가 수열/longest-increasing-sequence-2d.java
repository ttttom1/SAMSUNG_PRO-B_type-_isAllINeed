import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        int[][] dp = new int[n][m];
        dp[0][0] =  1;
        for (int i = 1; i< n;i++) {
            for(int j = 1; j< m;j++) {
                for(int p = 0;p < i;p++ ) {
                    for(int q = 0;q < j;q++) {
                        if(dp[p][q] != 0 && grid[i][j] > grid[p][q]) {
                            dp[i][j] = Math.max(dp[i][j], dp[p][q] + 1);
                        }
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i< n;i++) {
            for(int j = 1; j< m;j++) {
                max  =  Math.max(max, dp[i][j]);
            }
        }
        System.out.print(max);
    }
}