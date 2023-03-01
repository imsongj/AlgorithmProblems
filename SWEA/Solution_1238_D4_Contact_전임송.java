import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

//1238 Contact

public class Solution_1238_D4_Contact_전임송 { 
	static final int SIZE = 100;
	static int N;
	static int start;
	static Set<Integer>[] adj;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = 10;
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			start = Integer.parseInt(input[1]);
			
			adj = new Set[SIZE + 1];
			visited = new int[SIZE + 1];
			for (int i = 1; i <= SIZE; i++) {
				adj[i] = new HashSet<>();
			}
			input = br.readLine().split(" ");
			for (int i = 0; i < N; i += 2) {
				int a = Integer.parseInt(input[i]);
				int b = Integer.parseInt(input[i + 1]);
				adj[a].add(b); //인접 리스트 초기화
			}
			int lastNum = 0;
			int lastOrder = 0;
			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.add(start);
			visited[start] = 1;
			while (!queue.isEmpty()) { //BFSd
				int current = queue.poll();
				int currentOrder = visited[current];
				if (lastOrder < currentOrder) { //마지막 방문 업데이트
					lastNum = current;
					lastOrder = currentOrder;
				}
				if (lastOrder == currentOrder && lastNum < current) {
					lastNum = current;
					lastOrder = currentOrder;
				}
				int order = currentOrder + 1;
				
				for (int neighbor : adj[current]) {
					if (visited[neighbor] > 0) {
						continue;
					}
					visited[neighbor] = order;
					queue.add(neighbor);
				}
			}
			System.out.printf("#%d %d%n", t, lastNum);
		}
		
		br.close();
	}
}