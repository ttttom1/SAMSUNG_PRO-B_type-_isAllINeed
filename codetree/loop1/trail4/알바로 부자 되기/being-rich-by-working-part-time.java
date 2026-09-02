import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];
        int[] e = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt()-1;
            e[i] = sc.nextInt()-1;
            p[i] = sc.nextInt();
        }
        // Please write your code here.

        int[] dp = new int[n];
        for(int i =0 ; i < n; i++) {
            dp[i] = p[i];
            for(int j = 0; j <  i;j++) {
                if(s[i] >e[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                }
            }
        }

            
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}