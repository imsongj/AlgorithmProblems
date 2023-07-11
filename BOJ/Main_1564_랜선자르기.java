import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1564 랜선 자르기
 * 완전 탐색? 이분탐색?
 * 
 */
public class Main_1564_랜선자르기 {	
	public static int K;
	public static int N;
	public static int[] cable;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		K = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		
		cable = new int[K];
		int longest = 0;
		for (int i = 0; i < K; i++) {
			int cableLength = Integer.parseInt(br.readLine());
			cable[i] = cableLength;
			if (cableLength > longest) {
				longest = cableLength;
			}
		}
		long left = 0;
		long right = (long)Integer.MAX_VALUE + 1;
		
		while (left + 1 < right) {
			long mid = (right + left) / 2;
			if (check(mid)) {
				left = mid;
				continue;
			}
			right = mid;
		}
		System.out.println(left);
	}
	
	public static boolean check(long length) {
		long sum = 0;
		for (int i = 0; i < K; i++) {
			sum += cable[i] / length;
		}
		if (sum >= N) {
			return true;
		}
		return false;
	}
}