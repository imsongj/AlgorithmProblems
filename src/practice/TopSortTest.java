package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopSortTest {
	static class Node {
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
		
	}
	static Node[] adjList;
	static int[] inDegree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		adjList = new Node[N + 1];
		inDegree = new int[N + 1];
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		List<Integer> order = new ArrayList<>(10);
		while (!queue.isEmpty()) {
			int node = queue.poll();
			order.add(node);
			for(Node tmp = adjList[node]; tmp != null; tmp = tmp.link) {
				if (--inDegree[tmp.vertex] == 0) {
					queue.add(tmp.vertex);
				}
			}
		}
		if (order.size() != N) {
			System.out.println("cycle");
			return;
		}
		System.out.println(order);
	}
	
}
