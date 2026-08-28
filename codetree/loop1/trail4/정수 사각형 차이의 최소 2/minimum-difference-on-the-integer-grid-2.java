import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        int ans = Integer.MAX_VALUE;

        for (int L = 1; L <= 100; L++) {
            if (grid[0][0] < L) continue;

            int[][] dp = new int[n][n];
            for (int i = 0 ; i < n ;i++) {
                for (int j = 0 ; j < n;j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            dp[0][0] = grid[0][0];// 왜 이걸 넣지? 

            for (int i = 0; i< n; i++) {
                for (int  j = 0; j < n; j++) {
                    if(grid[i][j] < L) continue;

                    if(i > 0 && dp[i-1][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j],Math.max(dp[i-1][j], grid[i][j]));
                    }

                    if ( j > 0 && dp[i][j-1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j],Math.max(dp[i][j-1], grid[i][j]));
                    }
                }
            }

            if(dp[n-1][n-1] != Integer.MAX_VALUE) {
                int R =  dp[n-1][n-1];
                ans = Math.min(ans,R-L);
            }
        }
        System.out.println(ans);
    }
}