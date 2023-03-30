import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//1149 RGB거리
/*
 * N * 3 배열 dp
 * n번째 집의 값은 다른색깔 n-1번쨰 집중 더 작은 수
 */

public class Main_1149_S1_RGB거리_전임송 {	
	static final int RED = 0;
	static final int GREEN = 1; 
	static final int BLUE = 2; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] line;
		int[][] prices = new int[N][3];
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			prices[i][0] = Integer.parseInt(line[0]);
			prices[i][1] = Integer.parseInt(line[1]);
			prices[i][2] = Integer.parseInt(line[2]);
		}
		
		int[][] dp = new int[N][3];
		
		dp[0][RED] = prices[0][RED];
		dp[0][GREEN] = prices[0][GREEN];
		dp[0][BLUE] = prices[0][BLUE];
		
		for (int i = 1; i < N; i++) {
			dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + prices[i][RED];
			dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + prices[i][GREEN];
			dp[i][BLUE] = Math.min(dp[i - 1][GREEN], dp[i - 1][RED]) + prices[i][BLUE];
		}
		System.out.println(Math.min(dp[N - 1][RED], Math.min(dp[N - 1][GREEN], dp[N - 1][BLUE])));
	}
}
