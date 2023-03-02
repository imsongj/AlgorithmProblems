import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//16236 아기상어
//먹을 수 있는 물고기가 없으면 종료
//현재 상어 위치에서 bfs로 제일 가까운, 먹을수 있는 물고기 탐색
public class Main_16236_G3_아기상어_전임송 {
	static final int SHARK = 9;
	static final int EMPTY = 0;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Position {
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int N;
	static int[][] board;
	static int totalTime = 0;
	static int sharkR;
	static int sharkC;
	static int sharkSize = 2;
	static int consumedFishes = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		String[] input;
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(input[c]);
				if (board[r][c] == SHARK) {
					sharkR = r;
					sharkC = c;
				}
			}
		}
		
		while (true) {
			if (!findFish()) {
				System.out.println(totalTime);
				break;
			}
		}
	}
	
	public static boolean findFish() {
		Queue<Position> queue = new ArrayDeque<>();
		int[][] visited = new int[N][N];
		boolean[][] consumable = new boolean[N][N];
		
		queue.add(new Position(sharkR, sharkC));
		visited[sharkR][sharkC] = 1; //1부터 시작해서 시간값에서 1 빼야함
		
		while (!queue.isEmpty()) { //bfs
			Position current = queue.poll();
			int currentTime = visited[current.r][current.c];
			for (int d = 0; d < 4; d++) { //사방탐색
				int newR = current.r + dr[d];
				int newC = current.c + dc[d];
				int newTime = currentTime + 1;
				if (newR < 0 || newR >= N || newC < 0 || newC >= N) {
					continue;
				}
				if (visited[newR][newC] > 0 || board[newR][newC] > sharkSize) {
					continue;
				}
				if (board[newR][newC] != EMPTY && board[newR][newC] < sharkSize) {
					consumable[newR][newC] = true; //먹을 수 있는 물고기 위치 저장
				}
				visited[newR][newC] = newTime;
				queue.add(new Position(newR, newC));
			}
		}
		
		int minTime = Integer.MAX_VALUE;
		int minR = -1;
		int minC = -1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (consumable[r][c] && visited[r][c] < minTime) { //먹을 물고기 선택
					minTime = visited[r][c];
					minR = r;
					minC = c;
				}
			}
		}
		
		if (minTime == Integer.MAX_VALUE) { //먹을 수 있는 물고기를 찾지 못한경우
			return false;
		}
		consumeFish(minR, minC, visited[minR][minC] - 1);
		return true;
	}
	
	public static void consumeFish(int r, int c, int time) { //물고기 잡아먹을때 처리
		board[r][c] = EMPTY;
		board[sharkR][sharkC] = EMPTY;
		consumedFishes++;
		growShark();
		totalTime += time;
		sharkR = r;
		sharkC = c;
	}
	
	public static void growShark() {
		if (consumedFishes == sharkSize) {
			sharkSize++;
			consumedFishes = 0;
		}
	}
}
