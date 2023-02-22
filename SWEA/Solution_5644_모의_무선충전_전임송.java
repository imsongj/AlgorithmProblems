import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//사용자 이동후 두 사용자의 bc를 보고 최종 합을 결정
public class Solution_5644_모의_무선충전_전임송 { 
	static final int N = 10;
	static final int A_INITIAL_R = 1;
	static final int A_INITIAL_C = 1;
	static final int B_INITIAL_R = 10;
	static final int B_INITIAL_C = 10;
	
	static int M;
	static int A;
	static int[][] charger;
	
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();

			String[] input = br.readLine().split(" ");
			M = Integer.parseInt(input[0]);
			A = Integer.parseInt(input[1]);
			
			int[] AMove = new int[M + 1];
			int[] BMove = new int[M + 1];
			input = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				AMove[i] = Integer.parseInt(input[i]);
			}
			input = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				BMove[i] = Integer.parseInt(input[i]);
			}
			charger = new int[A][4];
			for (int i = 0; i < A; i++) {
				input = br.readLine().split(" ");
				charger[i][0] = Integer.parseInt(input[0]); //r
				charger[i][1] = Integer.parseInt(input[1]); //c
				charger[i][2] = Integer.parseInt(input[2]); //range
				charger[i][3] = Integer.parseInt(input[3]); //power
			}
					
			sb.append(String.format("#%d %d", t, move(AMove, BMove)));
			System.out.println(sb.toString());
		}
		br.close();
	}
	
	public static int move(int[] AMove, int[] BMove) {
		int totalSum = 0;
		int[] ACharger = new int[A];
		int[] BCharger = new int[A];
		int AR = A_INITIAL_R;
		int AC = A_INITIAL_C;
		int BR = B_INITIAL_R;
		int BC = B_INITIAL_C;
		
		for (int m = 0; m <= M; m++) { //0부터 M 이동 이후 자리까지
			for (int a = 0; a < A; a++) {
				ACharger[a] = 0;
				BCharger[a] = 0; //배열 초기화
				if (getDistance(AR, AC, charger[a][1], charger[a][0]) <= charger[a][2]) { //charger 범위안에 있으면
					ACharger[a] = charger[a][3];
				}
				if (getDistance(BR, BC, charger[a][1], charger[a][0]) <= charger[a][2]) { //charger 범위안에 있으면
					BCharger[a] = charger[a][3];
				}
			}
			totalSum += getMaxCombination(ACharger, BCharger);
			//이동
			AR = AR + dr[AMove[m]];
			AC = AC + dc[AMove[m]];
			BR = BR + dr[BMove[m]];
			BC = BC + dc[BMove[m]];
		}
		return totalSum;
	}
	
	public static int getMaxCombination(int[] ACharger, int[] BCharger) {
		int maxSum = 0;
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int sum = ACharger[i] + BCharger[j];
				
				if (i == j && (ACharger[i] > 0 && BCharger[j] > 0)) {
					sum /= 2;
				}
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
	}
	
	public static int getDistance(int ar, int ac, int br, int bc) {
		return Math.abs(ar - br) + Math.abs(ac - bc);
	}
}
