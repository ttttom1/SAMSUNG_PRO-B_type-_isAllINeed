import java.util.Scanner;
public class Main {

    static int n , r, c;
    static int max = Integer.MIN_VALUE;
    static int[][] num;
    static int[][] moveDir;


    static int[][] dirRC = {{-1, 0},{-1, 1},
                            { 0, 1},{ 1, 1},
                            { 1, 0},{ 1,-1},
                            { 0,-1},{-1,-1}};

    static boolean inRange(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < n;
    }

    static void permu(int r, int c,int cnt) {
        max = Math.max(max,cnt);

        int dir = moveDir[r][c]; 
        

        for (int step = 1; step < n;step++) {
            int nr = r + dirRC[dir][0] * step;
            int nc = c + dirRC[dir][1] * step;

            if (!inRange(nr, nc)) break;
            if (num[nr][nc] > num[r][c]) {
                permu(nr,nc,cnt+1);    
            }
            
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        moveDir = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moveDir[i][j] = sc.nextInt() - 1;
            }
        }
        r = sc.nextInt()-1;
        c = sc.nextInt()-1;
        // Please write your code here.

        permu(r,c,0);


        System.out.println(max);

    }
}