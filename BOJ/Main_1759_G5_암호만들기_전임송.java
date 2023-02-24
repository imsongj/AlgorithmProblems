import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1759 암호만들기
// 알파벳 정렬 후 조합

public class Main_1759_G5_암호만들기_전임송 {
	static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
	static int L;
	static int C;
	static char[] letters;
	static char[] password;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		L = Integer.parseInt(input[0]); 
		C = Integer.parseInt(input[1]); 
		
		letters = new char[C];
		password = new char[L];
		input = br.readLine().trim().split(" ");
		for (int i = 0; i < C; i++) {
			letters[i] = input[i].charAt(0);
		}
		Arrays.sort(letters);
		getCombination(0, 0);
		System.out.println(sb.toString());
	}
	
	public static void getCombination(int count, int start) {
		if (count == L) {
			if (checkPassword()) {
				for (int i = 0; i < L; i++) {
					sb.append(password[i]);
				}
				sb.append('\n');
			}
			return;
		}
		for (int i = start; i < C; i++) {
			password[count] = letters[i];
			getCombination(count + 1, i + 1);
		}
	}
	
	public static boolean checkPassword() {
		int vowelCount = 0;
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < VOWELS.length; j++) {
				if (password[i] == VOWELS[j]) {
					vowelCount++;
				}
			}
		}
		return vowelCount >= 1 && L - vowelCount >= 2;
	}
}
