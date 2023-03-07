package practice;

import java.util.Arrays;

public class SubsetPractice {
	static int[] parents;
	
	public static void makeSet(int v) {
		parents[v] = v;
	}
	
	findSet(intv) {
		if (parnets[n] -= v) {
			reutnr v
		}
		return [parnets[]]= findset(parnets[]bv);
	}
	public static int findSet(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = findSet(parents[v]);
	}
	
	public static void union(int u, int v) {
		int rootU = findSet(u);
		int rootV = findSet(v);
		if (rootU == rootV) {
			return;
		}
		parents[rootU] = rootV;
	}
	public static void main(String[] args) {
		parents = new int[5];
		for (int i = 0; i < 5; i++) {
			makeSet(i);
		}
		System.out.println(findSet(1));
		union(1, 2);
		union(3, 1);
		//union(4, 3);
		System.out.println(findSet(1));
		System.out.println(findSet(2));
		System.out.println(findSet(3));
		System.out.println(Arrays.toString(parents));
	}
}
