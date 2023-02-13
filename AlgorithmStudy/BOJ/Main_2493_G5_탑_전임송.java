package algorithm.boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Main_2493_G5_탑_전임송 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		int n =  Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		int[] building = new int[n];

		Deque<Integer> stack = new ArrayDeque<Integer>(10);
		
		for (int i = 0; i < n; i++) {
			building[i] = Integer.parseInt(input[i]);
			int answer = 0;
			while(!stack.isEmpty()) {
				int index = stack.pop();
				if (building[index] > building[i]) {
					answer = index + 1;
					stack.push(index);
					break;
				}
			}
			stack.push(i);
			sb.append(answer).append(" ");
		}
		bw.append(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}	
}