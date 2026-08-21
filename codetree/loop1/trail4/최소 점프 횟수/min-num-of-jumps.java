import java.util.Scanner;

public class Main {

    static int n;
    static int minCnt = Integer.MAX_VALUE;
    static int[] arr;

    static void permu(int idx, int cnt) {
        if (idx == n - 1) {
            minCnt = Math.min(minCnt,cnt);
            return;
        } 

        if(cnt >= minCnt) return;

        for (int i = 1; i <= arr[idx];i++) {
            if(idx + i < n) {
                permu(idx + i, cnt + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        permu(0, 0);

        if(minCnt == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCnt);
        }
    }
}