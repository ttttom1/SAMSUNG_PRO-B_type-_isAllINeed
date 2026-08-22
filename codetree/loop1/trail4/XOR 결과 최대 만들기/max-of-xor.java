import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static int n, m;
    static int max = Integer.MIN_VALUE;
    static ArrayList<Integer> seq = new ArrayList<>();
    static int[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        // Please write your code here.

        //숫자를 고르는 거 재귀로 하고, 그 결과 마다 계산 함수 해서  초댓값 갱신
        findCombination(0, 0);
        //최댓값 출력
        System.out.println(max);
    }

    static void findCombination(int curr_idx,int cnt) {
        if (cnt == m) {
            max = Math.max(max,getXor());
            return;
        }

        if(curr_idx == n) {
            return;
        }

        seq.add(A[curr_idx]);
        findCombination(curr_idx + 1, cnt + 1);
        seq.remove(seq.size() - 1);

        findCombination(curr_idx + 1, cnt);
    }


    static int getXor() {
        int val = 0 ;
        for (int num:seq) {
            val ^= num;
        }
        return val;

    }
}