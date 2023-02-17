package algorithm.update;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_1260_S2_DFS와BFS_전임송 {
	static final int CONNECTED = 1;
	
	static int N;
	static int[][] graph;
	static boolean[] visited;
	
	static StringBuilder sb;
	public static void dfs(int current) {
		sb.append(current).append(' ');
		visited[current] = true;
		for (int adj = 1; adj <= N; adj++) {
			if (graph[current][adj] == CONNECTED && !visited[adj]) {
				dfs(adj);
			}
		}
		
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<Integer>(10);
		visited[start] = true;
		queue.offer(start);
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(' ');
			for (int adj = 1; adj <= N; adj++) {
				if (graph[current][adj] == CONNECTED && !visited[adj]) {
					visited[adj] = true;
					queue.offer(adj);
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int v = Integer.parseInt(input[2]);
		
		graph = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int p = Integer.parseInt(input[0]);
			int q = Integer.parseInt(input[1]);
			
			graph[p][q] = CONNECTED;
			graph[q][p] = CONNECTED;
		}
		
		dfs(v);
		sb.append('\n');
		Arrays.fill(visited, false);
		bfs(v);
		
		System.out.println(sb.toString());
		br.close();
	}	
}