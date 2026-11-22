import java.util.Scanner;
import java.util.Arrays;
public class Main {

    static int N = 0;
    static int[] dp;

    static void initialize() {
        dp = new int[N+1]; //dp는 길이가 얼마일때 최대 가치
        dp[0] = 0;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] profit = new int[N+1];
        for (int i = 1; i <= N; i++) {
            profit[i] = sc.nextInt();
        }
        // Please write your code here.
        initialize();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i - j < 0) {
                    continue;
                }
                dp[i] = Math.max(dp[i],dp[i-j] + profit[j]);


                //debug
                
            }
        }

        int res = dp[N];
        
        System.out.println(res);
    }
}