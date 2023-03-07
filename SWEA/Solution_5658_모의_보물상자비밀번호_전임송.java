import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

//5658 보물상자 비밀번호
// N / 4 만큼 돌려야한다
// sliding window
// keep int set remove duplicates

public class Solution_5658_모의_보물상자비밀번호_전임송 { 
 	static int N;
 	static int K;
 	static int window;
 	static char[] characters;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			K = Integer.parseInt(input[1]);
			characters = new char[N];
			window = N / 4;
			String line = br.readLine();
			for (int r = 0; r < N; r++) {
				characters[r] = line.charAt(r);
			}
			
			Set<Integer> numberSet = new HashSet<>();
			List<Integer> numbers = new ArrayList<>(10);
			StringBuilder sb;
			for (int start = 0; start < window; start++) {
				for (int i = 0; i < 4; i++) {
					sb = new StringBuilder();
					for (int j = 0; j < window; j++) {
						sb.append(characters[(start + i * window + j) % N]);
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