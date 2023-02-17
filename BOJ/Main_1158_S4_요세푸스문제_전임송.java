package algorithm.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

class Main_1158_S4_요세푸스문제_전임송 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		
		List<Integer> table = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			table.add(i);
		}
		int killed = 0;
		int index = 0;
		
		String[] order = new String[n];
		
		bw.append('<');
		
		while (killed < n) {
			index = (index + k - 1) % table.size();
			order[killed++] = Integer.toString(table.remove(index)); 
		}
		bw.append(String.join(", ", order));
		bw.append('>');
		bw.flush();
		br.close();
		bw.close();
	}	
}