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
	static class Edge implements Comparable<Edge> {
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
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		List<Edge>[] adj = new List[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>(10);
		}
		for (int i = 0; i < m; i++) {
			adj[sc.nextInt()].add(new Edge(sc.nextInt(), sc.nextInt()));
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		int[] distance = new int[n];
		Arrays.fill(distance, 1_000_000);
		pq.add(new Edge(0, 0));
		visited[0] = true;
		distance[0] = 0;
		int count = 0;
		while (!pq.isEmpty()) {
			count++;
			Edge current = pq.poll();
			visited[current.v] = true;
			
			for (Edge edge : adj[current.v]) {
				if (visited[edge.v]) {
					continue;
				}
				if (current.weight + edge.weight < distance[edge.v]) {
					distance[edge.v] = current.weight + edge.weight;
					pq.add(new Edge(edge.v, distance[edge.v]));
				}
			}
			if (count == 6) {
				break;
			}
		}
		System.out.println(Arrays.toString(distance));
		System.out.println(count);
	}
}
