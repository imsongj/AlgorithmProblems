package algorithm.swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_1228_암호문1_전임송 {
	static final int PRINT_LENGTH= 10;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = 10;
		
		for (int t = 1; t <= testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			List<Integer> password = new LinkedList<Integer>();
			
			for (int i = 0; i < n; i++) {
				password.add(Integer.parseInt(input[i]));
			}
			int numberOfCommands = Integer.parseInt(br.readLine());
			input = br.readLine().split(" ");
			int commandCount = 0;
			while (numberOfCommands > 0) {
				commandCount++;
				int index = Integer.parseInt(input[commandCount++]);
				int numbers = Integer.parseInt(input[commandCount++]);
				for (int i = 0; i < numbers; i++) {
					password.add(index++, Integer.parseInt(input[commandCount++]));
				}
				numberOfCommands--;
			} 
			bw.append(String.format("#%d ", t));
			for (int i = 0; i < PRINT_LENGTH; i++) {
				bw.append(String.format("%d ", password.get(i)));
			}
			bw.append('\n');
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
