import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];

        // 기본값 설정 (0을 만드는 경우의 수 1가지)
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (i >= 1) dp[i] = (dp[i] + dp[i - 1]) % 10007;
            if (i >= 2) dp[i] = (dp[i] + dp[i - 2]) % 10007;
            if (i >= 5) dp[i] = (dp[i] + dp[i - 5]) % 10007;
        }

        System.out.println(dp[n]);
    }
}