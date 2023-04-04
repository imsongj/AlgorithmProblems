package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DijkstraTest {
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
		
			return Integer.compare(weight, o.weight);
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
		int count = 0;
		while (!queue.isEmpty()) {
			Edge current = queue.poll();
			visited[current.v] = true;
			if (count == N) {
				break;
			}
			for (Edge edge : adj[current.v]) {
				if (visited[edge.v]) {
					continue;
				}
				if (distance[edge.v] > current.weight + edge.weight) {
					distance[edge.v] = current.weight + edge.weight;
					queue.add(new Edge(edge.v, distance[edge.v]));
				}
			}
		}
		System.out.println(Arrays.toString(distance));
		
	}
}
