import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int n;
    static boolean[] visited;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // Please write your code here.
        visited = new boolean[n + 1];

        permu(1);
    }

    static void permu(int numCnt) {
        if(numCnt == n + 1) {
            printAnswer();
            return;
        }

        for (int i = n; i >= 1; i--) {
            if(visited[i]) {
                continue;
            }

            answer.add(i);
            visited[i] = true;

            permu(numCnt + 1);

            answer.remove(answer.size() - 1);
            visited[i] = false;
        }
    }

    static void printAnswer() {
        for (int num : answer) {
            System.out.print(num + " ");
        }
        System.out.println();

        return;
    }
}