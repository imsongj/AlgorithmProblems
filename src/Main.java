import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

//13913 숨바꼭질4
public class Main {
	static final int START= 0;
	static final int END = 20;
	
	static int[] prev;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		prev = new int[END + 1];
		int[] visitedTime = new int[END + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(n);
		prev[n] = -1;
		visitedTime[n] = 0;

		if (n == k) {
			System.out.printf("%d%n%d%n", 0, n);
			return;
		}
		
		int current;
		while (!queue.isEmpty()) {
			current = queue.poll();
			System.out.println(Arrays.toString(prev));
			System.out.println(Arrays.toString(visitedTime));
			int newX = current - 1;
			int newTime = visitedTime[current] + 1;
			if (newX >= START && newX <= END && visitedTime[newX] == 0) {
				queue.add(newX);
				prev[newX] = current;
				visitedTime[newX] = newTime;
				if (newX == k) {
					System.out.printf("%d%n%s", newTime, printPath(newX));
					break;
				}
			}
			
			newX = current + 1;
			if (newX >= START && newX <= END && visitedTime[newX] == 0) {
				queue.add(newX);
				prev[newX] = current;
				visitedTime[newX] = newTime;
				if (newX == k) {
					System.out.printf("%d%n%s", newTime, printPath(newX));
					break;
				}
			}
			
			newX = current * 2;
			if (newX >= START && newX <= END && visitedTime[newX] == 0) {
				queue.add(newX);
				prev[newX] = current;
				visitedTime[newX] = newTime;
				if (newX == k) {
					System.out.printf("%d%n%s", newTime, printPath(newX));
					break;
				}
			}
		}
	}
	
	public static String printPath(int prevIndex) {
		Deque<Integer> queue = new ArrayDeque<>();
		buildQueue(queue, prevIndex);
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			sb.append(queue.pollLast()).append(' ');
		}
		return sb.toString();
	}
	public static Deque<Integer>  buildQueue(Deque<Integer> queue, int prevIndex) {
		if (prev[prevIndex] == -1) {
			queue.add(prevIndex);
			return queue;
		}
		queue.add(prevIndex);
		return buildQueue(queue, prev[prevIndex]);
	}
}
