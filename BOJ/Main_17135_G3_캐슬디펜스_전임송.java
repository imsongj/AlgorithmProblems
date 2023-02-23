import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

//17135 캐슬 디펜스

//궁수 위치 조합 별로
	//거리 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다

class Position {
	int r;
	int c;
	int distance;
	public Position(int r, int c, int distance) {
		super();
		this.r = r;
		this.c = c;
		this.distance = distance;
	} 
}

public class Main_17135_G3_캐슬디펜스_전임송 {
	static final int NUMBER_OF_ARCHERS = 3;
	static final int ENEMY_SPEED = 1;
	static final int ENEMY = 1;
	static final int EMPTY = 0;
	
	static int N;
	static int M;
	static int D;
	static int[][] originalMap;
	static int[][] currentMap;
	static boolean[][] attacked;
	static boolean[][] killed;
	static int[] combination;
	
	static int maxKill = 0;
	
	static int[] dr = {0, -1, 0};
	static int[] dc = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		D = Integer.parseInt(input[2]);
		
		originalMap = new int[N + 1][M];
		currentMap = new int[N + 1][M];
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				originalMap[r][c] = Integer.parseInt(input[c]);
			}
		}
		combination = new int[NUMBER_OF_ARCHERS];
		
		getCombination(0, 0);
		
		System.out.println(maxKill);
	}
	
	public static void getCombination(int count, int start) {
		if (count == NUMBER_OF_ARCHERS) {
			runDefence();
			return;
		}
		for (int i = start; i < M; i++) {
			combination[count] = i;
			getCombination(count + 1, i + 1);
		}
	}
	
	public static void runDefence() {
		int totalKill = 0;
		copyOriginalMap();
		while (!isCleared()) {
			killed = new boolean[N][M];
			totalKill += attack();
			killEnemy();
			moveEnemy();
		}
		
		maxKill = Math.max(maxKill, totalKill);
	}
	
	public static int attack() {
		int roundKill = 0;
		Queue<Position> queue = new ArrayDeque<>();
		for (int a = 0; a < 3; a++) {
			attacked = new boolean[N][M];
			queue.add(new Position(N, combination[a], 0));
			Position current;
			while (!queue.isEmpty()) {
				current = queue.poll();
				for (int d = 0; d < 3; d++) {
					int targetR = current.r + dr[d];
					int targetC = current.c + dc[d];
					int targetDistance = current.distance + 1;
					if (targetDistance > D) {
						continue;
					}
					if (targetR < 0 || targetR >= N || targetC < 0 || targetC >= M) {
						continue;
					}		
					if (attacked[targetR][targetC]) {
						continue;
					}
					attacked[targetR][targetC] = true;
					queue.add(new Position(targetR, targetC, targetDistance));
					if (currentMap[targetR][targetC] == ENEMY) {
						if (!killed[targetR][targetC]) {
							killed[targetR][targetC] = true;
							roundKill++;
						}
						queue.clear();
						break;
					}
				}
			}
		}
		return roundKill;
	}
	
	public static void killEnemy() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (killed[r][c]) {
					currentMap[r][c] = EMPTY;
				}
			}
		}
	}
	
	public static void moveEnemy() {
		for (int i = N - 1; i >= 1; i--) {
			currentMap[i] = currentMap[i - 1];
		}
		currentMap[0] = new int[M];
	}
	
	private static boolean isCleared() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (currentMap[r][c] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void copyOriginalMap() {
		for (int i = 0; i < N; i++) {
			currentMap[i] = Arrays.copyOf(originalMap[i], M);
		}
	}
}
