import java.util.Arrays;

public class Practice {
	static int N = 3;
	static int M = 2;
	static int[] pick;
	static int[] selected;
	public static void main(String[] args) {
		pick = new int[N];
		permutation(0, 0);
		System.out.println();
		combination(0, 0);
		subset(0, 0);
	}
	
	public static void permutation(int count, int flag) {
		if (count == N) {
			System.out.println(Arrays.toString(pick));
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}
			pick[count] = i;
			permutation(count + 1, flag | 1 << i);
		}
	}
	
	public static void combination(int count, int start) {
		if (count == M) {
			System.out.println(Arrays.toString(pick));
			return;
		}
		for (int i = start; i < N; i++) {
			pick[count] = i;
			combination(count + 1, i + 1);
		}
	}
	
	public static void subset(int count, int flag) {
		if (count == N) {
			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) != 0) {
					continue;
				}
				System.out.print(i);
			}
			System.out.println();
			return;
		}
		subset(count + 1, flag | 1 << count);
		subset(count + 1, flag);
		
	}
}
