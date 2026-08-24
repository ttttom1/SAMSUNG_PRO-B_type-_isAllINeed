import java.util.Scanner;

public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = { 0, 0,-1, 1};

    static int n;
    static int[][] grid;
    static int maxBlocks;
    
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];
        int bombCnt  = 0;
        int  max =  -1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        // Please write your code here.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]){
                    visited[i][j] = true;
                    
                    int blockSize = simulate(i,j,grid[i][j]);

                    if (blockSize >= 4) {
                        bombCnt++;
                    }
                    max = Math.max(max, blockSize);
                }
                
            }
        }

        
        System.out.println(bombCnt + " " + max);
    }

    static int simulate(int r,int c, int comp) {
        int count = 1;
        for (int i = 0; i< 4;i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!inRange(nr,nc)) continue;
            
            if(!visited[nr][nc] && grid[nr][nc] == comp) {
                visited[nr][nc] = true;
                
                count += simulate(nr,nc, comp);
            }
        }
        return count;
    }

        static boolean inRange(int r,int c) {
        return r>= 0 && r < n && c >= 0 && c < n;
    }
}