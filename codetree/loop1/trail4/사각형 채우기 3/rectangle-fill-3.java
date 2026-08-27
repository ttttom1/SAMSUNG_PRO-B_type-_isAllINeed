import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        long[] dp = new long[Math.max(n + 1, 5)];
        dp[1] = 2;
        dp[2] = 7;
        dp[0] = 1;

        long sum  =  0;
        for (int i = 3; i <= n;i++) {
            sum = (sum + dp[i-3] * 2)  % 1_000_000_007;
            dp[i] = (dp[i-1] * 2 + dp[i-2] * 3 + sum) % 1_000_000_007;
        }
        System.out.println(dp[n]);

    }
}