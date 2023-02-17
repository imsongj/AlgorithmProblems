package algorithm.boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

class Main_2961_S2_도영이가만든맛있는음식_전임송 {
static final int MAX_VALUE = 1_000_000_000;
	
	static int N;
	static int minDiff;
	
	static int[] sour;
	static int[] bitter;
	static boolean[] selected;
	
	public static void combination(int count) {
		if (count == N) {
			int totalSour = 1;
			int totalBitter = 0;
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					totalSour *= sour[i]; 
					totalBitter += bitter[i];
					flag = true;
				}
			}
			if (!flag) {
				return;
			}
			int thisDiff = Math.abs(totalSour - totalBitter); 
			
			minDiff = Math.min(minDiff, thisDiff);
			return;
		}
		selected[count] = true;
		combination(count + 1);
		selected[count] = false;
		combination(count + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		sour = new int[N];
		bitter = new int[N];
		selected = new boolean[N];
		minDiff = MAX_VALUE;
		
		String[] input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			sour[i] = Integer.parseInt(input[0]);
			bitter[i] = Integer.parseInt(input[1]);
		}
		
		combination(0);
		
		bw.append(Integer.toString(minDiff));
		
		bw.flush();
		br.close();
		bw.close();
	}	
}