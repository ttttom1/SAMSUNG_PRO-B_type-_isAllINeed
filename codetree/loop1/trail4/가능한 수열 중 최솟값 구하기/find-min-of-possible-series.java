import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static int n;
    static ArrayList<Integer> sequence = new ArrayList<>();

    static boolean isPossible() {
        int length = sequence.size();

        for (int len = 1; len <= length/2;len++) {
            boolean isSame = true;

            for (int  i = 0 ; i < len;i++) {
                int left = sequence.get(length - 2 * len  +i);
                int right = sequence.get(length - len + i);

                if (left !=  right) {
                    isSame  =  false;
                    break;
                }
            }
            if(isSame) return false;
        }
        return true;
    }
    
    static void findSequence(int cnt) {
        if(!isPossible()) return;

        if (cnt ==  n) {
            for (int num : sequence) {
                System.out.print(num);
            }
            System.out.println();
            System.exit(0);
        }

        for (int i = 4; i <= 6;i++) {
            sequence.add(i);
            findSequence(cnt + 1);
            sequence.remove(sequence.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // Please write your code here.
        findSequence(0);
    
    }
}