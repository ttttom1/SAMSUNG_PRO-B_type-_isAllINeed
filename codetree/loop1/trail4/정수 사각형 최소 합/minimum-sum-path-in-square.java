import java.util.Scanner;

public class Main {
    static int n, min;
    static int[][] matrix;
    static long[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        matrix = new int[n][n];
        dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        
        init();

        for (int i = 1; i <= n - 1; i++) {
            for (int j = n-2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i-1][j] + matrix[i][j] , dp[i][j+1] + matrix[i][j]);
            }
        }

        System.out.print(dp[n-1][0]);
    }

    static void init() {
        dp[0][n-1] = matrix[0][n-1];
        for (int i = 1 ; i < n;i++) {
            dp[0][n - 1 - i] = dp[0][n-i] + matrix[0][n-i-1];
            dp[i][n-1] = dp[i-1][n-1] + matrix[i][n-1]; 
        }
    }
}