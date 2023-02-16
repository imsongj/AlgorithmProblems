package algorithm.update;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Main_2563_색종이 {
	static final int WHITE_SIZE = 100;
	static final int BLACK_SIZE = 10;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n =  Integer.parseInt(br.readLine());
		String[] input;
		int[][] board = new int[WHITE_SIZE + 1][WHITE_SIZE + 1];
		for (int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			int l = Integer.parseInt(input[0]);
			int d = Integer.parseInt(input[1]);
			
			for (int r = 0; r < BLACK_SIZE; r++) {
				for (int c = 0; c < BLACK_SIZE; c++) {
					board[r + d][c + l]++;
				}
			}
		}
		
		int count = 0;
		for (int r = 0; r < WHITE_SIZE; r++) {
			for (int c = 0; c < WHITE_SIZE; c++) {
				if (board[r][c] > 0) {
					count++;
				}
			}
		}
		System.out.println(count);
		br.close();
	}	
}