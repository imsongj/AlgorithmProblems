import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

//1981 배열에서이동
/*
 * bfs 객체에 최소 최대 저장
 * 최대 최소조합마다 visited
 */

public class Main {	
	static final int MAX = 200;
	static final int MIN = 0;
	static final int START_R = 0;
	static final int START_C = 0;
	static final int[] dr = {0, 0, -1 ,1};
	static final int[] dc = {-1, 1, 0 ,0};
	
	static class Position {
		int r;
		int c;
		int min;
		int max;
		public Position(int r, int c, int min, int max) {
			super();
			this.r = r;
			this.c = c;
			this.min = min;
			this.max = max;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		String[] line;
		for (int r = 0; r < N; r++) {
			line = br.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				int number= Integer.parseInt(line[c]);
				board[r][c] = number;
				
			}
		}
		int lower = MIN;
		int upper = MAX;
		int middle = 0;
		int[][] visited; //가능한 차이 값 만큼 visited
		Queue<Position> queue = new ArrayDeque<>();
		while (lower <= upper) {
			middle = (upper + lower) / 2;
			visited = new int[N][N];
			for (int r = 0; r < N; r++) {
				Arrays.fill(visited[r], -1);
			}
			queue.add(new Position(START_R, START_C, board[START_R][START_C], board[START_R][START_C]));
			visited[START_R][START_C] = 0;
			Position current;
			while (!queue.isEmpty()) {
				current = queue.poll();
				for (int d = 0; d < 4; d++) {
					int newR = current.r + dr[d];
					int newC = current.c + dc[d];
					if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
						continue;
					}
					int newMin = current.min;
					if (board[newR][newC] < newMin) {
						newMin = board[newR][newC];
					}
					int newMax = current.max;
					if (board[newR][newC] > newMax) {
						newMax = board[newR][newC];
					}
					
					int newDiff = newMax - newMin;
					if (newDiff > middle) {
						continue;
					}
					
					if (visited[newR][newC] != -1 && visited[newR][newC] <= newDiff) {
						continue;
					}
					//System.out.println("r = " + newR + " c= " + newC + " " + newMin + " " + newMax);
					queue.add(new Position(newR, newC, newMin, newMax));
					visited[newR][newC] = newDiff;
				}
			}
			if (visited[N - 1][N - 1] == -1) { //더 큰 차이값 필요
				lower = middle + 1;
			}
			if (visited[N - 1][N - 1] != -1) { 
				upper = middle - 1;
			}
		}
		System.out.println(lower);
	}
}
