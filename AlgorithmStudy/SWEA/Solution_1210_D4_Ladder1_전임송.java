package algorithm.swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution_1210_D4_Ladder1_전임송 {
	static BufferedWriter bw;
	static BufferedReader br;
	
	static final int ROW = 100;
	static final int COL = 100;
	static final int START = 2;
	static final int LADDER = 1;
	static final int END = 0;
	
	static final int LEFT_ROW = 0;
	static final int LEFT_COL = -1;
	static final int RIGHT_ROW = 0;
	static final int RIGHT_COL = 1;
	static final int UP_ROW = -1;
	static final int UP_COL = 0;
	
	static final String LEFT = "left";
	static final String RIGHT = "RIGHT";
	static final String UP = "UP";
	
	static int[][] board;
	
	static int start;
	
	public static void findWay(int currentR, int currentC, String previousDirection) {
		if (currentR == END) {
			start = currentC;
			return;
		}
		int newR;
		int newC;
		int flag = 0;
		newR = currentR + LEFT_ROW;
		newC = currentC + LEFT_COL;
		if (newR >= 0 && newR < ROW && newC >= 0 && newC < COL
				&& board[newR][newC] == LADDER
				&& !previousDirection.equals(RIGHT)) {
			flag = 1;
			findWay(newR, newC, LEFT);
		}
		newR = currentR + RIGHT_ROW;
		newC = currentC + RIGHT_COL;
		if (newR >= 0 && newR < ROW && newC >= 0 && newC < COL
				&& board[newR][newC] == LADDER
				&& !previousDirection.equals(LEFT)) {
			flag = 1;
			findWay(newR, newC, RIGHT);
		}
		newR = currentR + UP_ROW;
		newC = currentC + UP_COL;
		if (newR >= 0 && newR < ROW && newC >= 0 && newC < COL
				&& board[newR][newC] == LADDER
				&& flag == 0) {
			findWay(newR, newC, UP);
		}
	}
	
	public static int ladder() throws IOException {
		board = new int[ROW][COL];
		int startR = 0, startC = 0;
		for (int r = 0; r < ROW; r++) {
			String[] input = br.readLine().split(" ");
			for (int c = 0; c < COL; c++) {
				board[r][c] = Integer.parseInt(input[c]);
				if (board[r][c] == START) {
					startR = r;
					startC = c;
				}
			}
		}
		findWay(startR, startC, UP);
		return start;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("res/input.txt"));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			br.readLine();
			bw.append(String.format("#%d %d%n", testCase, ladder()));
			bw.flush();
		}	
		br.close();
		bw.close();
	}
}