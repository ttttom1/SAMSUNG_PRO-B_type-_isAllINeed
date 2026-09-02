import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[][] dp = new int[n][2];
        
        for(int i = 0 ; i < n;i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for(int j = 0; j < i;j++) {
                if(arr[j] < arr[i]) {
                    dp[i][0] = Math.max(dp[i][0],dp[j][0] + 1);
                }
                if (arr[j]  > arr[i]) {
                    dp[i][1] = Math.max(dp[i][1], Math.max(dp[j][0],dp[j][1]) + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0;i < n; i++) {
            res = Math.max(res, Math.max(dp[i][0],dp[i][1]));
        }
        System.out.println(res);
    }

}