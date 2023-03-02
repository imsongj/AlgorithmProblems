package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraTest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[][] adjMatirx = new int[V][V];
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < V; j++) {
				adjMatirx[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		final int INF = Integer.MAX_VALUE;
		int[] distance = new int[V];
		boolean[] visited = new boolean[V];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		int min;
		int current;
		for (int c = 0; c < V; c++) {
			min = INF;
			current = -1;
			
			for (int i = 0; i < V; i++) {
				if (!visited[i] && min > distance[i]) {
					min = distance[i];
					current = i;
				}
			}
			if (current == -1) {
				break;
			}
			visited[current] = true;
			for (int i = 0; i < V; i++) {
				if (!visited[i] && adjMatirx[current][i] != 0 
						&& distance[i] > min + adjMatirx[current][i]) {
					distance[i] = min + adjMatirx[current][i];
				}
			}
		}
		System.out.println(distance[end] != INF ? distance[end] : -1);
	}
}
