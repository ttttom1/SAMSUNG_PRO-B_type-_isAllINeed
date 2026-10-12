import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        boolean[] dp = new boolean[M + 1];
        dp[0] = true;

        
        for (int i = 0; i < n ; i++) {
            int val = arr[i];
            for ( int j  = M; j >= val ; j--) {
                if (dp[j - val]) {
                    dp[j] = true;
                }
            }
        }


        if ( dp[M] ) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}