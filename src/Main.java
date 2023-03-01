import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//20058 마법사상어와파이어스톰
// 1. 격자 90도 돌리기
// 2. 얼음 처리
		//모든칸탐색, 사방에 얼음이 없는 칸이 3개 미만이면 얼음 수 - 1;
public class Main {	
	static int N;
	static List<Integer>[] lowerWorkers;
	static int[] compliments;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		lowerWorkers = new List[N + 1];
		compliments = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			lowerWorkers[i] = new ArrayList<>(10);
		}
		
		input = br.readLine().split(" ");
		
		for (int workerNum = 2; workerNum <= N; workerNum++) { //1은 사장이라 제외
			int boss = Integer.parseInt(input[workerNum - 1]); //상사 부하 관계 종합
			lowerWorkers[boss].add(workerNum);
		}
		
		for (int i = 0; i < M; i++) { //각 직원이 받은 칭찬 종합
			input = br.readLine().split(" ");
			int workerNum = Integer.parseInt(input[0]);
			int weight = Integer.parseInt(input[1]);
			compliments[workerNum] += weight;
		}
		
		for (int workerNum = 2; workerNum <= N; workerNum++) { //칭찬 아래로 전달
			for (int lowerWorkerNum : lowerWorkers[workerNum]) {
				compliments[lowerWorkerNum] += compliments[workerNum];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(compliments[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}