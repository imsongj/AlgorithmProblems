package algorithm.update;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
	static int N;
	static int M;
	static int K;
	static int[][] originalBoard;
	static int[][] newBoard;
	static int[] order;
	static List<int[]> rotations;
	
	static int minSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		originalBoard = new int[N][M];
		newBoard = new int[N][M];
		order = new int[K];
		rotations = new ArrayList<int[]>(K);
		
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				originalBoard[r][c] = Integer.parseInt(input[c]);
			}
		}
		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int[] rotation = new int[3];
			for (int j = 0; j < 3; j++) {
				rotation[j] = Integer.parseInt(input[j]);
			}
			rotations.add(rotation);
		}
		minSum = 100_000;
		//permutation(0, 0);
		permutation2();
		
		sb.append(minSum);
		System.out.println(sb.toString());
		br.close();
	}	
	
	public static void permutation(int count, int flag) {
		if (count == K) {
			performRotations();
			return;
		}
		for (int i = 0; i < K; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}
			order[count] = i;
			permutation(count + 1, flag | 1 << i);
		}
	}
	
	private static void performRotations( ) {
		copyBoard(originalBoard, newBoard);
		for (int i : order) { //perform rotation in the given order
			rotate(rotations.get(i));
		}
		getMinRow(newBoard);
		
	}
	
	private static void rotate(int[] rotation) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		int startR = rotation[0] - rotation[2] - 2;
		int startC = rotation[1] - rotation[2] - 2;
		int endR = rotation[0] + rotation[2];
		int endC = rotation[1] + rotation[2];
		int layers = rotation[2];
		for (int s = layers; s > 0 ; s--) {
			startR++;
			startC++;
			endR--;
			endC--;
			int currentR = startR;
			int currentC = startC;
			int currentD = 0;
			int prevNum = newBoard[currentR][currentC];
			int newNum = 0;
			for (int r = 0; r < s * 8; r++) {
				int newR = currentR + dr[currentD];
				int newC = currentC + dc[currentD];
				if (newR < 0 || newR >= N || newC < 0 || newC >= M
						|| newR < startR || newR > endR || newC < startC || newC > endC) {//범위체크
					currentD = (currentD + 1);
					newR = currentR + dr[currentD];
					newC = currentC + dc[currentD];
				}
				newNum = prevNum;
				prevNum = newBoard[newR][newC];
				newBoard[newR][newC] = newNum;
				
				currentR = newR;
				currentC = newC;
			}
		}
	}
	
	private static void getMinRow(int[][] board) {
		for (int r = 0; r < N; r++) {
			int sum = 0;
			for (int c = 0; c < M; c++) {
				sum += board[r][c];
			}
			if (minSum > sum) {
				minSum = sum;
			}
		}
		
	}
	
	private static void copyBoard(int[][] original, int[][] copy) {
		for (int r = 0; r < N; r++) {
			copy[r] = Arrays.copyOf(original[r], M);
		}
	}
	
	private static void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}
	
	public static void permutation2() {
		for (int i = 0; i < K; i++) {
			order[i] = i;
		}
		do {
			performRotations();
		} while (nextPermutation());
	}
	
	public static boolean nextPermutation() {
		int i = K - 1;
		while (i > 0 && order[i - 1] >= order[i]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		int j = K - 1;
		while (order[i - 1] >= order[j]) {
			j--;
		}
		swap(order, i - 1, j);
		int k = K - 1;
		while(k > i) {
			swap(order, k--, i++);
		}
		return true;
	}
	
	public static void swap(int[] target, int i, int j) {
		int tmp = target[i];
		target[i] = target[j];
		target[j] = tmp;
	}
}
