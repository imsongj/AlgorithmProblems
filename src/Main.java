import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//5525 IOIOI
/*
 * 문자열 읽으면서 연결된 IOI 수 기록
 * 각 연결된 IOI 마다 Pn의 개수는 연결된 수 - n + 1
 */

public class Main {	
	static int N;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int start = 0;
	static int minCost = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] line;
		adjMatrix = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(line[j]);
			}
		}
		dfs(start, 1, 0);
		System.out.println(minCost);
	}
	
	public static void dfs(int current, int visitCount, int costSum) {
		visited[current] = true;
		//System.out.println(current + " " + visitCount + " " + costSum);
		if (visitCount == N) {
			if (adjMatrix[current][start] == 0) { //시작점으로 돌아갈수 없는 경우
				 return;
			}
			minCost = Math.min(minCost, costSum + adjMatrix[current][start]);
			visited[current] = false;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i] || adjMatrix[current][i] == 0) {
				continue;
			}
			dfs(i, visitCount + 1, costSum + adjMatrix[current][i]);
		}
		visited[current] = false;
	}
}
