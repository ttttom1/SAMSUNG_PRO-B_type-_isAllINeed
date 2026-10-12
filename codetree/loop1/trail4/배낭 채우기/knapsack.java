import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] w = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        // Please write your code here.

        int[] dp = new int[m + 1];

        for (int i = 0; i < n; i++) {
            int weight = w[i];
            int value = v[i];

            for (int j  = m ; j >= weight; j--) {
                dp[j] = Math.max(dp[j],  dp[j-weight] + value);
            }
        }

        int maxValue = 0;
        for(int j = 0; j <= m; j++) {
            maxValue = Math.max(maxValue, dp[j]);
        }
        System.out.println(maxValue);
    }
}