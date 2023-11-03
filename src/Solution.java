import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(
			s.solution(6, new int[] {7, 10})
		);
	}
	public long solution(int n, int[] times) {
		int minTime = Integer.MAX_VALUE;
		for (int time : times) {
			minTime = Math.min(minTime, time);
		}
		long maxFinishTime = minTime * n;
		long minFinishTime = 0;
		long mid;
		while (minFinishTime + 1 != maxFinishTime) {
			mid = (minFinishTime + maxFinishTime) / 2;
			long sum = getSum(times, mid);

			if (sum < n) {
				minFinishTime = mid;
			}
			if (sum >= n) {
				maxFinishTime = mid;
			}
		}
		return maxFinishTime;
	}

	public long getSum(int[] times, long m) {
		long sum = 0;
		for (int time : times) {
			sum += m / time;
		}
		return sum;
	}
}