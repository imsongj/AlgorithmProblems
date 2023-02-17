package algorithm.boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_11659_S3_구간합구하기4_전임송 {
	static int[] sums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		
		sums = new int[n + 1];
		input = br.readLine().split(" ");
		for (int i = 1; i <= n; i++) {
			sums[i] = Integer.parseInt(input[i - 1]);
			if (i > 1) {
				sums[i] += sums[i - 1]; 
			}
		}
		
		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int sum = sums[end] - sums[start - 1];
			bw.append(Integer.toString(sum)).append('\n');
			bw.flush();
		}
		br.close();
		bw.close();
	}
}