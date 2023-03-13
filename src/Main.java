import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//16398 행성연결
//간선 수가 적기 때문에 kruskal 알고리즘 사용
//1. 간선들 오름차순 정렬
//2. union find 사용으로 사이클 방지
// 	간선의 양끝 정점이 같은 집합에 속해 있는지를 먼저 검사

public class Main {	
	public static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
		
	}
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parents = new int[N];
		String[] line;
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			int weight = Integer.parseInt(line[2]);
		}
		
		
	}
	
}