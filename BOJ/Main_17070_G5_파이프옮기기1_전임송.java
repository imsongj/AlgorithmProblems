import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//17070 파이프옮기기
/*

 */

public class Main_17070_G5_파이프옮기기1_전임송 {	
	static final int NUMBER_OF_STATES = 3;
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int DIAGONAL = 2;
	static final int WALL = 1;
	static final int startR = 1;
	static final int startC = 2;
	
	static int[][] map;
	static long[][][] dp;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] line;
		map = new int[N + 1][N + 1];
		dp = new long[N + 1][N + 1][NUMBER_OF_STATES]; //특정 칸으로 오는 경우의 수
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i + 1][j + 1] = Integer.parseInt(line[j]);
			}
		}
		
		dp[startR][startC][HORIZONTAL] = 1;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[r][c] == WALL) {
					continue;
				}
				dp[r][c][HORIZONTAL] += dp[r][c - 1][HORIZONTAL] + dp[r][c - 1][DIAGONAL];
				dp[r][c][VERTICAL] += dp[r - 1][c][VERTICAL] + dp[r - 1][c][DIAGONAL];
				if (map[r - 1][c] == WALL || map[r][c - 1] == WALL) {
					continue;
				}
				dp[r][c][DIAGONAL] += dp[r - 1][c - 1][HORIZONTAL] + dp[r - 1][c - 1][VERTICAL] + dp[r - 1][c - 1][DIAGONAL];
			}
		}
		System.out.println(dp[N][N][HORIZONTAL] + dp[N][N][VERTICAL] +dp[N][N][DIAGONAL]);
	}
}
