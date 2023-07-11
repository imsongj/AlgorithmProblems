import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;


/*
 * 2448 별찍기 11
 * 
 * ..*
 * .*.*
 * *****
 * 
 */
public class Main_별찍기11 {	
	public static final String STAR = "*";
	public static boolean[][] board = new boolean[3100][6200];
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		printStars(N, 0, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N * 2; j++) {
				if (board[i][j]) {
					sb.append(STAR);
					continue;
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void printStars(int height, int startR, int startC) {
		if (height == 3) {
			board[startR][startC + 2] = true;
			board[startR + 1][startC + 1] = true;
			board[startR + 1][startC + 3] = true;
			for (int i = 0; i < 5; i++) {
				board[startR + 2][startC + i] = true;
			}
			return;
		}
		printStars(height / 2, startR, startC + height / 2);
		printStars(height / 2, startR + height / 2, startC);
		printStars(height / 2, startR + height / 2, startC + height);
	}
}