import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * 2531 회전초밥
 * 
 */

public class Main_2531_S1_회전초밥_전임송 {		
	static int N;
	static int k;
	static int c;
	static int[] exists;
	static int[] sushi;
	static int typeCount;
	static int maxType;
	static boolean cFlag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int d = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		c = Integer.parseInt(input[3]);
		exists = new int[d + 1];
		sushi = new int[N + k - 1];
		typeCount = 0;
		maxType = 0;
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			sushi[i] = number;
		}
		for (int i = 0; i < k - 1; i++) {
			sushi[N + i] = sushi[i];
		}
		//System.out.println(Arrays.toString(sushi));
		for (int i = 0; i < k; i++) {
			add(sushi[i]);
		}
		maxType = typeCount + checkCoupon();
		for (int i = 0; i < N - 1; i++) {
			remove(sushi[i]);
			add(sushi[i + k]);
			if (maxType < typeCount + checkCoupon()) {
				maxType = typeCount + checkCoupon();
			}
		}
		System.out.println(maxType);
	}
	
	public static void remove(int number) {
		exists[number]--;
		if (exists[number] == 0) {
			typeCount--;
		}
	}
	public static void add(int number) {
		if (exists[number] == 0) {
			typeCount++;
		}
		exists[number]++;
	}
	public static int checkCoupon() {
		if (exists[c] == 0) {
			return 1;
		}
		return 0;
	}
}
