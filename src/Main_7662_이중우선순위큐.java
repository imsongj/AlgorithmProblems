import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;


/*
 * 7662 이중 우선순위 큐
 * keep numbers of operations
 * two pq
 * 
 * 
 */
public class Main_7662_이중우선순위큐 {	
	public static final String INSERT = "I";
	public static final String DELETE = "D";
	public static final String GET_MAX = "1";
	public static final String GET_MIN = "-1";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> tm = new TreeMap<>();
			for (int i = 0; i < k; i++) {
				String[] input = br.readLine().split(" ");
				if (input[0].equals(INSERT)) {
					int number = Integer.parseInt(input[1]);
					tm.put(number, tm.getOrDefault(number, 0) + 1);
					continue;
				}
				if (input[0].equals(DELETE)) {
					if (tm.size() == 0) {
						continue;
					}
					int polledNumber = 0;
					if (input[1].equals(GET_MAX)) {
						polledNumber = tm.lastKey();
					}
					if (input[1].equals(GET_MIN)) {
						polledNumber = tm.firstKey();
					}
					if (tm.put(polledNumber, tm.get(polledNumber) - 1) == 1) {
						tm.remove(polledNumber);
					}
				}
			}
			if (tm.size() == 0) {
				sb.append("EMPTY\n");
				continue;
			}
			sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
		}
		System.out.println(sb);
	}
}