package algorithm.boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

class Main_2164_S4_카드2_전임송 {
	public static int getLastCard(int n) {
		Deque<Integer> queue = new ArrayDeque<>();
		for (int num = 1; num <= n; num++) {
			queue.add(num);
		}
		while(queue.size() > 1) {
			queue.poll();
			queue.add(queue.poll());
		}
		return queue.poll();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		bw.append(Integer.toString(getLastCard(n)));
		
		bw.flush();
		br.close();
		bw.close();
	}	
}