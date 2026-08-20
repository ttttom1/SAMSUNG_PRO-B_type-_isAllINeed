import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int n;
    static int ans;
    static ArrayList<Integer> seq= new ArrayList<>();

    static boolean isBeautiful() {
        for (int i = 0 ; i< n ; i+= seq.get(i)) {
            if (i + seq.get(i) > n)
                return false;
            for(int j = i ; j < i + seq.get(i);j++) 
                if (seq.get(j) != seq.get(i))
                    return false;
        }
        return true;
    }

    static void countBeautifulSeq(int cnt) {
        if(cnt == n) {
            if (isBeautiful())
                ans++;
            return;
        }
        for(int i = 1;i <= 4;i++) {
            seq.add(i);
            countBeautifulSeq(cnt + 1);
            seq.remove(seq.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // Please write your code here.
        countBeautifulSeq(0);
        System.out.println(ans);
    }
}