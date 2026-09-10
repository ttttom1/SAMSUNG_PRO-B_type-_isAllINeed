import java.util.*;

class Node implements Comparable<Node> {
    int to;
    int weight;

    Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight,o.weight);
    }
}

public class Main {
    static final int INF = (int) 1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Node>[] graph = new ArrayList[n+1];        
        for(int i = 1 ; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Node(v,w));
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.offer(new Node(1,0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            int curVertex = curr.to;
            int curWeight = curr.weight;

            if (dist[curVertex] < curWeight) {
                continue;
            }
            for(Node next: graph[curVertex]) {
                if(dist[next.to] > dist[curVertex] + next.weight) {
                    dist[next.to] = dist[curVertex] + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        for(int i = 2; i <= n;i++) {
            if(dist[i] == INF) System.out.println(-1);
            else System.out.println(dist[i]);
        }

    }
}