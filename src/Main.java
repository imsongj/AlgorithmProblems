import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//1781 컵라면
/*
 * 데드라인별 숫자 카운트 데드라인이 빠른순, 컵라면 많은 순 정렬
 * 
 */

public class Main {	
	static class Problem implements Comparable<Problem> {
		int number;
		int deadline;
		int count;
		public Problem(int number, int deadline, int count) {
			super();
			this.number = number;
			this.deadline = deadline;
			this.count = count;
		}
		@Override
		public int compareTo(Problem o) {
			if (count > o.count) {
				return -1;
			}
			if (count < o.count) {
				return 1;
			}
			return o.deadline - deadline; 
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Problem [number=").append(number).append(", deadline=").append(deadline).append(", count=")
					.append(count).append("]");
			return builder.toString();
		}
	}
	static final int MAX_N = 200_001;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String[] line;
		int[] deadlineCount = new int[MAX_N]; 
		Problem[] problems = new Problem[N + 1];
		
		for (int i = 1; i <= N; i++) {
			line = br.readLine().split(" ");
			int deadline = Integer.parseInt(line[0]);
			deadlineCount[deadline]++;
			int count = Integer.parseInt(line[1]);
			problems[i] = new Problem(i, deadline, count);
		}
		Arrays.sort(problems, 1, N + 1);
		System.out.println(Arrays.toString(problems));
		int index = 1;
		int currentTime = 1;
		int total = 0;
		while (index <= N) {
			int thisDeadline = problems[index].deadline;
			if (thisDeadline < currentTime) {
				index++;
				continue;
			}
			//System.out.println(problems[index] + " " + currentTime);
			total += problems[index].count;
			currentTime++;
			index++;
			
		} //특정 시간 이하 데드라인중 가장 큰 것 고르기
		System.out.println(total);
	}
}
