import java.util.Scanner;
public class Main {

    static int n, m ;
    static int[][] grid;
    static boolean[][] visited;
    static boolean answer = false;

    static int[] dr = {1,0};
    static int[] dc = {0,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        dfs(0,0);
        if (answer) System.out.print(1);
        else System.out.print(0);
    }
    
    static void dfs(int x,int y) {
        if (x == n-1 && y == m-1) {
            answer = true;
            return;
        }
        for(int i = 0 ; i < 2; i++) {
            int nx = x + dr[i];
            int ny = y + dc[i];

            if(inRange(nx,ny)) {
            if(!visited[nx][ny] && grid[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    dfs(nx,ny);
                }
            }
        }
    }

    static boolean inRange(int x, int y) {


        return(x >= 0 && x < n && y >= 0 && y < m);
    }
}