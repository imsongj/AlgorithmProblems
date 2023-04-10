import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//17136 색종이붙이기
/*
 * 모든 칸에서 1~5색종이 사용하는 경우 탐색 dfs
 *  
 */
public class Main_17136_G2_색종이붙이기_전임송 {
	static final int MAX_SIZE = 5;
	static final int MAX_COUNT = 5;
	static final int N = 10;
	static final int EMPTY = 0;
	static final int COVER = 1;
	static int[][] board;
	static int[] paperCount;
	static int minCount = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][N];
		paperCount = new int[MAX_SIZE + 1];
		String[] input;
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(input[c]);
			}
		}
		dfs(0, 0, 0);
		if (minCount == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(minCount);
	}
	
	public static void dfs(int r, int c, int count) {
		if (r == 0 && c == N) {
			if (checkCover() && count < minCount) {
				minCount = count;
			}
			return;
		}
		int nextR = (r + 1) % N;
		int nextC = c + (r + 1) / N;
		if (board[r][c] == EMPTY) {
			dfs(nextR, nextC, count);
			return;
		}
		for (int i = 1; i <= MAX_SIZE; i++) {
			if (paperCount[i] == MAX_COUNT) { //해당 사이즈의 종이를 사용할수 없는 경우
				continue;
			}
			if (check(r, c, i)) {
				fill(r, c, i);
				paperCount[i]++;
				dfs(nextR, nextC, count + 1);
				rollback(r, c, i);
				paperCount[i]--;
			}
		}
		
	}
	
	public static boolean check(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			int nextR = r + i;
			if (nextR < 0 || nextR >= N) {
				return false;
			}
			for (int j = 0; j < size; j++) {
				int nextC = c + j;
				if (nextC < 0 || nextC >= N) {
					return false;
				}
				if (board[nextR][nextC] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void fill(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[r + i][c + j] = EMPTY;
			}
		}
	}
	
	public static void rollback(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[r + i][c + j] = COVER;
			}
		}
	}
	
	public static boolean checkCover() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == COVER) {
					return false;
				}
			}
		}
		return true;
	}
}
