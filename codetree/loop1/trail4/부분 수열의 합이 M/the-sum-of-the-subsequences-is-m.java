import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] dp = new int[m + 1];
        // 최소값을 구해야 하므로 큰 값으로 초기화 (Overflow 방지를 위해 적절한 큰 값 선언)
        Arrays.fill(dp, 10001); 
        dp[0] = 0; // 합이 0일 때 필요한 원소 개수는 0개

        for (int i = 0; i < n; i++) {
            int num = arr[i];
            // 중복 방지를 위해 m부터 num까지 거꾸로 순회
            for (int j = m; j >= num; j--) {
                if (dp[j - num] != 10001) {
                    dp[j] = Math.min(dp[j], dp[j - num] + 1);
                }
            }
        }

        // 결과 출력 (만들 수 없는 경우 -1)
        if (dp[m] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[m]);
        }
    }
}