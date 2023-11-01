import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(
		);
	}
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int answer = 0;
		int[][] map = new int[101][101];
		for (int r = 0; r < rectangle.length; r++) {
			int r1 = rectangle[r][0] * 2;
			int c1 = rectangle[r][1] * 2;
			int r2 = rectangle[r][2] * 2;
			int c2 = rectangle[r][3] * 2;
			for (int i = r1; i <= r2; i++) {
				map[i][c1] = r + 1;
				map[i][c2] = r + 1;
			}
			for (int i = c1; i <= c2; i++) {
				map[r1][i] = r + 1;
				map[r2][i] = r + 1;
			}
		}
		for (int[] rec : rectangle) {
			int r1 = rec[0] * 2;
			int c1 = rec[1] * 2;
			int r2 = rec[2] * 2;
			int c2 = rec[3] * 2;
			for (int i = r1 + 1; i < r2; i++) {
				for (int j = c1 + 1; j < c2; j++) {
					map[i][j] = 0;
				}
			}
		}
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int[][] visited = new int[101][101];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {characterX * 2, characterY * 2});
		visited[characterX * 2][characterY * 2] = 1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			if (r == itemX * 2 && c == itemY * 2) {
				break;
			}
			for (int d = 0; d < 4; d++) {
				int newR = r + dr[d];
				int newC = c + dc[d];
				if (newR < 0 || newR >= 101 || newC < 0 || newC >= 101) {
					continue;
				}
				if (map[newR][newC] == 0) {
					continue;
				}
				if (visited[newR][newC] > 0) {
					continue;
				}
				visited[newR][newC] = visited[r][c] + 1;
				q.add(new int[] {newR, newC});
			}
		}
		return (visited[itemX * 2][itemY * 2] - 1) / 2;
	}
}