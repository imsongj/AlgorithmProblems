import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//7465 창용 마을 무리의 개수

public class Solution_7465_D4_창용마을무리의개수_전임송 { 
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
		parents[findSet(b)] = findSet(a);
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
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				union(a,b);
			}
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				set.add(findSet(i));
			}
			System.out.printf("#%d %s%n", t, set.size());
		}
		br.close();
	}
}