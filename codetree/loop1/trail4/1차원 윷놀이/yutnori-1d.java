import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static int n,m,k,maxScore = -9999;

    public static ArrayList<Integer> mals = new ArrayList<>();
    public static int[] nums;

    static int[] malScores;

    public static int calcScore() {
        for (int i = 0;i <k;i++) {
            malScores[i] = 0;
        }

        for (int i= 0 ; i < n;i++) {

            malScores[mals.get(i) - 1] += nums[i];
        }
        //LOG
        // for (int i : mals){
        //     System.out.println(i);
        // }
        // for (int i = 0; i < malScores.length;i++) {
        //     System.out.print(malScores[i] + "->");
        // }
        // System.out.println("//////");

        int count = 0; 
        for (int i = 0; i < malScores.length;i++) {
            if(malScores[i] >= m -1) count++;
        }
        return count;
    }

    public static void permu(int idx) {

        if (maxScore == k) return;
        
        if (idx == n) { 
            maxScore = Math.max(maxScore,calcScore());
            
            return;

        }

        for (int i = 1 ; i <= k;i++) {
            mals.add(i);
            permu(idx + 1);
            mals.remove(mals.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        nums = new int[n];
        malScores = new int[k];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // Please write your code here.
        // 퍼뮤테이션
        permu(0);
        //출력
        System.out.print(maxScore);
    }
}