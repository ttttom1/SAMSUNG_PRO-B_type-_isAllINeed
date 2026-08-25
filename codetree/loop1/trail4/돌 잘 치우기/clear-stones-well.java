import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

class Pair {
    int r, c;
    Pair (int r, int c){
        this.r = r;
        this.c =c;
    }
}

public class Main {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static int n,k,m;
    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q = new ArrayDeque<>();
    static ArrayList<Pair> rocks = new ArrayList<>();
    static ArrayList<Pair> selected = new ArrayList<>();
    static int max = 0, cnt;
    static int[][] startPoints;
    static boolean[] checkSelected;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        k = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1) rocks.add(new Pair(i,j));
            }
        }

        startPoints = new int[k][2];
        for (int i = 0; i < k; i++) {
            startPoints[i][0] = sc.nextInt()-1;
            startPoints[i][1] = sc.nextInt()-1;
        }
        
        // 저 돌들 개수  조합 m 구해서 반복
        checkSelected = new boolean[rocks.size()];
        permu(0,0);

        System.out.println(max);
    }

    static void permu(int idx , int start) {
        if(idx == m) {
            removeAndCount();
            return;
        }

        for (int i = start; i < rocks.size();i++) {
            selected.add(rocks.get(i));
            permu(idx + 1, i+1);
            selected.remove(selected.size() - 1);
        }
    }

    static void removeAndCount() {
        for (Pair rock:selected) {
            grid[rock.r][rock.c] = 0;
        }
        cnt = 0;
        visited = new boolean[n][n];
        count();
        max = Math.max(max,cnt);
        for (Pair rock:selected) {
            grid[rock.r][rock.c] = 1;
        }

    }

    static void count() {
        for (int i =0;i < k; i++) {
            q.offer(new Pair(startPoints[i][0],startPoints[i][1]));
            visited[startPoints[i][0]][startPoints[i][1]] = true;
            cnt++;
        }

        while(!q.isEmpty()) {
            Pair curr = q.poll();
            for (int d = 0 ; d < 4;d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if (canGo(nr,nc)) {
                    visited[nr][nc] = true;
                    cnt++;
                    q.offer(new  Pair(nr,nc));
                }
            }
        }
    }



    static boolean inRange(int r,int c) {
        return r >= 0 && r < n && c  >= 0 && c <n;
    }

    static boolean canGo(int r, int c) {
        return  inRange(r,c) && !visited[r][c] && grid[r][c] == 0; 
    } 
}