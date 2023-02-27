import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//3289 서로소집합

public class Solution_3289_D4_서로소집합_전임송 { 
	static final int UNION_OPERATION = 0;
	static final int CHECK_OPERATION = 1;
	static int[] parents;
	
	public static void makeSet(int v) {
		parents[v] = v;
	}
	public static int findSet(int v) {
		if (parents[v] == v) {
			return v;
		}
		parents[v] = findSet(parents[v]);
		return parents[v];
	}
	public static void union(int a, int b) {
		parents[findSet(a)] = findSet(b);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			parents = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				makeSet(i);
			}
			for (int i = 0; i < m; i++) {
				input = br.readLine().split(" ");
				int operation = Integer.parseInt(input[0]);
				int a = Integer.parseInt(input[1]);
				int b = Integer.parseInt(input[2]);
				if (operation == CHECK_OPERATION) {
					if (findSet(a) == findSet(b)) {
						sb.append(1);
						continue;
					}
					sb.append(0);
					continue;
				}
				union(a,b);
			}
			System.out.printf("#%d %s%n", t, sb.toString());
		}
		br.close();
	}
}