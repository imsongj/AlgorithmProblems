import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
 * 5430 AC
 * 
 * 
 */
public class Main_5430_AC {	
	public static final String ERROR_MESSAGE = "error\n";
	public static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		Deque<String> deque = new ArrayDeque<>();
		for (int i = 0; i < T; i++) {
			String commends = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			String[] arr = null;
			if (n > 0) {
				arr = input.substring(1, input.length() - 1).split(",");
				for (String str : arr) {
					deque.add(str);
				}
			}
			
			//System.out.println(deque.size() + " " + arr);
			//System.out.println(deque.isEmpty() + " test");
			operation(commends, deque);
		}
		System.out.println(sb);
	}
	public static void operation(String commends, Deque<String> deque) {
		int reversed = 0;
		for (int i = 0; i < commends.length(); i++) {
			char commend = commends.charAt(i);
			if (commend == 'R') {
				reversed = 1 - reversed;
				continue;
			}
			if (deque.isEmpty()) {
				sb.append(ERROR_MESSAGE);
				return;
			}
			if (reversed == 0) {
				deque.pollFirst();
				continue;
			}
			deque.pollLast();
		}
		sb.append("[");
		while (!deque.isEmpty() && true) {
			if (reversed == 1) {
				sb.append(deque.pollLast());
			}
			if (reversed == 0 ) {
				sb.append(deque.pollFirst());
			}
			if (deque.isEmpty()) {
				break;
			}
			sb.append(",");
		}
		sb.append("]\n");
	}
}