package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RobinKarpPattern {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			char[] text = br.readLine().trim().toCharArray();
			char[] pattern = br.readLine().trim().toCharArray();
			
			long pHash = 0;
			long tHash = 0;
			long power = 1;
			long base = 26 + 26 + 1; //소문자 + 대문자 + 띄어쓰기
			long mod = 1_000_000_000;
			int tLen = text.length;
			int pLen = pattern.length;
			Deque<Integer> list = new ArrayDeque<>();
			
			for (int i = 0; i < pLen; i++) {
				pHash = (pHash * base) % mod;
				pHash = (pHash + pattern[i]) % mod;
				tHash = (tHash * base) % mod;
				tHash = (tHash + text[i]) % mod;
				
				if (i < pLen - 1) {
					power = (power * base) % mod;
				}
				
			}
			System.out.println(power);
			if (pHash == tHash) {
				list.add(1);
			}
			for (int i = 1; i <= tLen - pLen; i++) {
				tHash = (((tHash - (text[i - 1] * power) % mod) % mod * base)
						+ text[i + pLen - 1]) % mod;
				if (pHash == tHash) {
					list.add(i + 1);
				}
			}
			System.out.println(list.size());
			for (int index : list) {
				System.out.println(index + " ");
			}
		}
	}
}
