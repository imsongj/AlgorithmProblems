import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//1263 사람네트워크2

public class Solution_1263_D6_사람네트워크2_전임송 { 
	static final int INF = Integer.MAX_VALUE>>3;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().trim().split(" ");
			int N = Integer.parseInt(input[0]);
			int[][] adj = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					adj[r][c] = Integer.parseInt(input[(r * N + c) + 1]);
					if (r != c && adj[r][c] == 0) {
						adj[r][c] = INF;
					}
				}
			}
			for (int k = 0; k < N; k++) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (adj[r][c] > adj[r][k] + adj[k][c]) {
							adj[r][c] = adj[r][k] + adj[k][c];
						}
					}
				}
			}
			int min = INF;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += adj[i][j];
				}
				if (min > sum) {
					min = sum;
				}
			}
			System.out.printf("#%d %d%n", t, min);
		}
		//모든 쌍 거리 구하고, 각 정점마다 합
		
		
		br.close();
	}
}