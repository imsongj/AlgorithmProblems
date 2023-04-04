import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//키순서
/*
 * 모든 노드의 정보를 받는 노드를 찾아야한다
 * 부모 탐색, 자식 탐색
 * floyd-warshall
 */

public class Solution_5643_D4_키순서_전임송 { 
	static final int INF = Integer.MAX_VALUE>>3;
	static int[] countConnected;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= testCase; t++) {
			N = Integer.parseInt(br.readLine().trim());
			int M = Integer.parseInt(br.readLine().trim());
			
			int[][] adj = new int[N + 1][N + 1];
			
			String[] input;
			for (int i = 0; i < M; i++) {
				input = br.readLine().trim().split(" ");
				int from = Integer.parseInt(input[0]);
				int to = Integer.parseInt(input[1]);
				adj[from][to] = 1;
			}
			countConnected = new int[N + 1];
			int[][] distance = new int[N + 1][N + 1];
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (adj[r][c] == 1) {
						distance[r][c] = 1;
						continue;
					}
					if (r == c) {
						distance[r][c] = 0;
						continue;
					}
					distance[r][c] = INF;
				}
			}
			getCount(distance);
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (adj[r][c] == 1) {
						distance[c][r] = 1;
						continue;
					}
					if (r == c) {
						distance[r][c] = 0;
						continue;
					}
					distance[c][r] = INF;
				}
			}
			getCount(distance);
			int result = 0;
			for (int i = 1; i <= N; i++) {
				if (countConnected[i] == N - 1) {
					result++;
				}
			}
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		//모든 쌍 거리 구하고, 각 정점마다 합
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static void getCount(int[][] distance) {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					distance[i][j] = Math.min(
							distance[i][k] + distance[k][j], distance[i][j]);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			int count = 0;
			for (int j = 1; j <= N; j++) {
				if (distance[i][j] == INF || distance[i][j] == 0) {
					continue;
				}
				count++;
			}
			countConnected[i] += count;
		}
	}
}