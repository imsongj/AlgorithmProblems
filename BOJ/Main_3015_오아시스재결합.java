import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 3015 오아시스 재결합
 * stack
 * 사이에 키가 큰 사람이 있으면 쌍이 아니다 -> 키가 변하지않으면 스택 사이즈 만큼 +1
 * 1. top 보다 키가 큰 사람이 들어오면 자기보다 키가 큰 사람이 top 에 있거나 stack 이 비였을때까지 pop 하며 +1
 * 2. top 보다 키가 작은 사람이 들어오면 stack 에 넣으면서 +1
 * 3. 키가 같은 사람이 들어오면 -> map 에 연속된 같은 키 사람 카운트, tmpstack 쓰면 시간초과
 */

public class Main_3015_오아시스재결합 {		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long count = 0;
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> sameHeight = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			while (!stack.isEmpty() && stack.peek() < input) {
				int popped = stack.pop();
				sameHeight.put(popped, sameHeight.get(popped) - 1);
				count++;
			}
			if (!stack.isEmpty() && stack.peek() == input) {
				count += sameHeight.get(input);
				if (stack.size() > sameHeight.get(input)) {
					count++;
				}
				stack.add(input);
				sameHeight.put(input, sameHeight.get(input) + 1);
				continue;
			}
			if (!stack.isEmpty()) {
				stack.add(input);
				if (sameHeight.containsKey(input)) {
					sameHeight.put(input, sameHeight.get(input) + 1);
				} else {
					sameHeight.put(input, 1);
				}
				count++;
				continue;
			}
			if (stack.isEmpty()) {
				stack.add(input);
				sameHeight.put(input, 1);
				continue;
			}
		}
		System.out.println(count);
	}
}
