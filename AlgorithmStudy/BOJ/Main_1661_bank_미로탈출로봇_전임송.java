import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Position {
	int r;
	int c;
	int time;
	
	public Position(int r, int c, int time) {
		super();
		this.r = r;
		this.c = c;
		this.time = time;
	}

	@Override
	public String toString() {
		return Integer.toString(time);
	}
}

public class Main {
	static final char WALL = '1';
	static final int MAX_TIME = 100_000;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static int row;
	static int col;
	static char[][] graph;
	static int[][] visited;
	static Position result;
	
	static StringBuilder sb;
	
	public static void bfs(Position start, Position end) {
		Queue<Position> queue = new ArrayDeque<>(10);
		queue.add(start);
		visited[start.r][start.c] = 1;
		
		while(!queue.isEmpty()) {
			Position current = queue.poll();
			for (int d = 0; d < 4; d++) {
				int newR = current.r + dr[d];
				int newC = current.c + dc[d];
				if (newR < 1 || newR > row || newC < 1 || newC > col 
						|| graph[newR][newC] == WALL || visited[newR][newC] == 1) {
					continue;
				}
				Position newPos = new Position(newR, newC, current.time + 1);
				queue.add(newPos);
				visited[newR][newC] = 1;
				if (newR == end.r && newC == end.c) {
					result = newPos;
					return;
				}
			}
		}
		
	}
	public static void dfs(Position current, Position end) {
		if (current.r == end.r && current.c == end.c 
				&& visited[end.r][end.c] > current.time) {
			visited[end.r][end.c] = current.time;
			result = current;
			return;
		}
		if (current.time >= result.time) {
			return;
		}
		visited[current.r][current.c] = current.time;
		for (int d = 0; d < 4; d++) {
			int newR = current.r + dr[d];
			int newC = current.c + dc[d];
			int newTime = current.time  + 1;
			if (newR < 1 || newR > row || newC < 1 || newC > col 
					|| graph[newR][newC] == WALL || visited[newR][newC] <= newTime) {
				continue;
			}
			Position newPos = new Position(newR, newC, newTime);
			dfs(newPos, end);
		}
		
	}
	static void printBoard(int[][] visited) {
		for(int i = 0; i < row; i++) {
				System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String[] input = br.readLine().trim().split(" ");
		col = Integer.parseInt(input[0]);
		row = Integer.parseInt(input[1]);
		
		input = br.readLine().trim().split(" ");
		int startC = Integer.parseInt(input[0]);
		int startR = Integer.parseInt(input[1]);
		int endC = Integer.parseInt(input[2]);
		int endR = Integer.parseInt(input[3]);
		
		
		graph = new char[row + 1][col + 1];
		visited = new int[row + 1][col + 1];
		
		for (int r = 1; r <= row; r++) {
			String line = br.readLine();
			for (int c = 1; c <= col; c++) {
				graph[r][c] = line.charAt(c - 1);
			}
		}
		//for dfs
		for (int i = 1; i <= row; i++) {
			Arrays.fill(visited[i], MAX_TIME);
		}
		result = new Position(0, 0, MAX_TIME);
		dfs(new Position(startR, startC, 0), new Position(endR, endC, 0));
		
		//bfs(new Position(startR, startC, 0), new Position(endR, endC, 0));
		sb.append(result).append('\n');
		System.out.println(sb.toString());
		br.close();
	}	
}