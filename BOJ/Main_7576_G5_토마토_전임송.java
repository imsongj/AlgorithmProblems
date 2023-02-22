import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
//토마토 7576

class Position {
	int r;
	int c;
	
	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main_7576_G5_토마토_전임송 {
	static final int RED = 1;
	static final int GREEN = 0;
	
	static int N;
	static int M;
	static int[][] board;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		board = new int[N][M];
		
		int greenCount = 0;
		int minDays = 1;
		Queue<Position> queue = new ArrayDeque<Position>();
		
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(input[c]);
				if (board[r][c] == GREEN) {
					greenCount++;
				}
				if (board[r][c] == RED) {
					queue.add(new Position(r, c));
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Position thisPos = queue.poll();
			for (int d = 0; d < 4; d++) {
				int newR = thisPos.r + dr[d];
				int newC = thisPos.c + dc[d];
				
				if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
					continue;
				}
				if (board[newR][newC] == GREEN) {
					board[newR][newC] = board[thisPos.r][thisPos.c] + 1;
					minDays = board[thisPos.r][thisPos.c] + 1;
					greenCount--;
					queue.add(new Position(newR, newC));
				}
			}
		}
		if (greenCount > 0) {
			minDays = 0;
		}
		System.out.println(minDays - 1);
	}
}
