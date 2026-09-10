
import java.util.*;
import java.io.*;

public class Main {

    static int N, Q;
    static int[][] commands = new int[55][4];
    static int[][] grid = new int[15][15];
    
    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        
        for(int i = 0 ; i < Q;i++) {
            for(int j = 0 ; j < 4; j++) {
                commands[i][j] = sc.nextInt();
            }
        }
    }
    /*
     * Simulate
     */
    static void simulate() {
        for(int q = 0; q < Q ;q++) {
            putMC(q);

            moveMC();
            
            markResult();
        }
    }
    
    static class MC implements Comparable<MC> {
        int group;
        int memCnt;
        LinkedList<Pair> member;
        
        MC(int group,int memCnt, LinkedList<Pair> member) {
            this.group = group;
            this.memCnt = memCnt;
            this.member = member;
        }
        
        
        @Override
        public int compareTo(MC o) {
            if (this.memCnt != o.memCnt) {
                return Integer.compare(o.memCnt, this.memCnt);
            } else {
                return Integer.compare(this.group,o.group);
            }
        }
    }
    
    static PriorityQueue<MC> pq = new PriorityQueue<>(); 
    /*
     * markResult() start 
     */
    static Set<Pair> nearSet = new HashSet<>();
    static void markResult() {
        // 전체 돌면서, 인접 그룹 체크해서 MC 셋에 넣고,
        nearSet.clear();
        
        for(int i = 0 ; i < N;i++) {
            for(int j = 0; j < N-1;j++) {
                int nx = i ;
                int ny = j + 1;
                if(grid[i][j] != 0 && grid[nx][ny] != 0 &&
                    grid[i][j] != grid[nx][ny]) {
                    int biggerNum = Math.max(grid[i][j], grid[nx][ny]);
                    int smallerNum = Math.min(grid[i][j], grid[nx][ny]);
                    
                    nearSet.add(new Pair(biggerNum,smallerNum));
                }
            }
        }
        for(int j = 0 ; j < N;j++) {
            for(int i = 0; i < N-1;i++) {
                int nx = i + 1 ;
                int ny = j;
                if(grid[i][j] != 0 && grid[nx][ny] != 0 &&
                    grid[i][j] != grid[nx][ny]) {
                    int biggerNum = Math.max(grid[i][j], grid[nx][ny]);
                    int smallerNum = Math.min(grid[i][j], grid[nx][ny]);
                    
                    nearSet.add(new Pair(biggerNum,smallerNum));
                }
            }
        }
        //하나씩 뽑아서 곱한다
        int sum = 0;
        for(Pair point: nearSet) {
            int biggerGCnt = groupInfo.get(point.x).memCnt;
            int smallerGCnt = groupInfo.get(point.y).memCnt;
            
            sum += biggerGCnt * smallerGCnt;
        }
        System.out.println(sum);
    }
    /*
     * moveMC() start
     */
    static int[][] newGrid;
    static Map<Integer,MC> groupInfo;
    static void moveMC() {
        //현재 존재하는 그룹을 다 넣어서 순서를 정함 그 후 하나씩 빼서, 새 용기에 담는다.
        for(int i = 1; i <= Q ;i++) {
            if(groupCnt[i] > 0) {
                LinkedList<Pair> member = MCBlocks(i);
                int gCnt = member.size();
                pq.offer(new MC(i,gCnt,member));
            }
        }
        newGrid = new int[15][15];
        groupInfo = new HashMap<>();
        //하나씩 빼서
        while(!pq.isEmpty()) {
            MC currMC = pq.poll();
            
            //새 용기에 담는다.
            boolean flag = false;
            for(int i = 0; i < N && !flag;i++) {
                for(int j = 0 ; j < N && !flag;j++) {
                    if(canPut(i,j,currMC)) {
                        for(int r = 0; r < currMC.memCnt;r++) {
                            Pair currPoint = currMC.member.get(r);
                            newGrid[i + currPoint.x][j + currPoint.y] = currMC.group; 
                            
                        }
                        groupInfo.put(currMC.group,currMC);
                        flag = true;
                    }
                }
            }
        }
        grid = newGrid;
    }
    
    static boolean canPut(int x, int y, MC mc) {
        for(Pair point: mc.member) {
            int nx = x + point.x;
            int ny = y + point.y;
            if(nx >= N || ny >= N) return false;
            if(newGrid[nx][ny] != 0) return false;
        }
        return true;
    }
    
    static LinkedList<Pair> MCBlocks(int group) {
        int cnt = 0;
        LinkedList<Pair> member = new LinkedList<>();
        int minX = N;
        int minY = N;
        for(int i = 0 ; i < N;i++) {
            for(int j = 0 ; j < N;j++) {
                if(grid[i][j] == group) {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                }
            }
        }
        
        for(int i = minX ; i< N;i++) {
            for(int j = minY ; j< N;j++) {
                if(grid[i][j] == group) {
                    int offsetX = i - minX;
                    int offsetY = j - minY;
                    member.add(new Pair(offsetX, offsetY));
                }
            }
        }
        return member;
    }

    /*
     * moveMC() End
     */
    /*
     * putMC() start
     */
    static void putMC(int q) {
        //해당 공간에 턴 (q는 0 ~ Q-1)에 q+1 찍기
        int sx = commands[q][0];
        int sy = commands[q][1];
        int ex = commands[q][2];
        int ey = commands[q][3];
        
        for (int i = sx ; i < ex;i++) {
            for(int j = sy; j < ey;j++) {
                grid[i][j] = q+1;
            }
        }
        
        //두개 이상으로 쪼개진거 제거
        gc();
    }
    
    static int[] groupCnt = new int[55]; 
    
    static void gc() {
        //전체 돌아서 그룹 쪼개진 거 잇는지 스캔
        // bfs로 그룹 하나당 그룹수 체크 
        Arrays.fill(groupCnt, 0);
        visited = new boolean[N][N];
        for(int i = 0 ; i < N;i++) {
            for(int j = 0 ; j < N;j++) {
                if(grid[i][j] != 0 && !visited[i][j]) {
                    groupCnt[grid[i][j]] += 1;
                    findSplited(i,j);    //그룹을 찾아서 넣는다.그룹 번호를 
                }
            }
        }
        
        //그룹 두개이상이면, 지운다.
        for(int i = 1; i <= Q  ;i++) {
            if(groupCnt[i] > 1) {
                removeMC(i);
                groupCnt[i] = 0;
            }
        }
    }
    
    static void removeMC(int g) {
        for(int i = 0 ; i < N;i++) {
            for(int j = 0 ; j < N;j++) {
                if(grid[i][j] == g) {
                    grid[i][j] = 0;
                }
            }
        }
    }
    
    static int[] dx = {0, 0,-1,1};    //x,y 기준 상하좌우
    static int[] dy = {1,-1,0,0}; 
    
    static class Pair {
        int x, y;
        Pair(int x,int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if(this == o ) return true;
            if(!(o instanceof Pair) ) return false;
            Pair p = (Pair) o;
            return x == p.x && y == p.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
    
    static Queue<Pair> q = new ArrayDeque<>();
    static boolean[][] visited;
    
    static void findSplited(int x,int y) {
        q.offer(new Pair(x,y));
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int cx = curr.x;
            int cy = curr.y;
            
            for(int d = 0 ;d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                
                if(nx <0 || nx >= N || ny <0 || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(grid[x][y] != grid[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.offer(new Pair(nx,ny));
            }
        }
        
    }
    /*
     * putMC() End
     */
    /*
     * debug
     */
    static void printGrid() {
        for (int i = 0 ; i < 15;i++) {
            for(int j = 0 ; j < 15;j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
    public static void main(String[] args) {
        input();

        simulate();
        
        
    }

}
