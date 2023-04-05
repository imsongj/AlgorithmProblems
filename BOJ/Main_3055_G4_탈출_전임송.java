import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

//3055 탈출
/*
 * bfs, 물 차오를 칸 표시
 */
public class Main_3055_G4_탈출_전임송 {
	static final char START = 'S';
	static final char EXIT = 'D';
	static final char WALL = 'X';
	static final char WATER = '*';
	static final char PRE_WATER = '+';
	static final char ROAD = '.';
	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	static class Position {
		int r;
		int c;
		int moves;
		public Position(int r, int c, int moves) {
			super();
			this.r = r;
			this.c = c;
			this.moves = moves;
		}
	}
	static int N;
	static int M;
	static char[][] board;
	static int minMoves = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		board = new char[N][M];
		int startR = 0;
		int startC = 0;
		String line;
		for (int r = 0; r < N; r++) {
			line = br.readLine();
			for (int c = 0; c < M; c++) {
				char character = line.charAt(c);
				board[r][c] = character;
				if (character == START) {
					startR = r;
					startC = c;
				}
			}
		}
		boolean[][] visited = new boolean[N][M];
		Queue<Position> queue = new ArrayDeque<Position>();
		queue.add(new Position(startR, startC, 0));
		visited[startR][startC] = true;
		Position current;
		int prevMoves = 1;
		markWater();
		while(!queue.isEmpty()) {
			current = queue.poll();
			if (current.moves == prevMoves) {
				markWater();
				prevMoves++;
			}
			//System.out.println(current);
			for (int d = 0; d < 4; d++) {
				int newR = current.r + dr[d];
				int newC = current.c + dc[d];
				int newMoves = current.moves + 1;
				
				if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
					continue;
				}
				if (visited[newR][newC]) {
					continue;
				}
				if (board[newR][newC] == WALL || board[newR][newC] == WATER 
						|| board[newR][newC] == PRE_WATER) {
					continue;
				}
				if (board[newR][newC] == EXIT) {
					System.out.println(newMoves);
					return;
				}
				queue.add(new Position(newR, newC, newMoves));
				visited[newR][newC] = true;
			}
		}
		System.out.println("KAKTUS");
	}	
	public static void markWater() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == PRE_WATER) {
					board[r][c] = WATER;
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {

				if (board[r][c] != WATER) {
					continue;
				}
				for (int d = 0; d < 4; d++) {
					int newR = r + dr[d];
					int newC = c + dc[d];
					if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
						continue;
					}
					if (board[newR][newC] == WALL || board[newR][newC] == EXIT 
							|| board[newR][newC] == WATER) {
						continue;
					}
					board[newR][newC] = PRE_WATER;
				}
			}
		}
		/*for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}*/
	}
}
