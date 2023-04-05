import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//1967 트리의 지름
/*
 * 1. 아무 정점에서 제일 먼 정점n을 찾는다 dfs
 * 2. 제일 먼 정점n에서 제일 먼 정점m까지의 거리를 구한다.dfs
 */
public class Main2 {
	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
	}
	static int N;
	static List<Edge>[] adj;
	static int maxDistance = 0;
	static int maxNode = 1;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>(5);
		}
		String[] input;
		for (int i = 0; i < N - 1; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			adj[from].add(new Edge(to, weight));
			adj[to].add(new Edge(from, weight));
		}
		if (N == 1) {
			System.out.println(0);
			return;
		}
		visited = new boolean[N + 1];
		dfs(1, 0);
		visited = new boolean[N + 1];
		dfs(maxNode, 0);
		System.out.println(maxDistance);
	}
	
	public static void dfs(int node, int distance) {
		visited[node] = true;
		if (distance > maxDistance) {
			maxDistance = distance;
			maxNode = node;
		}
		for (Edge adjEdge : adj[node]) {
			if (visited[adjEdge.to]) {
				continue;
			}
			dfs(adjEdge.to, distance + adjEdge.weight);
		}
	}
}
