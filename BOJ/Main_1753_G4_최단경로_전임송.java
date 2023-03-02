import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//1753 최단경로

public class Main_1753_G4_최단경로_전임송 {
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
			
		}
		public Edge(Edge edge, int weight) {
			this.to = edge.to;
			this.weight = edge.weight + weight;
			
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return weight - o.weight;
		}
		
	}
	static int V;
	static List<Edge>[] adj;
	static int[] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		V = Integer.parseInt(input[0]);
		int E = Integer.parseInt(input[1]);
		adj = new List[V + 1];
		distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>(10);
		}
		int start =  Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			adj[from].add(new Edge(to, weight));
		}
		dijkstra(start);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				sb.append("INF").append(" ");
				continue;
			}
			sb.append(distance[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.add(new Edge(start, 0)); //시작정점
		distance[start] = 0;
		while (!queue.isEmpty()) {
			Edge current = queue.poll();
			if (distance[current.to] < current.weight) {
				continue;
			}
			for (Edge edge : adj[current.to]) {
				if (distance[edge.to] > current.weight + edge.weight) {
					distance[edge.to] = current.weight + edge.weight;
					queue.add(new Edge(edge.to, distance[edge.to] ));
				}
			}
		}
	}
}
