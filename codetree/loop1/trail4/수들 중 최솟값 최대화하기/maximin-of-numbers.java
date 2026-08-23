import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int n, maxMin  = 0;
    static int[][] grid;
    static boolean[] visited;
    static ArrayList<Integer> seq = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        simulate(1);
        System.out.println(maxMin);
    }

    static void simulate(int idx) { 
        if (idx == n + 1) {
            maxMin = Math.max(maxMin, getMin());
            return;
        }


        for(int i = 1;i <= n ;i++) {
            if(visited[i]) continue;

            seq.add(i);
            visited[i] = true;
            simulate(idx + 1);
            seq.remove(seq.size() - 1);
            visited[i] = false;
        }
    }

    static int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < n;i++) {
            min = Math.min(min,grid[i][seq.get(i) - 1]);
        }

        return min;
    }
}