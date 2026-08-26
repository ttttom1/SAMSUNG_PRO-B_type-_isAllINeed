import java.util.*;
public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int MAX = 1000000;
        int[] step = new int[MAX + 1];
        Arrays.fill(step, -1);

        Queue<Integer> q = new LinkedList<>();

        q.add(n);
        step[n] = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if(curr == 1) {
                System.out.println(step[1]);
                break;
            }

            if(curr - 1 > 1 && step[curr - 1] == -1) {
                step[curr - 1] = step[curr] + 1;
                q.add(curr -1);
            }
            if(curr + 1 < MAX && step[curr+1] == -1) {
                step[curr + 1] = step[curr] + 1;
                q.add(curr+ 1);
            }
            if (curr % 2 == 0 && step[curr / 2] == -1) {
                step[curr / 2] = step[curr] + 1;
                q.add(curr / 2);
            }
            // 4. /3 연산
            if (curr % 3 == 0 && step[curr / 3] == -1) {
                step[curr / 3] = step[curr] + 1;
                q.add(curr / 3);
            }
        }
    }
}