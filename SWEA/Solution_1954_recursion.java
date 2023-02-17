package algorithm.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static int[][] snail;
	static int n;
	static int total;
	
	public static void move(int row, int col, int direction, int number) {
		snail[row][col] = number;
		if (number == total) {
			return;
		}
		int nr = row + dr[direction];
		int nc = col + dc[direction];
		if (nr < 0 || nr == n || nc < 0 || nc == n
				|| snail[nr][nc] > 0) {
			direction = (direction + 1) % 4;
			nr = row + dr[direction];
			nc = col + dc[direction];
		}
		move(nr, nc, direction, number + 1);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
            bw.append(String.format("#%d%n", t));
			n = Integer.parseInt(br.readLine());
			snail = new int[n][n];
			total = n * n;
			move(0,0,0,1);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bw.append(Integer.toString(snail[i][j])).append(' ');
				}
				bw.append('\n');
			}
			bw.flush();
		}
		br.close();
		bw.close();
	}	
}