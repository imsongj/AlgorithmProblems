import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_4386_별자리만들기 {
    /*
		4386 별자리만들기
		거리 정렬
		mst

   	*/
	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		double weight;

		public Edge(int a, int b, double weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge{" +
				"a=" + a +
				", b=" + b +
				", weight=" + weight +
				'}';
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	static int[] parents;
	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);

		if (parentB == parentA) {
			return false;
		}
		parents[parentA] = parentB;
		return true;
	}
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        double[][] position = new double[N][2];
		parents = new int[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            position[i][0] = Double.parseDouble(input[0]);
            position[i][1] = Double.parseDouble(input[1]);
			parents[i] = i;
        }

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				pq.add(new Edge(i, j, getDistance(position[i][0], position[i][1], position[j][0], position[j][1])));
			}
		}
		double sum = 0;
       while (!pq.isEmpty()) {
		   Edge edge = pq.poll();
		   if (union(edge.a, edge.b)) {
			   sum += edge.weight;
		   }
	   }
		System.out.printf("%.2f\n", sum);
    }

	static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}
}