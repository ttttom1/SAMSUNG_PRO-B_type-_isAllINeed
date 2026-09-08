import java.util.*;
import java.io.*;
import java.util.Collections;

public class Main {

    static final int MAX = 50 + 5;

    static final int TMINT_CHOCO_MILK = 7;
    static final int TMINT_CHOCO = 3;
    static final int TMINT_MILK = 5;
    static final int CHOCO_MILK = 6;
    static final int MILK = 4;
    static final int CHOCO = 2;
    static final int TMINT = 1;



    static int N, T;
    static int[][] B = new int[MAX][MAX];
    static char[][] F = new char[MAX][MAX];
    
    static Student[][] students; 

    static class Student implements Comparable<Student> {
        int food;
        int believe;
        
        boolean isLeader;
        boolean isProtected;
        
        int r;
        int c;
        
        Student(int food,int believe, int r,int  c) {
            this.food = food;
            this.believe = believe;
            this.r =  r;
            this.c = c;
            
            isLeader = false;
            isProtected = false;
        }
        
        @Override
        public int compareTo(Student o) {
            if (this.believe == o.believe && this.r == o.r) {
                if(this.c < o.c) {
                    return -1;
                } else {
                    return 1;
                }
            }
            else if (this.believe  == o.believe) {
                if(this.r < o.r) {
                    return -1;
                } else {
                    return 1;
                }
            } else  if (this.believe > o.believe) {
                return -1;
            }else {
                return 1;
            }
            
        }
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();
        
        for(int i= 0; i < N;i++) {
            String line = sc.next();
            for(int j = 0 ; j < N;j++) {
                F[i][j] = line.charAt(j);
            }
        }
        
        for(int i= 0; i < N;i++) {    
            for(int j = 0 ; j < N;j++) {
                B[i][j] = sc.nextInt();
            }
        }
        
        students = new Student[N][N];
        for (int i = 0 ; i < N;i++) {
            for(int j = 0 ; j < N;j++) {
                int tFood = (F[i][j] == 'T') ? TMINT : (F[i][j]=='C')? CHOCO : MILK; 
                students[i][j] = new Student(tFood,B[i][j],i,j);
            }
        }
    }
    
   /*
    * 
    */
    static void studentsDebug() {
        for(int i= 0; i < N;i++) {    
            for(int j = 0 ; j < N;j++) {
                System.out.print("(" + students[i][j].food + ", "  +
                    students[i][j].believe + ")");
            }
            System.out.println();
        }
    }
    
    
    static void morning() {
        for(int i= 0; i < N;i++) {    
            for(int j = 0 ; j < N;j++) {
                students[i][j].believe += 1;
            }
        }
    }
    /*
     * 2. 점심 시간
        bfs로 그룹 찾아서, 그거를  리스트에 넣어서, 리스트 정렬 숫서 student compareTo만들어야하나? 
     */
    static boolean[][] visited;
    static ArrayDeque<Pair> q = new ArrayDeque<>();
    
    static class Pair {
        int r ,c;
        Pair(int r,int c) {
            this.r =  r;
            this.c = c;            
        }
    }
    
    static List<Student> candidates = new ArrayList<>();
    
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    static void bfs(int r, int c) {
        q.offerLast(new Pair(r,c));
        visited[r][c]  = true;
        candidates.add(students[r][c]);
        
        while(!q.isEmpty()) {
            Pair curr = q.pollFirst();
            int cr = curr.r;
            int cc = curr.c;
            
            for(int d = 0 ; d< 4;d++) {
                int nr = cr  + dr[d];
                int nc = cc + dc[d];
                
                if(nr <0 || nr >= N || nc < 0 || nc >= N) continue;
                if(visited[nr][nc]) continue;
                if(students[cr][cc].food == students[nr][nc].food) {
                    visited[nr][nc] = true;
                    candidates.add(students[nr][nc]);
                    q.offerLast(new Pair(nr,nc));
                }
            }
        }
        
        //그룹내 리더 선출, 후보 비움 다음 그룹을 위해.
        Collections.sort(candidates);
//        printCandidates();
        candidates.get(0).isLeader = true;
        
        // 리더에게 신앙심 하나씩 이동
        for(Student s : candidates) {
            if(s.isLeader) {
                s.believe += candidates.size() - 1;    
            }  else {
                s.believe -= 1;
            }            
        }
        candidates.clear();
    }
    /*
     * 
     */
    static void printCandidates() {
        for(Student s : candidates) {
            System.out.print("(" + s.r + ", "+ s.c +")");
        }
        System.out.println(" /// ");
    }
    
