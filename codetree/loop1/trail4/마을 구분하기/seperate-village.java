import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int n,mems;
    static int[][] grid;
    static int townCnt = 0;
    static ArrayList<Integer> memCnts = new ArrayList<>();
    static boolean[][] visited;

    static int[] dr =  {-1,1,0,0};
    static int[] dc =  { 0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
                
        // Please write your code here.
        for (int i = 0 ; i < n;i++) {
            for (int j = 0; j < n;j++) {
                if(grid[i][j] ==1 && !visited[i][j]) {
                    townCnt++;
                    mems = 1;
                    visited[i][j] = true;
                    simulate(i,j);
                    memCnts.add(mems);
                }
            }
        }
        System.out.println(townCnt);
        Collections.sort(memCnts);
        for (int memCnt:memCnts) {
            System.out.println(memCnt);
        }
    }

    static void simulate(int r,int c) {
        for (int i = 0;i < 4;i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!inRange(nr,nc)) continue; 

            if (grid[nr][nc] == 1 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                mems++;
                simulate(nr,nc);
            }
        }
    }

    static boolean inRange(int x,int y){
        return x >=0 && x <n && y >= 0 && y<n;
    }
}