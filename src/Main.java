import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//15683 감시
//모든 경우 탐색, 조합(각 cctv 마다 4방향 경우를 체크해야한다; 5번일때는 불필요), 사다리랑 비슷하다: 각 cctv 마다, 각 방향마다 조합
// 전체 빈 칸 - cctv감시 칸  = 사각지대
//void findVisable(int cctv type, int r, int c, int direction) 1: 
public class Main {
	static final int EMPTY = 0;
	static final int WALL = 6;
	static final int CCTV_SINGLE = 1;
	static final int CCTV_DOUBLE_180 = 2;
	static final int CCTV_DOUBLE_90 = 3;
	static final int CCTV_TRIPLE = 4;
	static final int CCTV_FULL = 5;
	static final int[] CCTV_POSSIBLE_DIRECTIONS = {0, 4, 2, 4, 4, 1}; //cctv 별로 탐색해야하는 방향 수
	
	static int[] dr = {0, 1, 0, -1}; //right, down, left, up
	static int[] dc = {1, 0, -1, 0};
	
	static int N;
	static int M;
	static int[][] office;
	
	static int cctvCount;
	static List<CCTV> cctvs;
	static int[] directions;
	
	static int count1 = 0;
	static class CCTV {
		int type;
		int possibleDirections;
		int r;
		int c;
		public CCTV(int type, int r, int c) {
			this.type = type;
			this.possibleDirections = CCTV_POSSIBLE_DIRECTIONS[type];
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		office = new int[N][M];
		cctvCount = 0;
		cctvs = new ArrayList<>(8);
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				office[r][c] = Integer.parseInt(input[c]);
				if (office[r][c] > 0 && office[r][c] < 6) {
					int type = office[r][c];
					cctvCount++;
					cctvs.add(new CCTV(type, r, c));
				}
			}
		}
		directions = new int[cctvCount];
		getCombination(0, 0);
		System.out.println(count1);
	}
	
	public static void getCombination(int count, int start) { //cctv마다 방향 설정
		if (count == cctvCount) {
			//사각지대 카운트
			//System.out.println(Arrays.toString(directions));
			count1++;
			return;
		}
		for (int cctvNum = start; cctvNum < cctvCount; cctvNum++) {
			for (int d = 0; d < cctvs.get(cctvNum).possibleDirections; d++) {
				directions[cctvNum] = d;
				getCombination(count + 1, cctvNum + 1);
			}
		}
	}
}
