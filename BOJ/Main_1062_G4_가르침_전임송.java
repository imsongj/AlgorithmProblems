import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//1062 가르침
//n개 단어에서 사용되는 전체 알파벳 중 k 개를 고르는 조합을 구하고 각 조합마다 몇 단어를 만들 수 있는지 확인한다
//각 단어마다 사용되는 알파벳 표시
public class Main_1062_G4_가르침_전임송 {
	static final char[] REQUIRED = {'a', 'n', 't', 'i', 'c'};
	static final int NUMBER_OF_ALPHABET = 26;
	
	static int N;
	static int K;
	static String[] words;
	static Set<Character> totalLetters;
	static List<Character> additionalLetters;
	static boolean[] learnedAlphabet;
	static int maxCount = 0;
	static int combinationLength;
	static int additionalNumber;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		words = new String[N];
		totalLetters = new HashSet<>();
		additionalLetters = new ArrayList<>(10);
		learnedAlphabet = new boolean[NUMBER_OF_ALPHABET];
		
		for (char alphabet : REQUIRED) { //필수 알파벳 미리 표시
			totalLetters.add(alphabet);
			learnedAlphabet[alphabet - 'a'] = true;
		}
		
		String line;
		for (int i = 0; i < N; i++) { //각 단어마다 필요한 알파벳 표시
			line = br.readLine();
			words[i] = line;
			for (int j = 0; j < line.length(); j++) {
				char character = line.charAt(j);
				if (totalLetters.contains(character)) {
					continue;
				}
				totalLetters.add(character); //사용되는 전체 알파벳 셋에 추가
				additionalLetters.add(character); //추가로 배워야 하는 알파벳 리스트에 추가
			}
		}
		
		if (K < REQUIRED.length) { //필수로 배워야하는 알파벳 수보다 K 가 작은 경우 바로 종료
			System.out.println(0);
			return;
		}
		
		combinationLength = Math.min(totalLetters.size(), K) - REQUIRED.length; //조합의 최대 길이
		additionalNumber = additionalLetters.size();
		
		getCombination(0, 0);
		System.out.println(maxCount);
	}
	
	public static void getCombination(int count, int start) { //필수 알파벳을 제외한 모든 알파벳 조합 탐색
		if (count == combinationLength) { //필수 알파벳을 뺀 K
			maxCount = Math.max(maxCount, countWords());
			return;
		}
		for (int i = start; i < additionalNumber; i++) {
			int alphabet = additionalLetters.get(i) - 'a';
			learnedAlphabet[alphabet] = true; 
			getCombination(count + 1, i + 1);
			learnedAlphabet[alphabet] = false; 
		}
	}
	
	public static int countWords() { //각 단어마다 이번 조합의 알파벳으로 읽을 수 있는지 확인
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (canRead(i)) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean canRead(int wordNum) {
		for (int j = 0, size = words[wordNum].length(); j < size; j++) {
			if (!learnedAlphabet[words[wordNum].charAt(j) - 'a']) {
				return false;
			}
		}
		return true;
	}
}
