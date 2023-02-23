import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//1697 숨바꼭질
class Position {
	int x;
	int time;
	public Position(int x, int time) {
		super();
		this.x = x;
		this.time = time;
	}
}
public class Main_1697_S1_숨바꼭질_전임송 {
	static final int START= 0;
	static final int END = 100_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		boolean[] visited = new boolean[END + 1];
		Queue<Position> queue = new ArrayDeque<>();
		queue.add(new Position(n, 0));
		if (n == k) {
			System.out.println(0);
			return;
		}
		
		Position current;
		while (!queue.isEmpty()) {
			current = queue.poll();
			
			int newX = current.x + 1;
			int newTime = current.time + 1;
			if (newX >= START && newX <= END && !visited[newX]) {
				queue.add(new Position(newX, newTime));
				visited[newX] = true;
				if (newX == k) {
					System.out.println(newTime);
					break;
				}
			}
			newX = current.x - 1;
			if (newX >= START && newX <= END && !visited[newX]) {
				queue.add(new Position(newX, newTime));
				visited[newX] = true;
				if (newX == k) {
					System.out.println(newTime);
					break;
				}
			}
			newX = current.x * 2;
			if (newX >= START && newX <= END && !visited[newX]) {
				queue.add(new Position(newX, newTime));
				visited[newX] = true;
				if (newX == k) {
					System.out.println(newTime);
					break;
				}
			}
		}
	}
}
