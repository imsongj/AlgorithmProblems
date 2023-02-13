package algorithm.boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main_12981_S2_DNA비밀번호_전임송 {
	static final char A = 'A';
	static final char C = 'C';
	static final char G = 'G';
	static final char T = 'T';
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
	
		int n = Integer.parseInt(input[0]);
		int pwLength = Integer.parseInt(input[1]);
		String DNA =  br.readLine();
		int DNALength = DNA.length();
		int total = 0;
		
		input = br.readLine().split(" ");
		int aCount = Integer.parseInt(input[0]);
		int cCount = Integer.parseInt(input[1]);
		int gCount = Integer.parseInt(input[2]);
		int tCount = Integer.parseInt(input[3]);
		
		int[] aSum = new int[n + 1];
		int[] cSum = new int[n + 1];
		int[] gSum = new int[n + 1];
		int[] tSum = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			aSum[i] = aSum[i - 1];
			cSum[i] = cSum[i - 1];
			gSum[i] = gSum[i - 1];
			tSum[i] = tSum[i - 1];
			if (DNA.charAt(i - 1) == A) {
				aSum[i]++;
			}
			if (DNA.charAt(i - 1) == C) {
				cSum[i]++;
			}
			if (DNA.charAt(i - 1) == G) {
				gSum[i]++;
			}
			if (DNA.charAt(i - 1) == T) {
				tSum[i]++;
			}
		}
		
		for (int i = 0; i <= DNALength - pwLength; i++) {
			if (aSum[i + pwLength] - aSum[i] < aCount) {
				continue;
			}
			if (cSum[i + pwLength] - cSum[i] < cCount) {
				continue;
			}
			if (gSum[i + pwLength] - gSum[i] < gCount) {
				continue;
			}
			if (tSum[i + pwLength] - tSum[i] < tCount) {
				continue;
			}
			total++;
		}
		
		bw.append(Integer.toString(total));
		
		bw.flush();
		br.close();
		bw.close();
	}	
}