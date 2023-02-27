package practice;

public class DisjointSetTest {
	static int[] parents;
	static int[] rank;
	
	public static void makeSet(int v) {
		parents[v] = v;
	}
	
	public static int findSet(int v) {
		if (v == parents[v]) {
			return v;
		}
		return findSet(parents[v]);
	}
	
	public static void union(int u, int v) {
		//parents[findSet(u)] = findSet(v);
		int root1 = findSet(u);
		int root2 = findSet(v);
		if (root1 == root2) return;
		
		int rank1 = rank[root1];
		int rank2 = rank[root2];
		
		if (rank1 == rank2) {
			rank[root2]++;
			parents[root1] = root2;
			rank[root1] = 0; 
			return;
		} 
		if (rank1 > rank2) {
			parents[root2] = root1;
			rank[root2] = 0;
			return;
		}
		parents[root1] = root2;
		rank[root1] = 0;
	}
	public static void main(String[] args) {
		int N = 6;
		parents = new int[N + 1];
	}
}
