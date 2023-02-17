import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2247_bank_도서관_전임송 {
	private static class Study implements Comparable<Study> {
		int start, end;

		public Study(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Study o) {
			if (start != o.start) {
				return start - o.start;
			}
			return end - o.end;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Study[] studies = new Study[n];
		String[] input;
		for (int i = 0; i < n; i++) {
			input = br.readLine().trim().split(" ");
			studies[i] = new Study(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		
		Arrays.sort(studies);
		int maxUsage = 0;
		int maxEmpty = 0;
		int usageStart = studies[0].start;
		int endTime = studies[0].end;
		
		for (int i = 1; i < n; i++) {
			if (studies[i].start <= endTime) { //사용 시간이 이어지는 경우
				endTime = Math.max(endTime, studies[i].end);
				continue;
			}
			maxUsage = Math.max(maxUsage, endTime - usageStart);
			maxEmpty = Math.max(maxEmpty, studies[i].start - endTime);
			usageStart = studies[i].start;
			endTime = studies[i].end;
		}
		maxUsage = Math.max(maxUsage, endTime - usageStart);
		System.out.printf("%d %d", maxUsage, maxEmpty);
	}
}
