import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int n, maxSum;
    static int[][] grid;
    static boolean[] visited;
    static ArrayList<Integer> indexs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n+1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        simulate(1);

        System.out.println(maxSum);
    }

    static void simulate(int idx) {
        if(idx == n + 1) {
            maxSum = Math.max(maxSum, getSum());
            return;
        }

        for(int i =1 ; i <= n;i++) {
            if(visited[i]) continue;

            indexs.add(i);
            visited[i] = true;
            simulate(idx + 1);
            visited[i] = false;
            indexs.remove(indexs.size() -1);
        }
    }

    static int getSum() {
        int sum = 0 ; 
        for (int i = 0 ; i < n;i++) {
            sum += grid[i][indexs.get(i)-1];
        }
        return sum;
    }

}