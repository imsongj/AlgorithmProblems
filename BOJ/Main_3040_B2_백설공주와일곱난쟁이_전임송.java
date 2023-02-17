package algorithm.boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3040_B2_백설공주와일곱난쟁이_전임송 {	
	static final int N = 9;
	static final int M = 7;
	static int[] numbers;
	static int[] picks;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		numbers = new int[N];
		picks = new int[M];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		combination(0, 0);
		System.out.println(sb.toString());
		br.close();
	}	
	
	public static void combination(int count, int start) {
		if (count == M) {
			int sum = 0;
			for (int i = 0; i < M; i++) {
				sum += picks[i];
			}
			if (sum == 100) {
				for (int i = 0; i < M; i++) {
					sb.append(picks[i]).append("\n");
				}
			}
			return;
		}
		for (int i = start; i < N; i++) {
			picks[count] = numbers[i];
			combination(count + 1, i + 1);
		}
	}
}