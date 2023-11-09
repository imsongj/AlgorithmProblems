import java.util.*;

/*
    bfs
    visited int array,
    if visited is same, add count
    new int[]{r, c, count}
*/

class Solution {
	static final int MOD = 1_000_000_007;
	public int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		int[][] visited = new int[n][m];
		int[][] count = new int[n][m];
		Queue<int[]> queue = new ArrayDeque<>();
		final int startR = 0;
		final int startC = 0;
		final int endR = n - 1;
		final int endC = m - 1;
		final int[] dr = {0, 1};
		final int[] dc = {1, 0};
		for (int[] puddle : puddles) {
			visited[puddle[1] - 1][puddle[0] - 1] = -1;
		}

		queue.add(new int[]{startR, startC});
		count[startR][startC] = 1;
		visited[startR][startC] = 1;
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int currentCount = count[r][c];
			int currentVisited = visited[current[0]][current[1]];
			// System.out.println(r + " " + c + " " + currentCount + " " + currentVisited);
			for (int d = 0; d < 2; d++) {
				int newR = r + dr[d];
				int newC = c + dc[d];
				if (newR >= n || newR < 0 || newC >= m || newC < 0) {
					continue;
				}
				if (visited[newR][newC] == -1) {
					continue;
				}
				if (visited[newR][newC] > 0) {
					count[newR][newC] = currentCount + count[newR][newC];
					count[newR][newC] %= MOD;
					continue;
				}
				count[newR][newC] = currentCount + count[newR][newC];
				count[newR][newC] %= MOD;
				visited[newR][newC] = currentVisited + 1;
				queue.add(new int[]{newR, newC});
			}
		}
		return count[endR][endC];
	}
}