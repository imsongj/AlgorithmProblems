import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 18111 마인크래프트
 * 0부터 256까지 
 * 해당 높이로 모두 바꾸는 경우, 각 칸에서 몇초를 사용해야하는지 계산해서 합한다.
 */

public class Main_18111_마인크래프트_전임송 {		
	static final int MAX_HEIGHT = 256;
	static final int MIN_HEIGHT = 0;
	
	static int[][] board;
	static int N;
	static int M;
	static int B;
	static int minCount = Integer.MAX_VALUE;
	static int resultHeight = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		B = Integer.parseInt(input[2]);
		board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}
		for (int h = MIN_HEIGHT; h <= MAX_HEIGHT; h++) {
			int sum = 0;
			int blocks = B;
			for (int r = 0; r < N; r++) { 
				for (int c = 0; c < M; c++) {
					if (board[r][c] == h) { //h와 현재 위치의 높이가 같은 경우
						continue;
					}
					if (board[r][c] < h) { //h 보다 현재 위치의 높이가 낮은 경우
						int diff = h - board[r][c];
						sum += diff;
						blocks -= diff;
						continue;
					}
					if (board[r][c] > h) { //h 보다 현재 위치의 높이가 높은 경우
						int diff = board[r][c] - h;
						sum += diff * 2;
						blocks += diff;
					}
				}
			}
			if (blocks < 0) {
				continue;
			}
			if (sum <= minCount) {
				minCount = sum;
				resultHeight = h;
			}
		}
		System.out.println(minCount + " " + resultHeight);
	}
	
}
