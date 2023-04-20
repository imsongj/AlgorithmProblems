import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * 9019 DSLR
 * bfs
 * d s l r 하나씩 시도해본다.
 * 
 */

public class Main {		
	static final int DIGITS = 4;
	static final int MAX_NUMBER = 9_999;
	static final int MIN_NUMBER = 0;
	static final int MOD = 10_000;
	
	static class Case {
		int number;
		String path;
		public Case(int number, String path) {
			super();
			this.number = number;
			this.path = path;
		}
		
	}
	static int A;
	static int B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			String[] input = br.readLine().split(" ");
			A = Integer.parseInt(input[0]);
			B = Integer.parseInt(input[1]);
			bfs();
		}
	}
	public static void bfs() {
		Queue<Case> queue = new ArrayDeque<>();
		queue.add(new Case(A, ""));
		while (!queue.isEmpty()) {
			Case current = queue.poll();
			int newNumber;
			newNumber = D(current.number);
			if (newNumber == B) {
				System.out.println(current.path + "D");
				return;
			}
			queue.add(new Case(newNumber, current.path + "D"));
			
			newNumber = S(current.number);
			if (newNumber == B) {
				System.out.println(current.path + "S");
				return;
			}
			queue.add(new Case(newNumber, current.path + "S"));
			
			newNumber = L(current.number);
			if (newNumber == B) {
				System.out.println(current.path + "L");
				return;
			}
			queue.add(new Case(newNumber, current.path + "L"));
			
			newNumber = R(current.number);
			if (newNumber == B) {
				System.out.println(current.path + "R");
				return;
			}
			queue.add(new Case(newNumber, current.path + "R"));
		}
	}
	public static int D(int target) {
		int result = target * 2;
		if (result > MAX_NUMBER) {
			result = result % MOD;
		}
		return result;
	}
	
	public static int S(int target) {
		int result = target - 1;
		if (result < MIN_NUMBER) {
			result = MAX_NUMBER;
		}
		return result;
	}
	
	public static int L(int target) {
		int firstDigit = target / 1000;
		int result = (target % 1000) * 10 + firstDigit;
		return result;
	}
	
	public static int R(int target) {
		int lastDigit = target % 10;
		int result = (target / 10) + lastDigit * 1000;
		return result;
	}
}
