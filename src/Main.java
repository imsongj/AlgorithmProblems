import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//20055 컨베이어 벨트 위의 로봇
//내구도 저장
//벨트 위 상황 저장 -> 1차원 배열 2N 크기, 벨트 위 배열 시작 인덱스, 끝 인덱스 저장, 이동 - 인덱스 수정

public class Main {	
	static int N;
	static int K;

	static int[] belt;
	static boolean[] robot;
	static int beltLength;
	static int start;
	static int end;
	static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		beltLength = 2 * N;
		belt = new int[beltLength];
		robot = new boolean[beltLength];
		input = br.readLine().split(" ");
		for (int r = 0; r < beltLength; r++) {
			belt[r] = Integer.parseInt(input[r]);
		}
		start = 0; //벨트 위 시작 인덱스
		end = N - 1; //벨트 위 끝 인덱스
		System.out.println(runSimulation());
	}
	public static int runSimulation() {
		int step = 0; //현재단계
		while (true) {
			step++;
			if (count >= K) {
				return step;
			}
			moveBelt();
			moveRobot();
			addRobot();
			//printBelt();
			
			
		}
	}
	
	public static void moveBelt() {
 		start = reduceIndex(start);
 		end = reduceIndex(end);
 		if (robot[end]) { //로봇이 마지막 칸 도달하면 내린다.
 			robot[end] = false;
 		}
 	}
 	
 	public static void moveRobot() {
 		int index = end;
 		int nextIndex;
 		for (int i = 0; i < N; i++) {
 			if (!robot[index]) { //해당 칸에 로봇 없는 경우
 				index = reduceIndex(index);
 				continue;
 			}
 			nextIndex = (index + 1) % beltLength;
 			
 			if (!robot[nextIndex] && belt[nextIndex] > 0) {
 				robot[nextIndex] = true;
 				robot[index] = false;
 				useBelt(nextIndex);
 				if (robot[end]) { //로봇이 마지막 칸 도달하면 내린다.
 		 			robot[end] = false;
 		 		}
 			}
 		}
	}
 	
 	public static void addRobot() {
 		if (belt[start] != 0) {
 			robot[start] = true;
 			useBelt(start);
 		}
 	}
 	
	public static void useBelt(int index) {
		belt[index]--;
		if (belt[index] == 0) {
			count++;
		}
	}
	
	public static int reduceIndex(int index) {
		index--;
 		if (index < 0) {
 			index = beltLength - 1;
 		}
 		return index;
	}
	
	public static void printBelt() {
		int index = start;
		for (int i = 0; i < beltLength / 2; i++) {
			System.out.printf("%d ", belt[index % beltLength]);
			index++;
		}
		System.out.println();
		index += beltLength / 2 - 1;
		for (int i = 0; i < beltLength / 2; i++) {
			System.out.printf("%d ", belt[(index - i) % beltLength]);
		}
		System.out.println();
		index = start;
		for (int i = 0; i < beltLength / 2; i++) {
			System.out.printf("%b ", robot[index % beltLength]);
			index++;
		}
		System.out.println();
		System.out.println();
	}
}