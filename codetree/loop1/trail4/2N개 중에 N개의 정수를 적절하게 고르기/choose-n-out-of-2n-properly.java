import java.util.Scanner;

class Group {
    int sum, cnt;
    Group (int sum, int cnt) {
        this.sum = sum;
        this.cnt = cnt;
    }
}

public class Main {
    static int n;
    static int min = Integer.MAX_VALUE;
    static int[] arr;
    static int[] groupA = new int[10];
    static int totalSum = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = sc.nextInt();
            totalSum += arr[i];
        }
        // Please write your code here.
        //일단 인덱스 0 부터 2n -1 까지 를 절반 딱 나눈다.
        separate(0, 0);



        System.out.println(min);
    }

    static void separate(int target,int idx) {
        if( idx == n) {
            int sumA = sum(groupA);
            int sumB = Math.abs(totalSum - sumA);
            int curDiff = Math.abs(sumB - sumA);
            min = Math.min(min, curDiff);
            return;
        }
        if (target == 2*n-1) return;
        groupA[idx] = arr[target];
        separate(target + 1, idx + 1);
        groupA[idx] = 0;


        separate(target+1,idx);
    }
    
    static int sum(int[] nums) {
        int res = 0;
        for (int num :nums) {
            res += num;
        }
        return res;
    }
}