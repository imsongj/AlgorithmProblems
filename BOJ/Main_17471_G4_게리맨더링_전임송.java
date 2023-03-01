import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

//17471 게리맨더링

public class Main_17471_G4_게리맨더링_전임송 {
	static int N;
	static int[] population;
	static List<Integer>[] adj;
	static int minDifference = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>(10);
		}
		population = new int[N + 1];
		String[] input = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(input[i - 1]);
		}
		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			int m = Integer.parseInt(input[0]);
			for (int j = 0; j < m; j++) {
				int neighbor = Integer.parseInt(input[j + 1]);
				adj[i].add(neighbor);
			}
		}
		getSubset();
		if (minDifference == Integer.MAX_VALUE) { //나눌수 없는경우 -1 출력
			minDifference = -1;
		}
		System.out.println(minDifference);
	}
	
	public static void getSubset() { //모든 부분집합 탐색
		for (int i = 0; i < 1 << N; i++) {
			List<Integer> selectedRed = new ArrayList<>(10);
			List<Integer> selectedBlue = new ArrayList<>(10);
			for (int j = 0; j < N; j++) {
				if ((i & 1 << j) != 0) {
					selectedRed.add(j + 1);
					continue;
				}
				selectedBlue.add(j + 1);
			}
			check(selectedRed, selectedBlue);
		}
	}
	
	//두 구역 연결 체크
	public static void check(List<Integer> selectedRed, List<Integer> selectedBlue) {
		if (selectedRed.size() == 0 || selectedBlue.size() == 0) {
			return;
		}
		int redPopulation = getPopulation(selectedRed);
		int bluePopulation = getPopulation(selectedBlue);
		if (redPopulation == -1 || bluePopulation == -1) {
			return;
		}
		//System.out.printf("%s %s %d%n", selectedRed.toString(), selectedBlue.toString(), Math.abs(redPopulation - bluePopulation));
		minDifference = Math.min(Math.abs(redPopulation - bluePopulation), minDifference);
	}
	
	//BFS로 각 구역이 연결되어있는지 확인
	public static int getPopulation(List<Integer> selected) {
		int sum = 0;
		int numberOfSelected = selected.size();
		boolean[] visited = new boolean[N + 1];
		for (int i = 0; i < numberOfSelected; i++) {
			visited[selected.get(i)] = true; 
		}
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(selected.get(0));
		numberOfSelected--;
		sum += population[selected.get(0)];
		visited[selected.get(0)] = false;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int neighbor : adj[current]) {
				if (visited[neighbor]) {
					numberOfSelected--;
					sum += population[neighbor];
					visited[neighbor] = false;
					queue.add(neighbor);
				}
			}
		}
		if (numberOfSelected > 0) {
			return -1;
		}
		return sum;
	}
}
