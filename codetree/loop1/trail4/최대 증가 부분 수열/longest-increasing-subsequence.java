import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        // Please write your code here.
        int[] dp = new int[n];
        for (int i = 0 ; i < n;i++) {
            dp[i] = -1;
        }

        dp[0] = 1;

        for (int i = 1 ; i < n;i++) {
            //해당 인덱스에서 뒤로 갔을깨 자기보다 작은거 나오면 거기 플러스 1이랑 그 전의 것들
            for (int j = i - 1 ; j >= 0 ;j--) { 
                if(arr[i] >arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }    
            if(dp[i] == -1) dp[i] = 1;    
        }
        int res = 0;
        for (int i = 0; i <n;i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}