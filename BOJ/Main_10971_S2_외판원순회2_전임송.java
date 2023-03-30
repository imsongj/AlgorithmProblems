import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//10971 외판원순회2
/*
 * dfs
 */

public class Main_10971_S2_외판원순회2_전임송 {	
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
