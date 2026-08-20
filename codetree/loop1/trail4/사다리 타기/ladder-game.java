import java.util.Scanner;
import java.util.*;

class Line implements Comparable<Line> {
    int a;
    int b;

    Line(int a,int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(this.b, o.b);
    }
}

public class Main {
    static int n,m,min;
    static ArrayList<Line> lines = new ArrayList<>();
    static ArrayList<Line> selectedLines = new ArrayList<>();
    static int minAns = Integer.MAX_VALUE;

    static int[] getResult(ArrayList<Line> lineList) {
        lineList.sort(Line::compareTo);

        int[] result = new int[n+1];
        for (int i = 1; i <= n; i++) {
            result[i] = i;
        }

        for (Line line : lineList) {
            int col = line.a;
            int temp = result[col];
            result[col] = result[col + 1];
            result[col + 1] = temp;
        }
        return result;
    }

    static void findMinLines(int idx) {
        if (idx == m) {
            int[] originalResult = getResult(lines);
            int[] currentResult = getResult(selectedLines);
            
        

            boolean isSame = true;

            for (int i = 1; i <= n;i++) {
                if (originalResult[i] != currentResult[i]) {
                    isSame = false;
                    break;
                }
            }

            if (isSame) {
                minAns = Math.min(minAns,selectedLines.size());

            }
            return;
        }

        selectedLines.add(lines.get(idx));
        findMinLines(idx + 1);
        selectedLines.remove(selectedLines.size() - 1);

        findMinLines(idx + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            lines.add(new Line(a,b));    
        }

        findMinLines(0);
        System.out.println(minAns);
        // Please write your code here.
    }
}