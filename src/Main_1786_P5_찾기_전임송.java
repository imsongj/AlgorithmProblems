import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//1786 찾기
/*
 * robin karp
 */

public class Main_1786_P5_찾기_전임송 {		

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		//System.out.println(Arrays.toString(text));
		//System.out.println(Arrays.toString(pattern));
		long pHash = 0;
		long tHash = 0;
		long power = 1;
		long base = 26 + 26 + 1; //소문자 + 대문자 + 띄어쓰기
		long mod = 1_000_000_007;
		int tLen = text.length;
		int pLen = pattern.length;
		Deque<Integer> list = new ArrayDeque<>();
		
		if (tLen < pLen) {
			System.out.println(0);
			return;
		}
		for (int i = 0; i < pLen; i++) {
			pHash = (pHash * base) % mod;
			pHash = (pHash + pattern[i]) % mod;
			tHash = (tHash * base) % mod;
			tHash = (tHash + text[i]) % mod;
			
			if (i < pLen - 1) {
				power = (power * base) % mod;
			}
		}
		if (pHash == tHash) {
			list.add(1);
		}
		for (int i = 1; i <= tLen - pLen; i++) {
			tHash = (((tHash - (text[i - 1] * power) % mod + mod) % mod * base)
					+ text[i + pLen - 1]) % mod;
			if (pHash == tHash) {
				list.add(i + 1);
			}
		}
		sb.append(list.size()).append('\n');
		for (int index : list) {
			sb.append(index).append(' ');
		}
		System.out.println(sb.toString());
	}
}
