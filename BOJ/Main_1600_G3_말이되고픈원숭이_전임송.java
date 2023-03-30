import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//1600 말이되고픈원숭이
/*
 */

public class Main_1600_G3_말이되고픈원숭이_전임송 {	
	static final int startR = 0;
	static final int startC = 0;
	static final int HOLE = 1;
	static int[] dR = {0, 0, -1, 1};
	static int[] dC = {-1, 1, 0, 0};
	static int[] horseR = {-2, -2 , -1, -1, 1, 1, 2, 2};
	static int[] horseC = {-1, 1, -2, 2, -2, 2, -1, 1};
	static class Position {
		int r;
		int c;
		int count;
		public Position(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		int row = Integer.parseInt(line[1]);
		int col = Integer.parseInt(line[0]);
		int[][] map = new int[row][col];
		for (int r = 0; r < row; r++) {
			line = br.readLine().split(" ");
			for (int c = 0; c < col; c++) {
				map[r][c] = Integer.parseInt(line[c]);
			}
		}
		
		int[][][] visited = new int[row][col][K+1];
		Queue<Position> queue = new ArrayDeque<Main_1600_G3_말이되고픈원숭이_전임송.Position>();
		queue.add(new Position(startR, startC, 0));
		visited[startR][startC][0] = 1; //동작수 1 추가
		Position current;
		while (!queue.isEmpty()) {
			current = queue.poll();
			for (int d = 0; d < 4; d++) {
				int newR = current.r + dR[d];
				int newC = current.c + dC[d];
				if (newR < 0 || newR >= row || newC < 0 || newC >= col
						|| visited[newR][newC][current.count] > 0 || map[newR][newC] == HOLE) {
					continue;
				}
				queue.add(new Position(newR, newC, current.count));
				visited[newR][newC][current.count] = visited[current.r][current.c][current.count] + 1;
			}
			
			if (current.count >= K) { //더 이상 말의 동작을 못하는 경우
				continue;
			}
			
			for (int d = 0; d < 8; d++) { //말의 동작
				int newR = current.r + horseR[d];
				int newC = current.c + horseC[d];
				if (newR < 0 || newR >= row || newC < 0 || newC >= col
						|| visited[newR][newC][current.count + 1] > 0 || map[newR][newC] == HOLE) {
					continue;
				}
				queue.add(new Position(newR, newC, current.count + 1));
				visited[newR][newC][current.count + 1] = visited[current.r][current.c][current.count] + 1;
			}
			for (int i = 0; i <= K; i++) {
				if (visited[row - 1][col - 1][i] > 0) {
					queue.clear();
				}
			}
		}
		int min = Integer.MAX_VALUE;
		
		
		for (int i = 0; i <= K; i++) {
			if (visited[row - 1][col - 1][i] == 0) {
				continue;
			}
			min = Math.min(visited[row - 1][col - 1][i], min);
		}
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(min - 1);
	}
}
