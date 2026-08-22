import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static int n, m;
    static ArrayList<Integer> seq = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        // Please write your code here.
        int curMax = -1;
        permu(0,curMax);
    }

    static void permu(int idx, int curMax) {
        if (idx == m) {
            for (int i = 0;i < m;i++) {
                System.out.print(seq.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1 ; i <= n ; i++) {
            if(i > curMax){
                seq.add(i);
                permu(idx + 1, i);
                seq.remove(seq.size() - 1);
            }
        }
    }
}