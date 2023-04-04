package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 *
[입력데이타]
6 11
0 1 4
0 2 2 
0 5 25
1 3 8
1 4 7
2 1 1
2 4 4
3 5 6
4 0 3
4 3 5
4 5 12
 *[0, 3, 2, 11, 6, 17]
 */
public class Dijkstra_PQ {
	static class Edge implements Comparable<Edge>{
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return weight + "";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		List<Edge>[] adj = new ArrayList[V];
		for(int i = 0; i < V; i++)
			adj[i] = new ArrayList<>();
		for(int i = 0; i < E; i++) {
			//첫번째가 출발지, 두번째가 도착지, 세번째가 가중치ㅋ
			adj[sc.nextInt()].add(new Edge(sc.nextInt(), sc.nextInt()));
		}
		//
		//dijkstra
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V];
		Edge[] D = new Edge[V];
		//0번에서 출발하는걸로.
		for(int i = 0; i < V; i++) {
			//원하는 출발지
			if( i == 0 ) {
				D[i] = new Edge(i, 0);
			}
			else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}
		while( !pq.isEmpty() ) {
			Edge edge = pq.poll();
			
			for( Edge next : adj[edge.v] ) {
				// check되지 않았으면서, D[next.v]가 D[edge.v] + next.weight 보다 더 크다면 갱신
				if( !check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight ) {
					D[next.v].weight = D[edge.v].weight + next.weight;
					//decrease key
					//D[next.v].weight 값을 갱신했지만   값 갱신에 의해 pq가 정렬을 다시 하지 않으므로 
					//제거했다가 값을 다시 넣어줘야 정렬을 다시한다. 
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			check[edge.v] = true;
		}
		System.out.println(Arrays.toString(D));
	}
}












