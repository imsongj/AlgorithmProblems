package algorithm.swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.HashMap;

public class Solution_1218_D4_괄호짝짓기_전임송 {
	static final Map<Character, Character> PAIRS = new HashMap<Character, Character>() {{
	    put('(',')');
	    put('[',']');
	    put('{','}');
	    put('<','>');
	}};
	public static int check(String input) {
		Deque<Character> stack = new ArrayDeque<>();
		int length = input.length();
		for (int i = 0; i < length; i++) {
			char thisP = input.charAt(i);
			if (PAIRS.containsKey(thisP)) {
				stack.push(thisP);
				continue;
			}
			
			if (stack.isEmpty()) {
				return 0;
			}

			if (PAIRS.get(stack.pop()) != thisP) {
				return 0;
			}
		}
		
		if (stack.isEmpty()) {
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = 10;
		
		for (int t = 1; t <= testCase; t++) {
			br.readLine();
			String input = br.readLine();
			bw.append(String.format("#%d %d%n", t, check(input)));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
