import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            
            coin[i] = sc.nextInt();
        
        }
        // Please write your code here.
        int[] dp = new int[m+1];

        dp[0] = 0;
        
        for(int i = 0 ; i< n;i++) {
            if(coin[i] > m) continue;
            dp[coin[i]] = 1;
        }


        for(int  i = 1;  i <= m;i++) {
            for(int j =  0 ; j < n;j++) {
                if (i - coin[j] < 0) continue;
                if (dp[i-coin[j]] ==  0) continue;
                dp[i] = Math.max(dp[i], dp[i-coin[j]]  + 1);
            }
        }
        if(dp[m] ==  0) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[m]);
    }
}