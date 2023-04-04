package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class KMP {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int tLen = text.length;
		int pLen = pattern.length;
		
		Deque<Integer> list =  new ArrayDeque<>();
		int[] fail = new int[pLen];
		
		//i : 접미사 포인터 1부터 시작
		//j : 접두사 포인터
		for (int i = 1, j = 0; i < pLen; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = fail[j - 1];
			}
			if (pattern[i] == pattern[j]) {
				fail[i] = ++j;
			}
		}
		for (int i = 0, j = 0; i < tLen; i++) {
			while (j > 0 && text[i] != pattern[j]) {
				j = fail[j - 1];
			}
			if (text[i] == pattern[j]) {
				if (j == pLen - 1) {
					list.add((i + 1) - pLen + 1);
					j = fail[j];
					//continue;
				} else {
					j++;
				}
				
			}
		}
		
		int count = list.size();
		System.out.println(count);
		StringBuilder sb = new StringBuilder();
		if (count > 0) {
			for (int index : list) {
				sb.append(index + " ");
			}
		}
		System.out.println(sb.toString());
	}
}
