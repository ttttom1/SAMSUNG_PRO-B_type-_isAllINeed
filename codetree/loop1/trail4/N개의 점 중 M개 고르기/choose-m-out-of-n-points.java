import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int r,c;
    Pair(int r,int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, m;
    static Pair[] points;
    static ArrayList<Pair> selected = new ArrayList<>();
    static int minMaxDist =Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        points = new Pair[n];

        for (int i = 0; i < n; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            points[i] = new Pair(r,c);
        }
        // Please write your code here.

        permu(0,0);
        System.out.print(minMaxDist);
    }

    static void permu(int idx, int target) {
        if(idx == m) {
            int currentMaxDist = getMaxDist();
            minMaxDist = Math.min(minMaxDist,currentMaxDist);
            
            return;
        }

        if (target == n) return;

        selected.add(points[target]);
        permu(idx + 1, target + 1);
        selected.remove(selected.size() - 1);

        permu(idx, target + 1);

    }

    static int getMaxDist() {
        int max = 0; 
        for (int i= 0; i < selected.size();i++) {
            for (int j = i+1;j < selected.size();j++) {
                Pair p1 = selected.get(i);
                Pair p2 = selected.get(j);
                int dist = (int)Math.pow(p1.r - p2.r,2) + (int)Math.pow(p1.c - p2.c,2);
                max = Math.max(max,dist);
            }
        }
        return max;
    }
}