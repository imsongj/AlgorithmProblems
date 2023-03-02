import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

//5656 벽돌깨기
//구슬 떨어트리는 위치 순열 탐색
//깨질 벽돌 표시 - 재귀 사용
//벽돌 제거 후 중력 적용
public class Solution { 
	static final int EMPTY = 0;
	static int[] dr = {0, 0, 1}; //윗쪽은 탐색할 필요 없다
	static int[] dc = {-1, 1, 0};
	
	static int N;
	static int row;
	static int col;
	static int[][] brick;
	static int[] order;
	
	static boolean[][] destroyed;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			col = Integer.parseInt(input[1]);
			row = Integer.parseInt(input[2]);
			brick = new int[row][col];
			order = new int[N];
			for (int r = 0; r < row; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < col; c++) {
					brick[r][c] = Integer.parseInt(input[c]);
				}
			}
			order = new int[] {2, 2, 6};
			runSimulation();
			System.out.printf("#%d %d%n", t, 1);
		}
		
		br.close();
	}
	
	public static void getPermutation(int count, int flag) {
		if (count == N) {
			runSimulation();
			return;
		}
		for (int i = 0; i < col; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}
			order[count] = i;
			getPermutation(count + 1, flag | 1 << i);
		}
	}
	
	public static void runSimulation() {
		for (int dropCol : order) {
			drop(dropCol);
		}
	}
	
	public static void drop(int dropCol) {
		int startRow = findStartRow(dropCol);
		destroyed = new boolean[row][col];
		getBricksToRemove(startRow, dropCol);
		remove();
		printBrick();
	}
	
	public static void remove() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (destroyed[r][c]) {
					brick[r][c] = EMPTY;
				}
			}
		}
	}
	public static void getBricksToRemove(int r, int c) {
		destroyed[r][c] = true; //중앙 칸 제거
		for (int d = 0; d < 3; d++) {
			removeDirection(r, c, d);
		}
		
	}
	
	public static void removeDirection(int r, int c, int direction) {
		int amount = brick[r][c];
		for (int i = 1; i < amount; i++) {
			int newR = r + dr[direction] * i;
			int newC = c + dc[direction] * i;
			
			if (newR < 0 || newR >= row || newC < 0 || newC >= col) { //가장자리 체크
				return;
			}
			if (brick[newR][newC] == EMPTY || destroyed[newR][newC]) { //빈 칸은 통과
				continue;
			}
			getBricksToRemove(newR, newC);
		}
	}
	
	public static int findStartRow(int dropCol) {
		for (int r = 0; r < row; r++) {
			if (brick[r][dropCol] > 0) {
				return r;
			}
		}
		return 0;
	}
	
	public static void printBrick() {
		for (int r = 0; r < row; r++) {
			System.out.println(Arrays.toString(brick[r]));
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