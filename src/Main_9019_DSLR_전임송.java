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

public class Main_9019_DSLR_전임송 {		
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
	static int[] DResult;
	static int[] SResult;
	static int[] LResult;
	static int[] RResult;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		DResult = new int[MAX_NUMBER + 1];
		SResult = new int[MAX_NUMBER + 1];
		LResult = new int[MAX_NUMBER + 1];
		RResult = new int[MAX_NUMBER + 1];
		for (int i = 0; i <= MAX_NUMBER; i++) {
			DResult[i] = D(i);
			SResult[i] = S(i);
			LResult[i] = L(i);
			RResult[i] = R(i);
		}
		for (int test = 0; test < T; test++) {
			String[] input = br.readLine().split(" ");
			A = Integer.parseInt(input[0]);
			B = Integer.parseInt(input[1]);
			bfs();
		}
	}
	public static void bfs() {
	    visited = new boolean[MAX_NUMBER+1];
		Queue<Case> queue = new ArrayDeque<>();
		visited[A] = true;
		queue.add(new Case(A, ""));
		while (!queue.isEmpty()) {
			Case current = queue.poll();
			int newNumber = 0;
			for (int i = 1; i <= DIGITS; i++) {
			    String newPath = current.path;
			    if (i == 1) {
			    	newNumber = DResult[current.number];
			        newPath += "D";
			    }
			    if (i == 2) {
			    	newNumber = SResult[current.number];
			        newPath += "S";
			    }
			    if (i == 3) {
			    	newNumber = LResult[current.number];
			        newPath += "L";
			    }
			    if (i == 4) {
			    	newNumber = RResult[current.number];
			        newPath += "R";
			    }
			if (newNumber == B) {
			    System.out.println(newPath);
				return;
			}
			if (visited[newNumber]) {
			    continue;
			}
			queue.add(new Case(newNumber, newPath));
			visited[newNumber] = true;
		    }
			
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
