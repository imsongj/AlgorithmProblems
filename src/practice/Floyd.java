package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Floyd {
	static final int INF = Integer.MAX_VALUE>>3;
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if (i != j && map[i][j] == 0) {
					map[i][j] = INF;
				}
			}
		}
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 0; j < N; j++) {
					if (i == j || j == k) {
						continue;
					}
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j]; 
					}
				}
			}
		}
	}
 }
