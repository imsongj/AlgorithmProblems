package practice;

import java.util.ArrayList;

public class PrimTest {
	
	public static void main(String[] args) {
		int N;
		int[][] map = new int[N][N];
		boolean[] visited = new boolean[N];
		ArrayList<Integer> selected = new ArrayList<>(N);
		int index;
		int min;
		int result = 0;
		
		selected.add(0);
		visited[0] = true;
		
		for (int i = 0; i < N - 1; i++) {
			min = Integer.MAX_VALUE;
			index = 0;
			
			for (Integer v : selected) {
				for (int j = 0; j < N; j++) {
					if (map[v][j] != 0 && !visited[j] && map[v][j] < min) {
						min = map[v][j];
						index = j;
					}
				}
			}
			result += min;
			selected.add(index);
			visited[index] = true;
		}
	}
}
