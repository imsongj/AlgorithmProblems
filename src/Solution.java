import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1949 등산로조성

//모든 가장 높은 봉우리에서 4방향으로 낮은 지형으로 연결해서 가장 긴 등산로
//가장 높은 봉우리들 탐색
//모든 공사 경우의 수 탐색
	//그 봉우리들에서 dfs
public class Solution { 
	static final int NUMBER_OF_TOP = 5;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int N;
	static int K;
	static int[][] map;
	static int[] topR;
	static int[] topC;
	static int topCount;
	static int maxLength = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			K = Integer.parseInt(input[1]);
			
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				input = br.readLine().split(" ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(input[c]);
				}
			}
			findTops(); 
			maxLength = 0; //가장 긴 등산로 길이 초기화
			changeMap();
			System.out.printf("#%d %d%n", t, maxLength);
		}
		br.close();
	}
	
	public static void changeMap() { 
		for (int k = 1; k <= K; k++) { //1부터 k까지 모든칸을 한번씩 공사
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] - k < 0) {
						continue;
					}
					map[r][c] = map[r][c] - k; //공사
					findLongestTrail(); //공사 한 맵에서 제일 긴 등산로 탐색
					map[r][c] = map[r][c] + k; //공사 복구
				}
			}
		}
	}
	
	public static void findLongestTrail() { //모든 봉우리에서 제일 긴 등산로 탐색
		for (int t = 0; t < topCount; t++) {
			dfs(topR[t], topC[t], map[topR[t]][topC[t]], 1);
		}
	}
	
	public static void dfs(int currentR, int currentC, int prevHeight, int length) { //더 이상 진행하지 못할때 까지 등산로 탐색
		boolean deadend = true;
		for (int d = 0; d < 4; d++) {
			int newR = currentR + dr[d];
			int newC = currentC + dc[d];
			if (newR < 0 || newR >= N || newC < 0 || newC >= N 
					|| map[newR][newC] >= prevHeight) {
				continue;
			}
			deadend = false;
			dfs(newR, newC, map[newR][newC], length + 1);
		}
		if(deadend) {
			maxLength = Math.max(maxLength, length);
		}
	}
	
	public static void findTops() { //봉우리 탐색
		topR = new int[NUMBER_OF_TOP];
		topC = new int[NUMBER_OF_TOP];
		topCount = 0;
		int maxHeight = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				maxHeight = Math.max(maxHeight, map[r][c]);
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == maxHeight) {
					topR[topCount] = r;
					topC[topCount] = c;
					topCount++;
				}
			}
		}
	}
}