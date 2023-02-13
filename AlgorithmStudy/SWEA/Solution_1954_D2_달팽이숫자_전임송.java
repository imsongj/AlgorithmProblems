package algorithm.swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Solution_1954 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
            bw.append(String.format("#%d%n", t));
			int n = Integer.parseInt(br.readLine());
			int[][] snail = new int[n][n];
			int total = n * n;
			int cr = 0;
			int cc = 0;
			int d = 0;
			for (int num = 1; num <= total; num++) {
				snail[cr][cc] = num;
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if (nr < 0 || nr == n || nc < 0 || nc == n 
						|| snail[nr][nc] > 0) {
					d = (d + 1) % 4;
					nr = cr + dr[d];
					nc = cc + dc[d];
				}
				cr = nr;
				cc = nc;
			}
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