    static void lunch() {       
        visited = new boolean[N][N];
    
        for(int i= 0; i < N;i++) {    
            for(int j = 0 ; j < N;j++) {
                if(!visited[i][j]) {
                    bfs(i,j);
                }
            }
        }
    }
    
    static ArrayList<Student> singleG = new ArrayList<>();
    static ArrayList<Student> doubleG = new ArrayList<>();
    static ArrayList<Student> tripleG = new ArrayList<>();
    
    
    static  void propagate(Student leader) {
        if(leader.isProtected)  {
            leader.isProtected  = false;
            return;
        }
        
        int believeB =  leader.believe;
        int desperX =  believeB  - 1;
        leader.believe = 1;
        int dir =  believeB % 4;
    
        int cr = leader.r;
        int cc = leader.c;
        
        while (true) {
            int nr = cr + dr[dir];
            int nc = cc + dc[dir];
            
            if ((nr < 0 || nr >= N || nc < 0 || nc >= N) || desperX <= 0) {
                return;
            } // 범위에 밖
            
            if(leader.food == students[nr][nc].food) {
                cr =  nr;
                cc = nc;
                continue;
            }
            //propa
            int y = students[nr][nc].believe;
            if( desperX > y) {
                students[nr][nc].food = leader.food;
                desperX -= (y + 1);
                students[nr][nc].believe +=  1;
                students[nr][nc].isProtected = true;
            } else {
                students[nr][nc].food = students[nr][nc].food | leader.food;
                students[nr][nc].believe += desperX;
                students[nr][nc].isProtected = true;
                return;
            }
            cr =  nr;
            cc = nc;        
        }        
    }
    
    static void dinner() {
        // 대표들 전파 대기 시키기.
        for(int i= 0; i < N;i++) {    
            for(int j = 0 ; j < N;j++) {
                if (students[i][j].isLeader) {
                    if (students[i][j].food == 1 || students[i][j].food == 2 ||students[i][j].food == 4) {
                        singleG.add(students[i][j]);
                    } else if (students[i][j].food == 3 || students[i][j].food == 5 ||students[i][j].food == 6) {
                        doubleG.add(students[i][j]);
                    } else {
                        tripleG.add(students[i][j]);
                    }
                }
            }
        }
        Collections.sort(singleG);
        Collections.sort(doubleG);
        Collections.sort(tripleG);
        
        for(Student s : singleG) {
            propagate(s);
        }
        for(Student s : doubleG) {
            propagate(s);
        }
        for(Student s : tripleG) {
            propagate(s);
        }
        // leader reset
        for(int i= 0; i < N;i++) {    
            for(int j = 0 ; j < N;j++) {
                students[i][j].isLeader = false;
                students[i][j].isProtected = false;
            }
        }
        singleG.clear();
        doubleG.clear();
        tripleG.clear();
    }
    
    static void simulate() {
        for (int t = 0 ; t < T;t++) {
            morning();
            
            lunch();
        
            dinner();
            
            print();
        }
    }
    
    static void print() {
        int[] tastes = new int[7 + 1];
        
        for(int i = 0; i < N;i++) {
            for(int j = 0 ; j < N;j++) {
                tastes[students[i][j].food] += students[i][j].believe;
            }
        }
        
        System.out.println( tastes[7] + " " + tastes[3]+ " " + tastes[5]+ " " + tastes[6]+ " " + tastes[4]
                + " " + tastes[2]+ " " + tastes[1]);
    }
    
    public static void main(String[] args) {
        // Please write your code here.
        input();
        
        simulate();
    }
}