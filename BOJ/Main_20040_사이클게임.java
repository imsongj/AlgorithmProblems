import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20040_사이클게임 {
    /*
    20040 사이클 게임
    union find, 최적화

    */
    public static int[] parent;

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) {
            return true;
        }
        parent[parentB] = parentA;
        return false;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            boolean isSameSet = union(a, b);
            if (isSameSet) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}