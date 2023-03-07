import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//17143 낚시왕
//1. 낚시왕 이동, 왼쪽 에서 오른쪽으로, 가장 오른쪽 열 도착하면 이동 멈춤
//2. 낚시왕 위치 상어 중 땅과 제일 가까운 상어 잡는다
//3. 상어 이동
	// speed % (r * 2 - 2) 만큼 이동 
public class Main_17143_G1_낚시왕_전임송 {
	static final int EMPTY = 0;
	
	static int[] dr = {-1, 1, 0, 0}; //위,아래, 오른쪽, 왼쪽
	static int[] dc = {0, 0, 1, -1};
	
	static int R;
	static int C;
	static int M;
	static int[][] board;
	static List<Shark> sharks;
	static int totalCaught = 0;
	static class Shark {
		int row;
		int col;
		int speed;
		int dRow;
		int dCol;
		int size;
		int number;
		boolean alive;

		public Shark(int row, int col, int speed, int dRow, int dCol, int size, int number) {
			super();
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dRow = dRow;
			this.dCol = dCol;
			this.size = size;
			this.number = number;
			this.alive = true;
		}

		public void move() { //상어 이동
			if (!alive) {
				return;
			}
			int movement = 0;
			if (dRow != 0) {
				movement = speed % (R * 2 - 2);
			}
			if (dCol != 0) {
				movement = speed % (C * 2 - 2);
			}
			for (int i = 0; i < movement; i++) {
				int newR = row + dRow;
				int newC = col + dCol;
				if (newR <= 0 || newC <= 0 || newR > R || newC > C) {
					dRow *= -1;
					dCol *= -1;
					newR = row + dRow;
					newC = col + dCol;
				}
				row = newR;
				col = newC;
			}
			takePlace();
		}
		
		public void takePlace() {
			if (board[row][col] == EMPTY) { //빈자리면 차지
				board[row][col] = this.number;
				return;
			}
			Shark otherShark = sharks.get(board[row][col]);
			if (otherShark.size > this.size) { //존재하는 상어보다 작을 경우
				this.killed();
				return;
			}
			otherShark.killed(); //존재하는 상어보다 클 경우
			board[row][col] = this.number;
		}
		
		public void killed() {
			this.alive = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		M = Integer.parseInt(input[2]);
		board = new int[R + 1][C + 1];
		sharks = new ArrayList<>(10);
		sharks.add(null); //0자리에 추가
		for (int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int s = Integer.parseInt(input[2]);
			int d = Integer.parseInt(input[3]) - 1;
			int z = Integer.parseInt(input[4]);
			sharks.add(new Shark(r, c, s, dr[d], dc[d], z, i));
			board[r][c] = i; //보드에 상어 추가
		}
		startFishing();
		System.out.println(totalCaught);
	}
	
	public static void startFishing() {
		for (int col = 1; col <= C; col++) {
			catchShark(col);
			resetBoard();
			moveSharks();
		}
	}
	
	public static void catchShark(int col) {
		for (int row = 1; row <= R; row++) {
			if (board[row][col] > 0) {
				Shark shark = sharks.get(board[row][col]);
				board[row][col] = EMPTY;
				shark.killed();
				totalCaught += shark.size;
				return;
			}
		}
	}
	
	public static void resetBoard() {
		for (int r = 1; r <= R; r++) {
			Arrays.fill(board[r], 0);
		}
	}
	
	public static void moveSharks() {
		int numberOfSharks = sharks.size();
		for (int i = 1; i < numberOfSharks; i++) {
			sharks.get(i).move();
		}
	}
}
