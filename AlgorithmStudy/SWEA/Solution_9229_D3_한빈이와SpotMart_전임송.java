package algorithm.swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_9229_D3_한빈이와SpotMart_전임송 {
	static final int MAX_COUNT = 2;
	static int N;
	static int[] weights;
	static List<int[]> pairs;
	static int[] pair;
	public static void combination(int count, int start) {
		if (count == MAX_COUNT) {
			pairs.add(Arrays.copyOf(pair, MAX_COUNT));
			return;
		}
		for (int i = start; i < weights.length; i++) {
			pair[count] = weights[i];
			combination(count + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			weights = new int[N];
			pairs = new ArrayList<int[]>(10);
			pair = new int[MAX_COUNT];
			input = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(input[i]);
			}
			combination(0, 0);
			int max = -1;
			for (int[] pair : pairs) {
				int sum = pair[0] + pair[1];
				if (sum > m) {
					continue;
				}
				max = Math.max(sum, max);
			}

			bw.append("#").append(Integer.toString(t)).append(" ")
				.append(Integer.toString(max)).append("\n");
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
