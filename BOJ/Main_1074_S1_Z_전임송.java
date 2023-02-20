import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1074_S1_Z_전임송 {
	static int targetR;
	static int targetC;
	static int count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		targetR = Integer.parseInt(input[1]);
		targetC = Integer.parseInt(input[2]);
		count = 0;
		addCount(1<<n, 0, 0);
	}
	public static void addCount(int n, int currentR, int currentC) {
		if (currentR == targetR && currentC == targetC) {
			System.out.printf("%d", count);
			return;
		}
		int newN = n / 2;
		if (currentR <= targetR && targetR < currentR + n
				&& currentC <= targetC && targetC < currentC + n) {
			addCount(newN, currentR, currentC);
			addCount(newN, currentR, currentC + newN);
			addCount(newN, currentR + newN, currentC);
			addCount(newN, currentR + newN, currentC + newN);
			return;
		}
		count += n * n;
	}
}
