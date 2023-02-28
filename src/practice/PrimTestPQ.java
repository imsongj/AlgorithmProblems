package practice;

import java.util.ArrayList;
import java.util.PriorityQueue;

import practice.KruskalTest.Edge;

public class PrimTestPQ {
	static class Edge implements Comparable<Edge> {
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
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) {
		int N;
		
		ArrayList<Edge>[] adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		boolean[] visited = new boolean[N];
		
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		
		int count = 1;
		int result = 0;
		
		queue.addAll(adj[0]); 
		visited[0] = true;
		
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
	}
}
