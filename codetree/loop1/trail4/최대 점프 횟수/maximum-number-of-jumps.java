import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] dp = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        for(int i= 0 ;i <n ;i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        
        dp[0] = 0;

        for(int i = 1; i< n;i++) {
            for(int j = 0 ; j<i ; j++){
                if(dp[j] == Integer.MIN_VALUE) {
                    continue;
                }
                if(j + arr[j] >= i) {
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }

        
        int maxJumps = 0;
        for (int i = 0; i < n; i++) {
            maxJumps = Math.max(maxJumps, dp[i]);
        }

        System.out.println(maxJumps);
    }
}