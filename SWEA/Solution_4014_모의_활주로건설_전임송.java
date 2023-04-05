import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//4014 활주로건설
/*
 * 한방향으로 이동, 
 * 		같은 높이면 +1
 * 			다른 높이면 0
 * 				높이 차이가 1 나고 그 전 이동 값이 x 이상이면 
 */

public class Solution_4014_모의_활주로건설_전임송 { 
	static final int INF = Integer.MAX_VALUE>>3;
	static int[][] map;
	static int N;
	static int X;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().trim().split(" ");
			N = Integer.parseInt(input[0]);
			X = Integer.parseInt(input[1]);
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				input = br.readLine().trim().split(" ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(input[c]);
				}
			}
			int count = 0;
			for (int c = 0; c < N; c++) {
				if (checkRoadVertical(c)) {
					count++;
				}
			}
			for (int r = 0; r < N; r++) {
				if (checkRoadHorizontal(r)) {
					count++;
				}
			}
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean checkRoadVertical(int c) {
		boolean[] visited = new boolean[N];
		int equalCount = 1;
		int prevHeight = map[0][c];
		for (int r = 1; r < N; r++) {
			int currentHeight = map[r][c]; 
			if (currentHeight == prevHeight) {
				equalCount++;
				continue;
			}
			if (currentHeight != prevHeight) {
				int diff = currentHeight - prevHeight;
				if (diff > 1) {
					return false;
				}
				if (diff == 1 && equalCount < X) {
					return false;
				}
				if (diff == 1 && equalCount >= X) {
					for (int i = 1; i <= X; i++) {
						int nr = r - i;
						if (visited[nr]) {
							return false;
						}
						visited[nr] = true;
					}
				}
				equalCount = 1;
				prevHeight = currentHeight;
			}
		}
		equalCount = 1;
		prevHeight = map[N - 1][c];
		for (int r = N - 2; r >= 0; r--) {
			int currentHeight = map[r][c]; 
			if (currentHeight == prevHeight) {
				equalCount++;
				continue;
			}
			if (currentHeight != prevHeight) {
				int diff = currentHeight - prevHeight;
				if (diff > 1) {
					return false;
				}
				if (diff == 1 && equalCount < X) {
					//System.out.println(4);
					return false;
				}
				if (diff == 1 && equalCount >= X) {
					for (int i = 1; i <= X; i++) {
						int nr = r + i;
						if (visited[nr]) {
							return false;
						}
						visited[nr] = true;
					}
				}
				equalCount = 1;
				prevHeight = currentHeight;
			}
		}
		return true;
	}
	
	public static boolean checkRoadHorizontal(int r) {
		boolean[] visited = new boolean[N];
		int equalCount = 1;
		int prevHeight = map[r][0];
		for (int c = 1; c < N; c++) {
			int currentHeight = map[r][c]; 
			if (currentHeight == prevHeight) {
				equalCount++;
				continue;
			}
			if (currentHeight != prevHeight) {
				int diff = currentHeight - prevHeight;
				if (diff > 1) {
					return false;
				}
				if (diff == 1 && equalCount < X) {
					return false;
				}
				if (diff == 1 && equalCount >= X) {
					for (int i = 1; i <= X; i++) {
						int nc = c - i;
						if (visited[nc]) {
							return false;
						}
						visited[nc] = true;
					}
				}
				equalCount = 1;
				prevHeight = currentHeight;
			}
		}
		equalCount = 1;
		prevHeight = map[r][N - 1];
		for (int c = N - 2; c >= 0; c--) {
			int currentHeight = map[r][c]; 
			if (currentHeight == prevHeight) {
				equalCount++;
				continue;
			}
			if (currentHeight != prevHeight) {
				int diff = currentHeight - prevHeight;
				if (diff > 1) {
					return false;
				}
				if (diff == 1 && equalCount < X) {
					return false;
				}
				if (diff == 1 && equalCount >= X) {
					for (int i = 1; i <= X; i++) {
						int nc = c + i;
						if (visited[nc]) {
							return false;
						}
						visited[nc] = true;
					}
				}
				equalCount = 1;
				prevHeight = currentHeight;
			}
		}
		return true;
	}
}