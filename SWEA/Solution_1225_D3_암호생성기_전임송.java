package algorithm.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1225_D3_암호생성기_전임송 {
	static final int LENGTH = 8;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = 10;
		
		for (int t = 1; t <= testCase; t++) {
			br.readLine();
			String[] input = br.readLine().split(" ");
			Deque<Integer> queue = new ArrayDeque<>(LENGTH);
			for (int i = 0; i < LENGTH; i++) {
				queue.add(Integer.parseInt(input[i]));
			}
			
			int i = 0;
			while(true) {
				int head = queue.pop() - ((i % 5) + 1); 
				if (head < 0) {
					head = 0;
				}
				queue.add(head);
				if (head == 0) {
					break;
				}
				i++;
			}
			bw.append(String.format("#%d ", t));
			for (int num : queue) {
				bw.append(String.format("%d ", num));
			}
			bw.append('\n');
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
