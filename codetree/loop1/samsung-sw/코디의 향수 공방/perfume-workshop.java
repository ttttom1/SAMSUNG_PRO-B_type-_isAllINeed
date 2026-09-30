import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] sArr = new int[1200];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int Q = sc.nextInt();

        for (int q = 1; q <= Q; q++) {
            int opt = sc.nextInt();

            switch(opt) {
                case 1:
                    ready();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    abandon();
                    break;
                case 4:
                    blending();
                    break;
                case 5:
                    compose();
                    break;
            }
        }
        
        sc.close(); // main 함수 내부로 이동
    }

    static void ready() {
        N = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            sArr[i] = sc.nextInt();
        }
    }

    static void add() {
        sArr[++N] = sc.nextInt();
    }

    static void abandon() {
        int targetIdx = sc.nextInt();
        
        if (targetIdx < 1 || targetIdx > N || sArr[targetIdx] == 0) {
            System.out.println(-1);
            return; // 1. 세미콜론 ; 추가
        }

        System.out.println(sArr[targetIdx]);
        sArr[targetIdx] = 0;
    }

    static void blending() {
        int K = sc.nextInt();
        
        int[] dp = new int[K + 1];
        Arrays.fill(dp, 10000);
        dp[0] = 0; // 2. dp[0] = 0 로 수정 (합 0을 만드는 동전 개수는 0개)

        for (int i = 1; i <= N; i++) { // 3. 1부터 N까지 순회 (대문자 N)
            int val = sArr[i];
            if (val == 0) continue;
            
            for (int j = val; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - val] + 1);
            }
        }
        
        int ans = dp[K] >= 10000 ? -1 : dp[K];
        System.out.println(ans);
    }

    // static void compose() {
    //     int K = sc.nextInt();
        
    //     List<Integer> validList = new ArrayList<>();
    //     for (int i = 1; i <= N; i++) {
    //         if (sArr[i] > 0) { // 4. sArr[1] -> sArr[i] 오타 수정
    //             validList.add(sArr[i]);
    //         }
    //     }
        
    //     int size = validList.size();
    //     long count = 0;

    //     for (int i = 0; i < size; i++) {
    //         for (int j = 0; j < size; j++) {
    //             for (int k = 0; k < size; k++) {
    //                 if (validList.get(i) + validList.get(j) + validList.get(k) >= K) {
    //                     count++;
    //                 }
    //             }
    //         }
    //     }

    //     System.out.println(count);
    // }

    static void compose() {
    int K = sc.nextInt();
    
    // 향도별 개수 카운팅 (향도 범위가 3000 이하인 경우)
    int MAX_S = 3000;
    long[] cnt = new long[MAX_S + 1];
    
    for (int i = 1; i <= N; i++) {
        if (sArr[i] > 0 && sArr[i] <= MAX_S) {
            cnt[sArr[i]]++;
        }
    }

    // C 향도 개수의 우측 누적합 (suffixSum[x] = 향도가 x 이상인 향료의 총 개수)
    long[] suffixSum = new long[MAX_S + 2];
    for (int i = MAX_S; i >= 1; i--) {
        suffixSum[i] = suffixSum[i + 1] + cnt[i];
    }

    long totalCount = 0;

    // A(탑), B(미들) 2중 루프 탐색
    for (int a = 1; a <= MAX_S; a++) {
        if (cnt[a] == 0) continue;

        for (int b = 1; b <= MAX_S; b++) {
            if (cnt[b] == 0) continue;

            // K - a - b 이상이어야 하는 최소 C 값
            int minC = K - a - b;
            
            if (minC <= 1) {
                // minC가 1 이하이면 1 이상인 모든 C 향료 사용 가능
                totalCount += cnt[a] * cnt[b] * suffixSum[1];
            } else if (minC <= MAX_S) {
                // minC 이상인 C 향료들의 개수 합 곱해주기
                totalCount += cnt[a] * cnt[b] * suffixSum[minC];
            }
        }
    }

    System.out.println(totalCount);
}
}