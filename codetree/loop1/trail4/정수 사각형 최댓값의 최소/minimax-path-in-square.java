import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        int[][] dp = new int[n][n];
        for(int i = 0 ; i< n;i++) {
            Arrays.fill(dp[i], -1);
        }
        
        dp[0][0] = matrix[0][0];

        for(int i = 1 ; i < n;i++) {
            dp[i][0] = Math.max(matrix[i][0] ,dp[i-1][0]);
            dp[0][i] = Math.max(matrix[0][i], dp[0][i-1]);
        }

        for(int i = 1; i < n; i++) {
            for(int j= 1;  j  < n;j++) {
                    dp[i][j] = Math.max(matrix[i][j], Math.min(dp[i-1][j],dp[i][j-1]));
            }
        }

        System.out.println(dp[n-1][n-1]);
    }
}