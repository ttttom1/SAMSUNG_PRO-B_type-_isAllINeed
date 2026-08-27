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
        rotate();
        init();

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <n; j++) {
                dp[i][j] = Math.min(dp[i-1][j] + matrix[i][j] , dp[i][j-1] + matrix[i][j]);
            }
        }

        System.out.print(dp[n-1][n-1]);

    }

    static void init() {
        dp[0][0] = matrix[0][0];
        for (int i = 1 ; i < n;i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
            dp[0][i] = dp[0][i-1] + matrix[0][i]; 
        }
    }

    static void rotate() {
        
        int[][] rotated = new int[n][n];

        for(int i = 0 ; i < n;i++) {
            for (int j = 0 ; j < n;j++) {
                rotated[n-1-j][i] = matrix[i][j];
            }
        }
        matrix = rotated;
    }
}