import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        long[] dp = new long[n + 1];

        dp[0]=1;
        dp[1] = 1;
        
        for (int i = 2; i <=n;i++) {
            for (int j =0;j < i;j++) {
                dp[i] += dp[j] * dp[i -  1 -j];
            }
        }

        System.out.println(dp[n]);
    }
}