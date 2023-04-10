import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * 9205 맥주마시면서걸어가기
 * 
 * 집 - 1000미터 이내 모든 편의점
 * 모든 편의점 - 1000미터 이내 모든 편의점
 * 모든 편의점 - 100미터 이내 페스티벌
 * 
 * bfs
 * 
 */

public class Main_9205_G5_맥주마시면서걸어가시_전임송 {		
	static final int MAX_BEER = 20;
	static final int METER_PER_BEER = 50;
	
	static class Location {
		int x; 
		int y;
		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static Location start;
	static Location dest;
	static List<Location> stores;
	static List<Integer>[] connected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			start = new Location(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			stores = new ArrayList<>(N + 1);
			stores.add(null);
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				stores.add(new Location(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
			}
			input = br.readLine().split(" ");
			dest = new Location(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			createRoads();
			if (bfs()) {
				sb.append("happy").append('\n');
				continue;
			}
			sb.append("sad").append('\n');;
		}
		System.out.println(sb);
	}
	public static boolean bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 2];
		queue.add(0);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (Integer to : connected[current]) {
				if (to == N + 1) {
					return true;
				}
				if (visited[to]) {
					continue;
				}
				queue.add(to);
				visited[to] = true;
			}
		}
		return false;
	}
	public static void createRoads() {
		connected = new List[N + 2];
		for (int i = 0; i < N + 2; i++) {
			connected[i] = new ArrayList<>(10);
		}
		int distance = getDistance(start, dest); //집에서 페스티벌
		if (distance <= MAX_BEER * METER_PER_BEER) {
			connected[0].add(N + 1);
		}
		
		for (int i = 1; i <= N; i++) { //집에서 편의점 가는 간선
			distance = getDistance(start, stores.get(i));
			if (distance > MAX_BEER * METER_PER_BEER) {
				continue;
			}
			connected[0].add(i);
		}
		for (int i = 1; i <= N; i++) { //편의점에서 편의점 가는 간선
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					continue;
				}
				distance = getDistance(stores.get(i), stores.get(j));
				if (distance > MAX_BEER * METER_PER_BEER) {
					continue;
				}
				connected[i].add(j);
			}
		}
		for (int i = 1; i <= N; i++) { //편의점에서 패스티벌
			distance = getDistance(stores.get(i), dest);
			if (distance > MAX_BEER * METER_PER_BEER) {
				continue;
			}
			connected[i].add(N + 1);
		}
	}
	public static int getDistance(Location a, Location b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}
