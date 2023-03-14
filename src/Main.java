import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//16398 행성연결
//간선 수가 많기 때문에 prim's 사용
//1. 간선들 오름차순 정렬
//2. union find 사용으로 사이클 방지
// 	간선의 양끝 정점이 같은 집합에 속해 있는지를 먼저 검사

public class Main {	
	public static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
		
	}
	static boolean[] visited;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		String[] line;
		for (int r = 1; r <= N; r++) {
			line = br.readLine().split(" ");
			for (int c = 1; c <= N; c++) {
				if (r == c) {
					continue;
				}
				adj[r].add(new Edge(r, c, Integer.parseInt(line[c - 1])));
			}
		}
		visited = new boolean[N + 1];
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		int count = 1;
		long result = 0;

		queue.addAll(adj[1]); 
		visited[1] = true;
		
		while (count != N) {
			Edge min = queue.poll();
			if (visited[min.to]) {
				continue;
			}
			
			result += min.weight;
			queue.addAll(adj[min.to]);
			visited[min.to] = true;
			count++;
		}
		System.out.println(result);
	}
}