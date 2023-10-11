import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_2623_음악프로그램 {
    /*
		2623 음악프로그램
		위상정렬
   	*/
	static List<Integer> answer = new ArrayList<>();
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] indegree;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		indegree = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			for (int j = 1; j < input.length - 1; j++) {
				int a = Integer.parseInt(input[j]);
				int b = Integer.parseInt(input[j + 1]);
				indegree[b]++;
				graph.get(a).add(b);
			}
		}
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}

		for (int i = 0; i < N; i++) {
			if (queue.isEmpty()) {
				System.out.println(0);
				return;
			}
			int current = queue.poll();
			answer.add(current);
			for (int adj : graph.get(current)) {
				indegree[adj]--;
				if (indegree[adj] == 0) {
					queue.add(adj);
				}
			}
		}
		for (int i : answer) {
			System.out.println(i);
		}
    }
}