import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    static int n, minCostSum = Integer.MAX_VALUE;
    static ArrayList<Integer> route = new ArrayList<>();
    static int[][] cost;
    static boolean[] visited ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][n];
        visited = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        simulate(1);
        System.out.print(minCostSum);
    }

    static void simulate(int idx) {
        if(idx == n) {//총 n-1 개를 뽑아야해.
            minCostSum = Math.min(minCostSum,getCostSum());
            return;
        }

        for(int i = 2; i <= n;i++) {
            if (visited[i]) continue;

            route.add(i);
            visited[i] = true;
            simulate(idx + 1);
            route.remove(route.size() -1);
            visited[i] = false;
        }
    }

    static int getCostSum() {
        int sum = 0;
        int curS = 0;
        for (int i : route){ // 2부터 n 까지
            if (cost[curS][i - 1] == 0) return Integer.MAX_VALUE;
            sum += cost[curS][i - 1];
            curS =  i-1;
        }
        int toHomeCost = cost[route.get(route.size() - 1) - 1][0];
        if (toHomeCost == 0) return Integer.MAX_VALUE;
            sum += toHomeCost;
        return sum;
    }
}