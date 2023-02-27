package practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Algo1_서울_13반_전임송 {
	static final int SQUARE_SIZE = 10;
	static final int BOARD_SIZE = 100;
	static final int OUTSIDE = 1;
	static final int INSIDE = 2;
	static final int EMPTY = 0;
	
	static int[] squareR;
	static int[] squareC;
	
	static int[][] covered;
	public static void main(String[] args) throws IOException {
		//covered 배열에 어떤 부분이 겹치는지 확인
		//테두리만 기록
		//모든 칸에서 dfs, visited 기록
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //스카프 개수 입력
		squareR = new int[N];
		squareC = new int[N];
		covered = new int[BOARD_SIZE + 1][BOARD_SIZE + 1];
		String[] input;
		for (int i  = 0; i < N; i++) {
			input = br.readLine().split(" ");
			squareC[i] = Integer.parseInt(input[0]); //스카프 위치 입력
			squareR[i] = Integer.parseInt(input[1]);
		}
		
		for (int i = 0; i < N; i++) { //스카프 정보 초기화
			for (int r = 0; r < SQUARE_SIZE; r++) {
				for (int c = 0; c < SQUARE_SIZE; c++) {
					if ((r == 0 || c == 0 || r == SQUARE_SIZE - 1 || c == SQUARE_SIZE - 1)
							&& covered[squareR[i] + r][squareC[i] + c] != 2) { //테두리인 경우
						covered[squareR[i] + r][squareC[i] + c] = OUTSIDE;
						continue;
					}
					covered[squareR[i] + r][squareC[i] + c] = INSIDE; //테두리가 아닌경우 
				}
			}
		}
		
		int count = 0;
		boolean fromOutside;
		for (int r = 0; r <= BOARD_SIZE; r++) { //가로방향 카운트 왼쪽에서 오른쪽으로
			fromOutside = true;
			for (int c= 0; c <= BOARD_SIZE; c++) {
				if (covered[r][c] == EMPTY) {
					fromOutside = true;
					continue;
				}
				if (fromOutside && covered[r][c] == OUTSIDE) {
					count++;
					fromOutside = false;
					continue;
				}
			}
		}
		
		for (int r = 0; r <= BOARD_SIZE; r++) { //가로방향 카운트 오른쪽에서 왼쪽으로
			fromOutside = true;
			for (int c= BOARD_SIZE; c >= 0; c--) {
				if (covered[r][c] == EMPTY) {
					fromOutside = true;
					continue;
				}
				if (fromOutside && covered[r][c] == OUTSIDE) {
					count++;
					fromOutside = false;
					continue;
				}
			}
		}
		for (int c = 0; c <= BOARD_SIZE; c++) { //세로방향 카운트 위에서 아래로
			fromOutside = true;
			for (int r= 0; r <= BOARD_SIZE; r++) {
				if (covered[r][c] == EMPTY) {
					fromOutside = true;
					continue;
				}
				if (fromOutside && covered[r][c] == OUTSIDE) {
					count++;
					fromOutside = false;
					continue;
				}
			}
		}
		for (int c = 0; c <= BOARD_SIZE; c++) { //세로방향 카운트 아래에서 위로
			fromOutside = true;
			for (int r= BOARD_SIZE; r >= 0; r--) {
				if (covered[r][c] == EMPTY) {
					fromOutside = true;
					continue;
				}
				if (fromOutside && covered[r][c] == OUTSIDE) {
					count++;
					fromOutside = false;
					continue;
				}
			}
		}
		System.out.println(count);
	}
	
	public static void print(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}
