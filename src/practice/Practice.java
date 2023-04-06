package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Practice {
	static class Edge implements Comparable<Edge>{
		int v;
		int weight;
		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return weight - o.weight;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		List<Edge>[] adj = new List[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>(5);
		}
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			adj[from].add(new Edge(to, weight));
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		int[] distance = new int[N];
		Arrays.fill(distance, 100_000);
		queue.add(new Edge(0, 0));
		distance[0] = 0;
		
		while (!queue.isEmpty()) {
			Edge current = queue.poll();
			visited[current.v] = true;
			for (Edge adjE : adj[current.v]) {
				if (visited[adjE.v]) {
					continue;
				}
				if (distance[adjE.v] > current.weight + adjE.weight) {
					distance[adjE.v] = current.weight + adjE.weight;
					queue.add(new Edge(adjE.v, distance[adjE.v]));
				}
			}
		}
		System.out.println(Arrays.toString(distance));
	}
}
