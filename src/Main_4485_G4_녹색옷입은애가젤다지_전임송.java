import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//4485 녹색옷입은애가젤다지?
/*
 * 어떤 정점에 도착하면 그 정점에서 갈수있는  정점마다 pq에 정점까지의 거리 + 갈수있는 정점까지의 거리를 추가
 */
public class Main_4485_G4_녹색옷입은애가젤다지_전임송 {
	static final int START_R = 0;
	static final int START_C = 0;
	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	static class Position implements Comparable<Position> {
		int r;
		int c;
		int weight;
		public Position(int r, int c, int weight) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
		}
		@Override
		public int compareTo(Position o) {
			return weight-o.weight;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Position [r=").append(r).append(", c=").append(c).append(", weight=").append(weight)
					.append("]");
			return builder.toString();
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			int[][] board = new int[N][N];
			String[] line;
			for (int r = 0; r < N; r++) {
				line = br.readLine().split(" ");
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(line[c]);
				}
			}
			int[][] distance = new int[N][N];
			for (int r = 0; r < N; r++) {
				Arrays.fill(distance[r], Integer.MAX_VALUE);
			}
			boolean[][] visited = new boolean[N][N];
			PriorityQueue<Position> queue = new PriorityQueue<>();
			queue.add(new Position(START_R, START_C, board[START_R][START_C]));
			//visited[START_R][START_C] = true;
			distance[START_R][START_C] = board[START_R][START_C];
			Position current;
			outerLoop:
			while (!queue.isEmpty()) {
				current = queue.poll();
				visited[current.r][current.c] = true;
//				System.out.println(current);
				for (int d = 0; d < 4; d++) {
					int newR = current.r + dr[d];
					int newC = current.c + dc[d];
					
					if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
						continue;
					}
					if (visited[newR][newC]) {
						continue;
					}
					if(newR == N-1 && newC == N-1) {
						distance[newR][newC] = Math.min(distance[newR][newC], 
								distance[current.r][current.c] + board[newR][newC]);
						break outerLoop;
					}
					if (distance[newR][newC] > distance[current.r][current.c] + board[newR][newC]) {
						distance[newR][newC] = distance[current.r][current.c] + board[newR][newC];
						queue.add(new Position(newR, newC, distance[newR][newC]));
					}
				}
			}
			sb.append("Problem ").append(tc).append(": ").append(distance[N - 1][N - 1]).append("\n");
			tc++;
		}
		System.out.println(sb.toString());
	}
}
