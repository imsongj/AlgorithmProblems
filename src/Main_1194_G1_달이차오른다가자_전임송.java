import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

//1194 달이차오른다가자
/*
 * bfs, 키 상태별로 visited배열 
 */
public class Main_1194_G1_달이차오른다가자_전임송 {
	static final char START = '0';
	static final char EXIT = '1';
	static final char WALL = '#';
	static final char ROAD = '.';
	static final int NUMBER_OF_KEYS = 6;
	static final int INITIAL_KEYS = 0;
	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	static class Position {
		int r;
		int c;
		int keys;
		int moves;
		public Position(int r, int c, int keys, int moves) {
			super();
			this.r = r;
			this.c = c;
			this.keys = keys;
			this.moves = moves;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Position [r=").append(r).append(", c=").append(c).append(", keys=").append(keys)
					.append(", moves=").append(moves).append("]");
			return builder.toString();
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
					board[r][c] = ROAD;
				}
			}
		}
		boolean[][][] visited = new boolean[N][M][1<<NUMBER_OF_KEYS];
		Queue<Position> queue = new ArrayDeque<Position>();
		queue.add(new Position(startR, startC, INITIAL_KEYS, 0));
		visited[startR][startC][INITIAL_KEYS] = true;
		Position current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			//System.out.println(current);
			for (int d = 0; d < 4; d++) {
				int newR = current.r + dr[d];
				int newC = current.c + dc[d];
				int newKeys = current.keys;
				int newMoves = current.moves + 1;
				
				if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
					continue;
				}
				if (visited[newR][newC][current.keys]) {
					continue;
				}
				if (board[newR][newC] == WALL) {
					continue;
				}
				if (board[newR][newC] == EXIT) {
					System.out.println(newMoves);
					return;
				}
				if (board[newR][newC] >= 'a' && board[newR][newC] <= 'f') { //열쇠인 경우
					newKeys |= 1 << board[newR][newC] - 'a';
				}
				if (board[newR][newC] >= 'A' && board[newR][newC] <= 'F') { //문인 경우
					if ((newKeys & 1 << board[newR][newC] - 'A') == 0) { //못 여는경우
						continue;
					}
				}
				queue.add(new Position(newR, newC, newKeys, newMoves));
				visited[newR][newC][newKeys] = true;
			}
		}
		System.out.println(-1);
	}	
}
