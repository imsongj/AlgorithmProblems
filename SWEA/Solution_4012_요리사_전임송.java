import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_4012_요리사_전임송 { 
	static int N;
	static int[][] table;
	static int minScore;
	static boolean[] selectedIngredients;
	static int[] secondIngredients;
	static int[] pair;
	static int totalScore;
	
	public static void pickIngredients(int count, int start) {
		if (count == N / 2) {
			totalScore = 0;
			getScore(0, 0, true);
			int firstScore = totalScore;
			
			totalScore = 0;
			getScore(0, 0, false);
			int secondScore = totalScore;
			
			minScore = Math.min(Math.abs(firstScore - secondScore), minScore);
			return;
		}
		for (int i = start; i < N; i++) {
			selectedIngredients[i] = true;
			pickIngredients(count + 1, i + 1);
			selectedIngredients[i] = false;
		}
	}
	
	public static void getScore(int count, int flag, boolean isFirst) {
		if (count == 2) {
			totalScore += table[pair[0]][pair[1]];
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((i & flag << i) != 0) {
				continue;
			}
			if (selectedIngredients[i] != isFirst) {
				continue;
			}
			pair[count] = i;
			getScore(count + 1, flag | 1 << i, isFirst);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			table = new int[N][N];
			selectedIngredients = new boolean[N];
			pair = new int[2];
			minScore = Integer.MAX_VALUE;
			String[] input;
			for (int r = 0; r < N; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < N; c++) {
					table[r][c] = Integer.parseInt(input[c]);
				}
			}
			pickIngredients(0, 0);
			sb.append(String.format("#%d %d", t, minScore));
			System.out.println(sb.toString());
		}
		br.close();
	}
	
	
	
	
}
