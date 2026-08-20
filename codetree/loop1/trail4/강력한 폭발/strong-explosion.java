import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N,maxCnt = 0 ; 
    static int[][] grid;

    static ArrayList<Pair> bombPos = new ArrayList<>();
    static ArrayList<Integer> selectedBombs = new ArrayList<>();;

    static int[][][] bombTypes = {
        {},
        {{-2, 0}, {-1, 0},{ 0, 0},{ 1, 0},{ 2, 0}},
        {{-1, 0}, { 1, 0},{ 0, 0},{ 0,-1},{ 0, 1}},
        {{-1,-1}, {-1, 1},{ 0, 0},{ 1,-1},{ 1, 1}}
    };

    static void simulate() {
        int[][] exploded = new int[N][N];

        for (int i = 0 ; i < bombPos.size();i++) {
            Pair pos = bombPos.get(i);
            int type = selectedBombs.get(i);

            for(int[] offset:bombTypes[type]) {
                int nr = pos.r + offset[0];
                int nc = pos.c + offset[1];

                if (nr >= 0 && nr < N && nc >= 0 && nc  < N){
                    exploded[nr][nc] = 1;
                }
            }
        }
        int count = 0; 
        for (int i = 0; i < N;i++) {
            for(int j = 0 ; j < N;j++) {
                if (exploded[i][j] == 1) count++;
            }
        }

        maxCnt = Math.max(maxCnt, count);
    }

    static void findMaxExplosion(int idx) {
        if (idx == bombPos.size()) {
            simulate();
            return;
        }

        for (int type = 1; type <= 3;type++) {
            selectedBombs.add(type);
            findMaxExplosion(idx + 1);
            selectedBombs.remove(selectedBombs.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1) {
                    bombPos.add(new Pair(i,j));
                }
            }
        }

        findMaxExplosion(0);
        System.out.println(maxCnt);
        // Please write your code here.
    }
}