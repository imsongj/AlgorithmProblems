package algorithm.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GreedyTest_1931_회의실배정 {
	private static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if (end != o.end) {
				return end - o.end;
			}
			return start - o.start;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[n];
		String[] input;
		for (int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			meetings[i] = new Meeting(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		
		Arrays.sort(meetings);
		int count = 0;
		int endTime = 0;
		for (int i = 0; i < n; i++) {
			if (meetings[i].start >= endTime) {
				count++;
				endTime = meetings[i].end;
			}
		}
		System.out.println(count);
	}
}
