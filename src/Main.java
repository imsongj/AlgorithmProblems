import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//18222 투에-모스문자열
//분할정복, k부터 시작, 1인경우 0
홀수면 1
public class Main {	
	static long K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		System.out.println(getNumber(K));
	}
	public static int getNumber(long length) {
		if (length == 1) {
			return 0;
		}
		
		return 1;
	}
}