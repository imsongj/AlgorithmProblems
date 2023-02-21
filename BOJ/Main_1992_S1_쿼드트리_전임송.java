import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1992_S1_쿼드트리_전임송 {
	static int N;
	static int[][] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		String line;
		for (int r = 0; r < N; r++) {
			line = br.readLine();
			for (int c = 0; c < N; c++) {
				board[r][c] = line.charAt(c) - '0';
			}
		}
		System.out.println(compress(N, 0, 0));
	}
	//2로 나누면서 모든 칸이 그 숫자면 리턴, 아니면 다시 나누기
	public static String compress(int n, int startR, int startC) {
		StringBuilder sb = new StringBuilder();
		if (isCompressable(n, startR, startC)) {
			return Integer.toString(board[startR][startC]);
		}
		int newN = n / 2;
		return sb.append("(")
				.append(compress(newN, startR, startC))
				.append(compress(newN, startR, startC + newN))
				.append(compress(newN, startR + newN, startC))
				.append(compress(newN, startR + newN, startC + newN))
				.append(")")
				.toString();
	}
	
	public static boolean isCompressable(int n, int startR, int startC) {
		int value = board[startR][startC];
		for (int r = startR; r < startR + n; r++) {
			for (int c = startC; c < startC + n; c++) {
				if (board[r][c] != value) {
					return false;
				}
			}
		}
		return true;
	}
}
