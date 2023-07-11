import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;


/*
 * 13549 숨바꼭질 3
 * 
 *
 * 
 */
public class Main_숨바꼭질3 {	
	public static final int MAX_LENGTH = 100_000;
	public static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		int[] time = new int[MAX_LENGTH + 1];
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		queue.add(N);
		time[N] = 1;
		int next;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			int currentTime = time[current];
			if (current == K) {
				System.out.println(currentTime - 1);
				break;
			}
			next = current * 2;
			if (next <= MAX_LENGTH && next >= 0 && time[next] == 0) {
				queue.add(next);
				time[next] = currentTime;
			}
			next = current - 1;
			if (next <= MAX_LENGTH && next >= 0 && time[next] == 0) {
				queue.add(next);
				time[next] = currentTime + 1;
			}
			
			
			next = current + 1;
			if (next <= MAX_LENGTH && next >= 0 && time[next] == 0) {
				queue.add(next);
				time[next] = currentTime + 1;
			}
		}
	}
}