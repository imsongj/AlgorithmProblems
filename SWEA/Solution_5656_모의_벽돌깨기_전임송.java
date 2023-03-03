import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//5656 벽돌깨기
//구슬 떨어트리는 위치 순열 탐색 -> 중복 순열 사용해야함
//깨질 벽돌 표시 - 재귀 사용
//벽돌 제거 후 중력 적용

//순열 구할때 마다 계속 중복적으로 깨면서 반복할 필요가 없다, 순열 구하는 동시에 차례대로 제거한 벽돌 상태 유지

public class Solution { 
	static final int EMPTY = 0;
	static int[] dr = {0, 0, -1, 1}; 
	static int[] dc = {-1, 1, 0, 0};
	
	static int N;
	static int row;
	static int col;
	static int[][] bricksOriginal;

	static boolean[][] destroyed;
	
	static int minBrickCount;
	static int brickCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			col = Integer.parseInt(input[1]);
			row = Integer.parseInt(input[2]);
			bricksOriginal = new int[row][col];
			brickCount = 0;
			for (int r = 0; r < row; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < col; c++) {
					bricksOriginal[r][c] = Integer.parseInt(input[c]);
					if (bricksOriginal[r][c] > 0) {
						brickCount++;
					}
				}
			}
			
			minBrickCount = Integer.MAX_VALUE;
			
			getPermutation(0, bricksOriginal, 0);
			if (minBrickCount == Integer.MAX_VALUE) {
				minBrickCount = 0;
			}
			System.out.printf("#%d %d%n", t, minBrickCount);
		}
		br.close();
	}
	
	public static boolean getPermutation(int count, int[][] bricks, int destroyedCount) { //중복 순열
		if (count == N) { 
			minBrickCount = Math.min(minBrickCount, brickCount - destroyedCount);
			return false;
		}
		if (destroyedCount == brickCount) { //모든 벽돌 제거한 경우
			minBrickCount = 0;
			return true;
		}
		for (int dropCol = 0; dropCol < col; dropCol++) {
			int startRow = findStartRow(bricks, dropCol); //제거될 벽돌이 없는 열은 스킵
			if (startRow < 0) {
				continue;
			}
			
			int[][] bricksCopy = makeCopy(bricks);
			int newDestroyedCount = destroyedCount + runSimulation(bricksCopy, startRow, dropCol);
			
			if (getPermutation(count + 1, bricksCopy, newDestroyedCount)) { //구슬 해당 위치에 떨어트리는 경우 전달
				return true;
			} 
		}
		return false;
	}
	
	public static int findStartRow(int[][] bricksCopy, int dropCol) {
		for (int r = 0; r < row; r++) {
			if (bricksCopy[r][dropCol] > 0) {
				return r;
			}
		}
		return -1; //벽돌이 없는 경우
	}
	
	public static int runSimulation(int[][] bricks, int startRow, int dropCol) {
		destroyed = new boolean[row][col];
		destoryBrick(bricks, startRow, dropCol);
		int destroyedCount = remove(bricks);
		applyGravity(bricks);
		return destroyedCount;
	}
	
	public static int[][] makeCopy(int[][] original) {
		int[][] bricksCopy = new int[row][col];
		for (int r = 0; r < row; r++) {
			bricksCopy[r] = Arrays.copyOf(original[r], col);
		}
		return bricksCopy;
	}
	
	public static void destoryBrick(int[][] bricksCopy, int r, int c) { 
		destroyed[r][c] = true; //중앙 칸 제거 표시
		for (int d = 0; d < 4; d++) { //4방향으로 제거
			int amount = bricksCopy[r][c];
			for (int i = 1; i < amount; i++) {
				int newR = r + dr[d] * i;
				int newC = c + dc[d] * i;
				
				if (newR < 0 || newR >= row || newC < 0 || newC >= col) { //가장자리 체크
					continue;
				}
				if (bricksCopy[newR][newC] == EMPTY || destroyed[newR][newC]) { //빈 칸은 통과
					continue;
				}
				destoryBrick(bricksCopy, newR, newC);
			}
		}
		
	}
	
	public static int remove(int[][] bricksCopy) { //제거 표시된 벽돌 제거
		int destroyedCount = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (destroyed[r][c]) {
					bricksCopy[r][c] = EMPTY;
					destroyedCount++;
				}
			}
		}
		return destroyedCount;
	}
	
	public static void applyGravity(int[][] bricksCopy) { //빈 공간이 있을 경우 벽돌을 밑으로 이동한다.
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
}