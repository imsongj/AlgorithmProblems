import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(
			s.solution(3,  new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})
		);
	}
	static int[] parents;
	int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if (parentA != parentB) {
			parents[parentA] = parentB;
		}
	}

	public int solution(int n, int[][] computers) {
		int answer = 0;
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (computers[i][j] == 1 && i != j) {
					union(i, j);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (parents[i] == i) answer++;
		}
		return answer;
	}


}