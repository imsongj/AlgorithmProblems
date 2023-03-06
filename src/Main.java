import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//20055 컨베이어 벨트 위의 로봇
//내구도 저장
//벨트 위 상황 저장 -> 1차원 배열 2N 크기, 벨트 위 배열 시작 인덱스, 끝 인덱스 저장, 이동 - 인덱스 수정

public class Main {	
	static final int NUMBER_OF_STEPS = 4;
	
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
		for (int i = 0; i < 10; i++) {
			printBelt();
			moveBelt();

		}
	}
	public static int runSimulation() {
		int step = 0; //현재단계
		while (true) {
			boolean stop = false;
			if (step == 0) { //1단계
				moveBelt();
			}
			if (step == 1) { //2단계
				stop = moveRobot();
			}
			if (step == 2) { //3단계
	
			}
			if (step == 3) { //4단계
				
			}
			if (stop) {
				break;
			}
			step = (step + 1) % NUMBER_OF_STEPS;
			
		}
		return step;
	}
	
 	public static boolean moveRobot() {
 		int index = end;
 		int nextIndex;
 		for (int i = 0; i < N; i++) {
 			if (!robot[index]) { //해당 칸에 로봇 없는 경우
 				continue;
 			}
 			nextIndex = (index + 1) % beltLength;
 			
 			if (!robot[nextIndex] && belt[nextIndex] > 0) {
 				robot[nextIndex] = true;
 				robot[index] = false;
 				belt[nextIndex]--;
 			}
 			index--;
 			if (index < 0) {
 				index = beltLength - 1;
 	 		}
 		}
		return false;
	}
 	
 	public static boolean addRobot() {
 		
 	}
 	
	public static void moveBelt() {
 		start--;
 		if (start < 0) {
 			start = beltLength - 1;
 		}
 		end--;
 		if (end < 0) {
 			end = beltLength - 1;
 		}
 		if (robot[end]) { //로봇이 마지막 칸 도달하면 내린다.
 			robot[end] = false;
 		}
 	}
 	
 	public static void printBelt() {
 		int index = start;
 		for (int i = 0; i < N; i++) {
 			System.out.printf("%d ", belt[index]);
 			index = (index + 1) % beltLength;
 		}
 		System.out.println();
 	}
}