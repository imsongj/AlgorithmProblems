import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//5658 보물상자 비밀번호
// N / 4 만큼 돌려야한다
// sliding window

public class Solution { 
	static final int NUMBER_OF_SIDES = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
		 	int K = Integer.parseInt(input[1]);
		 	int window = N / 4;
		 	char[] characters= new char[N];
			String line = br.readLine();
			for (int r = 0; r < N; r++) {
				characters[r] = line.charAt(r);
			}
			
			Set<Integer> numberSet = new HashSet<>();
			List<Integer> numbers = new ArrayList<>(10);
			StringBuilder sb;
			for (int start = 0; start < window; start++) {
				for (int i = 0; i < NUMBER_OF_SIDES; i++) {
					sb = new StringBuilder();
					for (int j = 0; j < window; j++) {
						int index = (start + i * window + j) % N;
						sb.append(characters[index]);
					}
					int number = Integer.parseInt(sb.toString(), 16);
					if (numberSet.contains(number)) {
						continue;
					}
					numberSet.add(number);
					numbers.add(number);
				}
			}
			Collections.sort(numbers, Collections.reverseOrder());
			System.out.printf("#%d %d%n", t, numbers.get(K - 1));
		}
		br.close();
	}
}