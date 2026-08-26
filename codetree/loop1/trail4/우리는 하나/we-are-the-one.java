import java.util.*;

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, k, u, d;
    static int[][] grid;
    static ArrayList<Pair> cities = new ArrayList<>();
    static ArrayList<Pair> selected = new ArrayList<>();
    static int maxCities = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 1. DFS 백트래킹으로 K개의 도시 고르기
    static void selectCities(int idx, int count) {
        if (count == k) {
            // K개가 선택되면 BFS 실행 후 최댓값 갱신
            maxCities = Math.max(maxCities, bfs());
            return;
        }

        for (int i = idx; i < cities.size(); i++) {
            selected.add(cities.get(i));
            selectCities(i + 1, count + 1);
            selected.remove(selected.size() - 1); // 백트래킹 복구
        }
    }

    // 2. 선택된 K개의 도시에서 출발하는 BFS
    static int bfs() {
        boolean[][] visited = new boolean[n][n];
        Queue<Pair> q = new ArrayDeque<>();
        int count = 0;

        // 선택된 K개의 시작점을 한 번에 큐에 삽입
        for (Pair p : selected) {
            q.offer(p);
            visited[p.r][p.c] = true;
            count++;
        }

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = curr.r + dr[dir];
                int nc = curr.c + dc[dir];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int diff = Math.abs(grid[curr.r][curr.c] - grid[nr][nc]);
                    // 높이 차이가 U 이상 D 이하인 경우만 이동
                    if (diff >= u && diff <= d) {
                        visited[nr][nc] = true;
                        q.offer(new Pair(nr, nc));
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                cities.add(new Pair(i, j)); // 전체 도시 좌표 저장
            }
        }

        selectCities(0, 0); // 조합 탐색 시작
        System.out.println(maxCities);
    }
}