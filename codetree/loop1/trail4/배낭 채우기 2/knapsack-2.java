import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> jewls = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for (int i = 0 ; i < N; i++) {
            jewls.add( new ArrayList<>());
            st  = new StringTokenizer(br.readLine().trim());
            for (int  j = 0 ; j < 2; j++) {
                jewls.get(i).add(Integer.parseInt(st.nextToken()));                
            }
        }
        
        // 무게를 인덱스로, 가치를 값으로 가지는  그런
        int maxWeight = 0;
        for (int i = 0; i < N ; i++) {
            maxWeight += jewls.get(i).get(0);
        }
        
        int[] dp = new int[1+M];
        
        for (int i =  0; i < N;i++) {
            int weight = jewls.get(i).get(0);
            int valueOfJ = jewls.get(i).get(1);

            if (weight > M) continue;

            for (int j = weight; j <= M; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight] + valueOfJ);
            }
        }
        int res  =  0;
        for (int i = 1;i  <= M;i++) {
            res = Math.max(res, dp[i]);
        }
        
        System.out.println(res);
    }
}