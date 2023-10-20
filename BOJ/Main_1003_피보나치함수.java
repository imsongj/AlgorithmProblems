import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1003_피보나치함수 {
    /*
    1003 피보나치함수

   	*/
	public static final int MAX = 41;
    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		long[] fibonacci0 = new long[MAX];
		long[] fibonacci1 = new long[MAX];
		fibonacci0[0] = 1;
		fibonacci1[0] = 0;
		fibonacci0[1] = 0;
		fibonacci1[1] = 1;
		for (int i = 2; i < MAX; i++) {
			fibonacci0[i] = fibonacci0[i - 1] + fibonacci0[i - 2];
			fibonacci1[i] = fibonacci1[i - 1] + fibonacci1[i - 2];
		}
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(fibonacci0[N] + " " + fibonacci1[N]);
		}
	}
}