import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] coin = new int[n];
        for (int i = 0; i < n; i++)
            coin[i] = sc.nextInt();
        // Please write your code here.

        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]  = 0;
        for(int i =  1; i <=m;i++) {
            for(int j= 0;j < n;j++) {
                int currCoin = coin[j];
                if(i - currCoin <0 || dp[i-currCoin] == Integer.MAX_VALUE)  continue;
                
                dp[i]  =  Math.min(dp[i], (dp[i-currCoin] + 1));
            }
        }
        int res =0;
        if(dp[m] == Integer.MAX_VALUE) res = -1;
        else res  = dp[m];
        System.out.println(res);
    }
}