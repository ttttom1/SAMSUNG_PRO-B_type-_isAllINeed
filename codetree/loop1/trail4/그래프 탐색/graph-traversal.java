import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int n, m;
    static int cnt = 0; 
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for(int i = 1 ; i <= n;i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph[from].add(to);
            graph[to].add(from);
        }
        // Please write your code here.
        visited[1] = true;
        dfs(1);

        System.out.print(cnt);
    }

    static void dfs(int currVertex) {
        for (int next:graph[currVertex]) {
            if(!visited[next]) {
                visited[next] = true;
                cnt++;
                dfs(next);
            }
        }
    }
}