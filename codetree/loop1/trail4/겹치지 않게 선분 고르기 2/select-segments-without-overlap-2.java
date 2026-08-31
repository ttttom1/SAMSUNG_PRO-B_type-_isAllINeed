import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }
        // Please write your code here.

        Arrays.sort(segments,(a,b) -> Integer.compare(a[0],b[0]));

        int[] dp = new int[n+1];
        int maxCount = 0;

        for(int i = 0 ; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j <i;j++){
                if(segments[i][0] > segments[j][1]) {
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            maxCount = Math.max(maxCount,dp[i]);
        }

        System.out.println(maxCount);
    }
}