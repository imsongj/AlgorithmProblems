import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/*
 * 17472 다리만들기2
 * 
 * 섬 연결 체크: 서로수
 * dfs
 */

public class Main_17472_G1_다리만들기2_전임송 {		
	static final int WATER = 0;
	static final int UNKNOWN = 1;
	static final int ISLAND_START = 2;
	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	
	static class Position {
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static class Road implements Comparable<Road>{
		int from;
		int to;
		int length;
		public Road(int from, int to, int length) {
			super();
			this.from = from;
			this.to = to;
			this.length = length;
		}
		@Override
		public String toString() {
			return "Road [from=" + from + ", to=" + to + ", length=" + length + "]";
		}
		@Override
		public int compareTo(Road o) {
			return Integer.compare(length, o.length);
		}
		
	}
	static int N;
	static int M;
	static int[][] map;
	
	static int[] parents;
	static List<List<Position>> islands; 
	static int numberOfIslands = 0;
	static List<Road> roads = new ArrayList<>(10);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(input[c]);
			}
		}
		islands = new ArrayList<>(6);
		
		findIslands(); //섬 표시
		
		createPossibleRoads(); //섬  사이 가능한 모든 길 생성
		Collections.sort(roads);
		parents = new int[numberOfIslands];
		makeSet();
		int sum = 0;
		for (Road road : roads) {
			
			if (findSet(road.from) == findSet(road.to)) {
				continue;
			}
			sum += road.length;
			union(road.from, road.to);
		}
		for (int i = 0; i < numberOfIslands - 1; i++) { //모든 섬 연결 체크
			if (findSet(i) != findSet(i + 1)) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(sum);
		
	}
	public static void makeSet() {
		for (int i = 0; i < numberOfIslands; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int n) {
		if (parents[n] == n) {
			return n;
		}
		return parents[n] = findSet(parents[n]);
	}
	
	public static void union(int n, int m) {
		int pn = findSet(n);
		int pm = findSet(m);
		if (pn < pm) {
			parents[pm] = pn;
			return;
		}
		parents[pn] = pm;
	}
	
	public static void findIslands() {
		int islandNumber = ISLAND_START;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == WATER || map[r][c] != UNKNOWN) {
					continue;
				}
				islands.add(new ArrayList<Position>(10));
				islandDfs(r, c, islandNumber);
				islandNumber++;
				numberOfIslands++;
			}
		}
	}
	
	public static void islandDfs(int r, int c, int number) {
		map[r][c] = number;
		islands.get(number - ISLAND_START).add(new Position(r, c));
		for (int d = 0; d < 4; d++) {
			int nextR = r + dr[d];
			int nextC = c + dc[d];
			if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
				continue;
			}
			if (map[nextR][nextC] == WATER || map[nextR][nextC] != UNKNOWN) {
				continue;
			}
			islandDfs(nextR, nextC, number);
		}
	}
	
	public static void createPossibleRoads() {
		for (int i = 0; i < numberOfIslands; i++) {
			for (Position land : islands.get(i)) { 
				connect(land.r, land.c, i);
			}
		}
	}
	
	public static void connect(int r, int c, int from) {
		for (int d = 0; d < 4; d++) {
			int nextR = r;
			int nextC = c;
			int length = 0;
			while (true) {
				nextR = nextR + dr[d];
				nextC = nextC + dc[d];
				
				if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
					break;
				}
				if (map[nextR][nextC] - ISLAND_START == from) {
					break;
				}
				if (map[nextR][nextC] != WATER) { //새로운 섬 
					if (length > 1) {
						roads.add(new Road(from, map[nextR][nextC] - ISLAND_START, length));
					}
					break;
				}
				length++;
			}
		}
	}
}
