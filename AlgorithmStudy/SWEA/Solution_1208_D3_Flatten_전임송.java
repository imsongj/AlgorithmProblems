package algorithm.swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Solution_1208
{
	static BufferedWriter bw;
	static BufferedReader br;
	
	static final int COLUMN = 100;

	public static int flatten() throws IOException {
		int count = Integer.parseInt(br.readLine());
		int[] columns = new int[COLUMN];
		
		String[] input = br.readLine().split(" ");
		
		for (int c = 0; c < COLUMN; c++) {
			columns[c] = Integer.parseInt(input[c]);
		}
		int diff = 0;
		for (int i = 0; i < count; i++) {
			Arrays.sort(columns);
			columns[COLUMN - 1]--;
			columns[0]++;
		}
		Arrays.sort(columns);
		diff = columns[COLUMN - 1] - columns[0];
		return diff;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			bw.append(String.format("#%d %d%n", testCase, flatten()));
			bw.flush();
		}	
		br.close();
		bw.close();
	}
}