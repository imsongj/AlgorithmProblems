package algorithm.swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

public class Solution_1233_D4_사칙연산유효성_전임송 {
	private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = 10;
		
		for (int t = 1; t <= testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			String[] input;
			int result = 1;
			for (int i = 0; i < n; i++) {
				input = br.readLine().split(" ");
				if (result == 0) {
					continue;
				}
				if (input.length == 4 && isNumeric(input[1])) {
					result = 0;
				}
				if (input.length == 2 && !isNumeric(input[1])) {
					result = 0;
				}
			}
			bw.append("#").append(Integer.toString(t)).append(" ").append(Integer.toString(result)).append("\n");
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
