import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.

        long[] dp = new long[Math.max(n+1,4)];
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= n;i++) {
            dp[i] = (dp[i-2] + dp[i-3])  % 10007;
        }
        System.out.print(dp[n]);
    }
}