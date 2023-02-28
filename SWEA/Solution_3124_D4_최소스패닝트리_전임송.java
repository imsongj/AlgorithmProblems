import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//3124 최소 스패닝 트리

public class Solution_3124_D4_최소스패닝트리_전임송 { 
	static int[] parents;
	static int V;
	static int E;
	static class Edge implements Comparable<Edge>{
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
	static void makeSet(int v) {
		parents[v] = v;
	}
	static int findSet(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = findSet(parents[v]);
	}
	static boolean union(int u, int v) {
		int parentU = findSet(u);
		int parentV = findSet(v);
		if (parentU == parentV) {
			return false;
		}
		parents[parentU] = parents[parentV];
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			String[] input = br.readLine().split(" ");
			V = Integer.parseInt(input[0]);
			E = Integer.parseInt(input[1]);
			
			Edge[] edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				int c = Integer.parseInt(input[2]);
				edges[i] = new Edge(a, b, c);
			}
			//System.out.printf("#%d %d%n", t, kruskals(edges));
			System.out.printf("#%d %d%n", t, prims(edges));
		}
		br.close();
	}
	
	static long prims(Edge[] edges) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		List<Edge>[] adj = new ArrayList[V + 1];
		boolean[] visited = new boolean[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<Edge>(10);
		}
		for (int i = 0; i < E; i++) {
			Edge edge = edges[i];
			adj[edge.from].add(new Edge(edge.from, edge.to, edge.weight)); 
			adj[edge.to].add(new Edge(edge.to, edge.from, edge.weight)); 
		}
		queue.addAll(adj[edges[0].from]);
		visited[edges[0].from] = true;
		int count = 1;
		long result = 0;
		while (count != V) {
			Edge min = queue.poll();
			if (visited[min.to]) {
				continue;
			}
			result += min.weight;
			visited[min.to] = true;
			count++;
			queue.addAll(adj[min.to]);
		}
		return result;
	}
	
	static long kruskals(Edge[] edges) { //Kruskal's algorithm
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			makeSet(i);
		}
		
		Arrays.sort(edges);
		long result = 0;
		int count = 0;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				count++;
				if (count == V - 1) {
					break;
				}
			}
		}
		return result;
	}
}