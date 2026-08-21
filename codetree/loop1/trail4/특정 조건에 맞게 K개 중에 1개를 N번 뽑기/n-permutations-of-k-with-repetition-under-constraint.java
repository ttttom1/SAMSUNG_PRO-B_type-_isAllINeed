import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    static int k,n;
    static int[] arr;
    static ArrayList<Integer> nums  = new ArrayList<>();

    static void printAnswer() {
        nums.stream().forEach(x-> System.out.print(x + " "));
        System.out.println();
    }

    static boolean isTriple(int i) {
        if(nums.size() <2) return false;
        else if(nums.get(nums.size() - 1) == i && nums.get(nums.size() - 2) == i) {
            return true;
        }
        return false;
    }

    static void permutate(int idx) {
        if(idx ==  n) {
            printAnswer();
            return;
        }

        for (int i = 1;i <= k;i++) {
            if(!isTriple(i)){
                nums.add(i);
                permutate(idx + 1);
                nums.remove(nums.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        // Please write your code here.
        permutate(0);
    }
}