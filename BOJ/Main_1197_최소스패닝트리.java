import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main_1197_최소스패닝트리 {
    /*
    1197 최소스패닝트리
 	정렬
 	subset

   	*/
	
	static int[] parents;
	static class Edge implements Comparable<Edge>{
		int n;
		int m;
		int w;
		
		public Edge(int n, int m, int w) {
			super();
			this.n = n;
			this.m = m;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "Edge [n=" + n + ", m=" + m + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(w, o.w);
		}
	}
	
	public static int findSet(int n) {
		if (parents[n] == n) {
			return n;
		}
		return parents[n] = findSet(parents[n]);
	}
	
	public static boolean union(int n, int m) {
		int set1 = findSet(n);
		int set2 = findSet(m);
		
		if (set1 == set2) {
			return false;
		}
		parents[set1] = set2;
		return true;
	}
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
        	input = br.readLine().split(" ");
        	int n = Integer.parseInt(input[0]);
        	int m = Integer.parseInt(input[1]);
        	int w = Integer.parseInt(input[2]);
        	edges.add(new Edge(n, m, w));
        }
        
        Collections.sort(edges);
        
        parents = new int[V + 1];
        for (int i = 0; i < V; i++) {
        	parents[i] = i;
        }
        int sum = 0;
        for (Edge edge : edges) {
        	if (union(edge.n, edge.m)) {
        		sum += edge.w;
        		//System.out.println(edge);
        	}
        }
        System.out.println(sum);
    }
}