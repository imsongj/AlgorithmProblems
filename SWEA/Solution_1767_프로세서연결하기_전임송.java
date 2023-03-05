import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//1767 프로세서연결하기
//코어 위치 저장
//코어 당 사방 탐색 dfs / 연결 코어 개수, 선 길이 카운트
//코어 개수가 크거나, 코어개수가 같고 선 길이가 짧은 경우 탐색

public class Solution_1767_프로세서연결하기_전임송 { 
	static final int EMPTY = 0;
	static final int CORE = 1;
	static final int LINE = 2;
	
	static class Core {
		int r;
		int c;
		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	static int[] dr = {0, 0, -1, 1}; 
	static int[] dc = {-1, 1, 0, 0};
	
	static int N;
	static int[][] board;
	static int numberOfCores;
	static List<Core> cores;
	
	static int maxConnectedCount;
	static int minLineCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input;
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			numberOfCores = 0;
			cores = new ArrayList<>(10);
			for (int r = 0; r < N; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(input[c]);
					if (board[r][c] == CORE) {
						if (r == 0 || r == N - 1 || c == 0 || c == N - 1) { //이미 연결된 코어 제외
							continue;
						}
						numberOfCores++;
						cores.add(new Core(r,c));
					}
				}
			}
			maxConnectedCount = 0;
			minLineCount = Integer.MAX_VALUE;
			dfs(0, 0);
			System.out.printf("#%d %d%n", t, minLineCount);
		}
		br.close();
	
	}
	public static void printBoard() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
	public static void dfs(int coreNum, int connectedCount) { 
		if (coreNum == numberOfCores) { //모든 코어를 탐색했을 때 
			compareResult(connectedCount);
			return;
		}
		for (int d = 0; d < 4; d++) {
			if (!checkConnection(coreNum, d)) {
				continue;
			}
			connect(coreNum, d);
			dfs(coreNum + 1, connectedCount + 1);
			disconnect(coreNum, d);
		}
		dfs(coreNum + 1, connectedCount);//해당 코어를 연결 안하는 경우
	}
	
	public static boolean checkConnection(int coreNum, int d) {
		int newR = cores.get(coreNum).r;
		int newC = cores.get(coreNum).c;
		while (true) {
			newR = newR + dr[d];
			newC = newC + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= N) { //연결 가능
				break;
			}
			if (board[newR][newC] != EMPTY) { //해당 방향 연결 불가능
				return false;
			}
		}
		return true;
	}
	
	public static void connect(int coreNum, int d) {
		int newR = cores.get(coreNum).r;
		int newC = cores.get(coreNum).c;
		while (true) {
			newR = newR + dr[d];
			newC = newC + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= N) { 
				break;
			}
			board[newR][newC] = LINE;
		}
	}
	public static void disconnect(int coreNum, int d) {
		int newR = cores.get(coreNum).r;
		int newC = cores.get(coreNum).c;
		while (true) {
			newR = newR + dr[d];
			newC = newC + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= N) { 
				break;
			}
			board[newR][newC] = EMPTY;
		}
	}
	
	public static void compareResult(int connectedCount) {
		if (connectedCount > maxConnectedCount) {
			maxConnectedCount = connectedCount;
			minLineCount = countLine();
			return;
		}
		if (connectedCount == maxConnectedCount) {
			minLineCount = Math.min(minLineCount, countLine());
		}
		
	}
	public static int countLine() {
		int count = 0; 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] == LINE) {
					count++;
				}
			}
		}
		return count;
	}
}