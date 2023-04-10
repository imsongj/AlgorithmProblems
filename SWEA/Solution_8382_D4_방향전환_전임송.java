import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//8382 방향전환
/*
 * bfs
 * 세로이동으로 온 경우, 가로 이동으로 온 경우 따로 visited
 */

public class Solution_8382_D4_방향전환_전임송 { 
	static final int MIN_VALUE = 0;
	static final int MAX_VALUE = 200;
	static final int VALUE_RANGE = 202;
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int[] DIRECTIONS = {-1, 1};
	static class Position {
		int x;
		int y;
		int prev;
		int moves;
		public Position(int x, int y, int prev, int moves) {
			super();
			this.x = x;
			this.y = y;
			this.prev = prev;
			this.moves = moves;
		}
		
	}
	static int startX;
	static int startY;
	static int destX;
	static int destY;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().trim().split(" ");
			startX = Integer.parseInt(input[0]) + 100;
			startY = Integer.parseInt(input[1]) + 100;
			destX = Integer.parseInt(input[2]) + 100;
			destY = Integer.parseInt(input[3]) + 100;
			
			sb.append('#').append(t).append(' ').append(bfs()).append('\n');
			
		}
		System.out.println(sb);
	}
	
	public static int bfs() {
		int minMoves = Integer.MAX_VALUE;
		boolean[][][] visited = new boolean[VALUE_RANGE][VALUE_RANGE][2];
		Queue<Position> queue = new ArrayDeque<>();
		queue.add(new Position(startX, startY, HORIZONTAL, 0));
		visited[startX][startY][HORIZONTAL] = true;
		queue.add(new Position(startX, startY, VERTICAL, 0));
		visited[startX][startY][VERTICAL] = true;
		while (!queue.isEmpty()) {
			Position curr = queue.poll();
			for (int d = 0; d < 2; d++) {
				int nextX = curr.x;
				int nextY = curr.y;
				int nextDirection = 0;
				int nextMoves = curr.moves + 1;
				if (curr.prev == HORIZONTAL) {
					nextY += DIRECTIONS[d];
					nextDirection = VERTICAL;
				}
				if (curr.prev == VERTICAL) {
					nextX += DIRECTIONS[d];
					nextDirection = HORIZONTAL;
				}
				if (nextX < MIN_VALUE || nextX > MAX_VALUE || nextY < MIN_VALUE || nextY > MAX_VALUE) {
					continue;
				}
				if (visited[nextX][nextY][nextDirection]) {
					continue;
				}
				if (nextX == destX && nextY == destY) {
					return nextMoves;
				}
				queue.add(new Position(nextX, nextY, nextDirection, nextMoves));
				visited[nextX][nextY][nextDirection] = true;
			}
		}
		return 0;
	}
}