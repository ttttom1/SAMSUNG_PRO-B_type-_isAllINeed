import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static int n, max;
    static int[][] segments;
    static ArrayList<Integer> selectedSegments = new ArrayList<>();

    // 현재 선택된 선분들과 새로 추가할 선분(idx)이 겹치는지 검사하는 함수 (별도 분리)
    static boolean isPossible(int idx) {
        for (int segIdx : selectedSegments) {
            int l1 = segments[segIdx][0], r1 = segments[segIdx][1];
            int l2 = segments[idx][0], r2 = segments[idx][1];

            // 두 선분이 겹치는 조건: !(한 선분이 다른 선분보다 완전히 왼쪽에 있거나 오른쪽에 있음)
            // 문제 조건상 끝점을 공유해도 겹치는 것으로 간주
            if (!(r1 < l2 || r2 < l1)) {
                return false; // 겹치면 추가 불가
            }
        }
        return true; // 겹치는 선분이 없으면 추가 가능
    }

    // start: 현재 고려 중인 선분의 인덱스 (0 ~ n-1)
    static void findPermutation(int start) {
        // [수정 1] 재귀 종료 조건 추가 (모든 선분을 다 검사했을 때)
        if (start == n) {
            max = Math.max(max, selectedSegments.size()); // 최댓값 갱신
            return;
        }

        // [수정 2] start번째 선분을 '선택하는' 경우 (겹치지 않을 때만)
        if (isPossible(start)) {
            selectedSegments.add(start);
            findPermutation(start + 1);
            selectedSegments.remove(selectedSegments.size() - 1); // 백트래킹 (원상복구)
        }

        // [수정 3] start번째 선분을 '선택하지 않고 넘어가는' 경우
        findPermutation(start + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        segments = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            // [수정 4] 입력값 -1 제거 (좌표 비교만 정확히 이루어지면 상관없음)
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }

        findPermutation(0);
        System.out.println(max);
    }
}