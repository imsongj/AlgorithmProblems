import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//5656 벽돌깨기
//구슬 떨어트리는 위치 순열 탐색 -> 중복 순열 사용해야함
//깨질 벽돌 표시 - 재귀 사용
//벽돌 제거 후 중력 적용

//계속 중복적으로 깨면서 반복할 필요가 없다, 순열에서 차례대로 제거 유지

public class Solution_5656_모의_벽돌깨기_전임송 { 
	static final int EMPTY = 0;
	static int[] dr = {0, 0, -1, 1}; 
	static int[] dc = {-1, 1, 0, 0};
	
	static int N;
	static int row;
	static int col;
	static int[][] bricks;
	static int[][] bricksCopy;
	static int[] order;
	
	static boolean[][] destroyed;
	
	static int minBrickCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			col = Integer.parseInt(input[1]);
			row = Integer.parseInt(input[2]);
			bricks = new int[row][col];
			bricksCopy = new int[row][col];
			order = new int[N];
			for (int r = 0; r < row; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < col; c++) {
					bricks[r][c] = Integer.parseInt(input[c]);
				}
			}
			
			minBrickCount = Integer.MAX_VALUE;
			getPermutation(0);
			System.out.printf("#%d %d%n", t, minBrickCount);
		}
		br.close();
	}
	
	public static void getPermutation(int count) { //중복 순열
		if (count == N) {
			runSimulation();
			minBrickCount = Math.min(minBrickCount, countBricks());
			return;
		}
		for (int i = 0; i < col; i++) {
			order[count] = i;
			getPermutation(count + 1);
		}
	}
	
	public static void runSimulation() {
		makeCopy();
		for (int dropCol : order) {
			dropMarble(dropCol);
		}
	}
	
	public static void makeCopy() {
		for (int r = 0; r < row; r++) {
			bricksCopy[r] = Arrays.copyOf(bricks[r], col);
		}
	}
	
	public static void dropMarble(int dropCol) {
		int startRow = findStartRow(dropCol);
		if (startRow < 0) {
			return;
		}
		destroyed = new boolean[row][col];
		destoryBrick(startRow, dropCol);
		remove();
		applyGravity();
	}
	
	public static void destoryBrick(int r, int c) { 
		destroyed[r][c] = true; //중앙 칸 제거 표시
		for (int d = 0; d < 4; d++) { //4방향으로 제거
			destoryInDirection(r, c, d);
		}
		
	}
	
	public static void remove() { //제거 표시된 벽돌 제거
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (destroyed[r][c]) {
					bricksCopy[r][c] = EMPTY;
				}
			}
		}
	}
	
	public static void applyGravity() { //빈 공간이 있을 경우 벽돌을 밑으로 이동한다.
		for (int r = row - 2; r >= 0; r--) { //row -2 부터 확인
			for (int c = 0; c < col; c++) {
				if (bricksCopy[r][c] != EMPTY && bricksCopy[r + 1][c] == EMPTY) { //아래 칸이 비여있는 경우
					int newR = r + 1;
					while (newR + 1 < row && bricksCopy[newR + 1][c] == EMPTY) {
						newR++;
					}
					bricksCopy[newR][c] = bricksCopy[r][c];
					bricksCopy[r][c] = EMPTY;
				}
			}
		}
	}
	
	public static void destoryInDirection(int r, int c, int direction) {
		int amount = bricksCopy[r][c];
		for (int i = 1; i < amount; i++) {
			int newR = r + dr[direction] * i;
			int newC = c + dc[direction] * i;
			
			if (newR < 0 || newR >= row || newC < 0 || newC >= col) { //가장자리 체크
				return;
			}
			if (bricksCopy[newR][newC] == EMPTY || destroyed[newR][newC]) { //빈 칸은 통과
				continue;
			}
			destoryBrick(newR, newC);
		}
	}
	
	public static int findStartRow(int dropCol) {
		for (int r = 0; r < row; r++) {
			if (bricksCopy[r][dropCol] > 0) {
				return r;
			}
		}
		return -1; //벽돌이 없는 경우
	}
	
	public static int countBricks() {
		int count = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (bricksCopy[r][c] > 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void print(int[][] bricks) {
		for (int r = 0; r < row; r++) {
			System.out.println(Arrays.toString(bricks[r]));
		}
		System.out.println();
	}
	public static void printDestroyed() {
		for (int r = 0; r < row; r++) {
			System.out.println(Arrays.toString(destroyed[r]));
		}
		System.out.println();
	}
}