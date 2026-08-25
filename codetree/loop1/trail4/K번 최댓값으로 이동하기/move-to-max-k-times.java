import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;


class Pair implements Comparable<Pair>{
    int r, c, num;
    Pair (int r, int c, int num) {
        this.r =r;
        this.c =c;
        this.num = num;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.num != o.num) {
            return o.num - this.num;
        } else if (this.r != o.r) {
            return this.r - o.r;
        } else {
            return this.c- o.c;
        }
    }
}

public class Main {
    static int n, k;
    static int[][] grid;
    static boolean[][] visited;
    static int currR,currC;
    static int[] dr = new int[] {-1,1,0,0};
    static int[] dc = new int[] {0,0,-1,1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][n];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        currR = sc.nextInt()-1;
        currC = sc.nextInt()-1;

        for (int i = 0; i <k ;i++) {
            boolean isMoved = move();
            if (!isMoved) break;
        }

        System.out.println((currR +1) + " " +(currC  + 1));
    }

    static boolean move() {
        visited = new boolean[n][n];
        Queue<Pair> q = new LinkedList<>();
        List<Pair> candidates = new  ArrayList<>();

        int startVal = grid[currR][currC];
        q.offer(new Pair(currR,currC,startVal));
        visited[currR][currC]  = true;


        while(!q.isEmpty()) {
            Pair curr = q.poll();

            for(int i = 0 ; i < 4;i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                if(canGo(nr,nc,startVal)) {
                    visited[nr][nc] = true;
                    Pair next = new Pair(nr, nc, grid[nr][nc]);
                    q.offer(next);
                    candidates.add(next);
                }
            }
        }
        if (candidates.isEmpty()) {
            return false;
        }
        Collections.sort(candidates);

        Pair best = candidates.get(0);
        currR = best.r;
        currC = best.c;
        return true;
    }

    static boolean canGo(int r, int  c, int cNum) {
        return inRange(r,c) && !visited[r][c] && grid[r][c] < cNum ;
    }
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}