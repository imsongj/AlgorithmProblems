import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 1406 에디터
 * LDBP
 */

public class Main_1406_에디터 {	
	static final int END = -1;
	static final int MAX_LENGTH = 600_002;
	static int N;
	static int cursor;
	static char[] letter;
	static int[] left;
	static int[] right;
	static int counter = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		letter = new char[MAX_LENGTH];
		left = new int[MAX_LENGTH];
		right = new int[MAX_LENGTH];
		String str = br.readLine().trim();
		letter[0] = ' ';
		right[0] = 1;
		left[0] = END;
		for (int i = 1; i < str.length() + 1; i++) {
			letter[i] = str.charAt(i - 1);
			left[i] = i - 1;
			right[i] = i + 1;
			counter++;
		}
		counter++;
		right[str.length()] = END;
		cursor = str.length();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] operation = br.readLine().split(" ");
			if (operation[0].equals("L")) {
				actionL();
				continue;
			} 
			if (operation[0].equals("D")) {
				actionD();
				continue;
			} 
			if (operation[0].equals("B")) {
				actionB();
				continue;
			} 
			if (operation[0].equals("P")) {
				actionP(operation[1].charAt(0));
				continue;
			} 
			
		}
		//System.out.println(cursor);
		System.out.println(print());
	}
	public static void actionL() {
		if (left[cursor] == END) {
			return;
		}
		cursor = left[cursor];
	}
	public static void actionD() {
		if (right[cursor] == END) {
			return;
		}
		cursor = right[cursor];
	}
	//Sabc
	public static void actionB() { //현재 커서의 문자를 삭제, 커서의 왼쪽과 오른쪽 연결, 커서 이동
		if (cursor == 0) {
			return;
		}
		if (right[cursor] != END) {
			left[right[cursor]] = left[cursor];
		}
		right[left[cursor]] = right[cursor];
		cursor = left[cursor];
	}
	public static void actionP(char newCharacter) {
		letter[counter] = newCharacter;
		left[counter] = cursor;
		right[counter] = right[cursor];
		//System.out.printf("counter: %d, left %d, right %d\n", counter, left[counter], right[counter]);
		if (right[cursor] != END) {
			left[right[cursor]] = counter;
		}
		right[cursor] = counter;
		cursor = counter;
		counter++;
	}
	public static String print() {
		StringBuilder sb = new StringBuilder();
		int index = right[0];
		while (index != END) {
//			System.out.printf("index: %d, letter: %c, left: %d, right: %d\n", 
//					index, letter[index], left[index], right[index]);
			sb.append(letter[index]);
			index = right[index];
		}
		return sb.toString();
	}
}
