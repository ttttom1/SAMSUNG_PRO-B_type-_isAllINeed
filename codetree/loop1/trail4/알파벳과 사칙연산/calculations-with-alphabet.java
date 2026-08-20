import java.util.Scanner;
public class Main {
    static String expr;
    static int[] val = new int[6];
    static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        expr = sc.next();
        // Please write your code here.
        findMax(0);

        System.out.println(maxResult);
    }

    static void findMax(int idx) {
        if (idx == 6) {
            maxResult = Math.max(maxResult,calculate());
            return;
        }

        for (int i = 1 ; i <= 4 ; i++) {
            val[idx] = i;
            findMax(idx+1);
        }
    }

    static int calculate() {
        int res = val[expr.charAt(0) - 'a'];

        for(int i = 1; i < expr.length() ;i+= 2) {
            char op = expr.charAt(i);
            int nextVal = val[expr.charAt(i + 1) - 'a'];

            if (op == '+') res += nextVal;
            else if (op == '-') res -= nextVal;
            else if (op == '*') res *= nextVal;
        }
        return res;
    }
}