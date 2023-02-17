package algorithm.update;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5215_D3_햄버거다이어트_전임송 { //5215
	static int N;
	static int L;
	static int[] taste;
	static int[] cal;
	static int maxTaste;
	static void subset(int count, int tasteSum, int calSum) {
		if (calSum > L) {
			return;
		}
		maxTaste = Math.max(maxTaste, tasteSum);
		if (count == N) {
			return;
		}
		subset(count + 1, tasteSum + taste[count], calSum + cal[count]);
		subset(count + 1, tasteSum, calSum);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			StringBuilder sb = new StringBuilder();
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			L = Integer.parseInt(input[1]);
			taste = new int[N];
			cal = new int[N];
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				taste[i] = Integer.parseInt(input[0]);
				cal[i] = Integer.parseInt(input[1]);
			}
			//bit masking
			/*int numberOfSubsets = 1 << n;
			int maxScore = 0;
			for (int i = 1; i < numberOfSubsets; i++) {
				int tasteSum = 0;
				int calSum = 0;
				for (int j = 0; j < n; j++) {
					if ((i & 1 << j) != 0) {
						tasteSum += taste[j];
						calSum += cal[j];
					}
				}
				if (calSum <= l) {
					maxScore = Math.max(maxScore, tasteSum);
				}
			}*/
			maxTaste = 0;
			subset(0, 0, 0);
			sb.append('#').append(t).append(' ').append(maxTaste);
			System.out.println(sb.toString());
		}
		br.close();
	}
}
