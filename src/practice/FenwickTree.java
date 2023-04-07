package practice;

public class FenwickTree {
	static int[] tree;
	static int N;
	public static void main(String[] args) {
		int[] nums = {3,5,2,4,0,8,1,5,3,4,1,0,6,7,7,3}; 
		N = nums.length;
		tree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			update(i, nums[i - 1]);
		}
		
		System.out.println(rangeSum(3,6));
	}
	
	public static void update(int i, int num) {
		while (i < N) {
			tree[i] += num;
			i += (i & -i); //k 값 (제일 마지막 1)
		}
	}
	
	public static long sum(int i) {
		long ans = 0;
		while (i > 0) {
			ans += tree[i];
			i -= (i & -i);
		}
		return ans;
	}
	public static long rangeSum(int from, int to) {
		return sum(to) - sum(from);
	}
}
