import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//2115 벌꿀채취
//0. 길이 M의 가로 선택의 모든 경우의 수 탐색 후 개인 최고 수익 저장
//1.길이 M의 가로 선택 모든 경우의 수 탐색
	// 일꾼 1 가로 선택 후 남은 부분에 일꿀 2 가로 선택
//2. 해당 가로 선택에서 M의 모든 부분집합을 탐색
	//각 벌통 선택이 C를 초과하지 않아야한다.
	//3. 선택 가능한 부분집합의 수익 계산

public class Solution_2115_모의_벌꿀채취_전임송 { 
	
	static int N;
	static int M;
	static int C;
	static int[][] board;
	static int[][] profit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().trim().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			board = new int[N][N];
			profit = new int[N][N];
			for (int r = 0; r < N; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(input[c]);
				}
			}
			recordProfit();
			//print(profit);

			System.out.printf("#%d %d%n", t, getMaxProfit());
		}
		br.close();
	
	}
	
	public static void recordProfit() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N - (M - 1); c++) {
				profit[r][c] = calculateProfit(r, c);
			}
		}
	}
	
	public static int calculateProfit(int r, int c) {
		int maxProfit = 0;
		
		for (int s = 0; s < 1 << M; s++) {
			int amountSum = 0;
			int profitSum = 0;
			for (int i = 0; i < M; i++) {
				if ((s & 1 << i) != 0) { //벌통 선택 안된경우
					continue;
				}
				
				amountSum += board[r][c + i];
				profitSum += board[r][c + i] * board[r][c + i]; //꿀의 양 제곱
				//System.out.printf("%d %d %d", board[r][c + i], amountSum, profitSum);
			}
			if (amountSum > C) { //채취량보다 클 경우
				continue;
			}
			maxProfit = Math.max(maxProfit, profitSum);
		}
		return maxProfit;
	}
	
	public static int getMaxProfit() {
		int maxProfit = 0;
		
		for (int firstR = 0; firstR < N; firstR++) {
			for (int firstC = 0; firstC < N - (M - 1); firstC++) {
				for (int secondR = 0; secondR < N; secondR++) {
					for (int secondC = 0; secondC < N - (M - 1); secondC++) {
						if (secondR == firstR && doesOverlap(firstC,secondC)) {
							continue;
						}
						if (maxProfit < profit[secondR][secondC] + profit[firstR][firstC]) {
							//System.out.printf("%d %d %d %d%n", firstR, firstC, secondR, secondC);
						}
						maxProfit = Math.max(maxProfit, profit[secondR][secondC] + profit[firstR][firstC]);
					}
				}
			}
		}
		return maxProfit;
	}
	
	public static boolean doesOverlap(int firstC, int secondC) {
		for (int i = 0; i < M; i++) {
			if (secondC + i == firstC || firstC + i == secondC) { //겹치는지 확인
				return true;
			}
		}
		return false;
	}
	public static void print(int[][] board) {
		for (int r = 0; r < board.length; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
	}
}