import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//2887 행성터널
/*
 * 3차원 mst
 */
public class Main2 {
	static class Planet {
		int id;
		int x;
		int y;
		int z;
		public Planet(int id, int x, int y, int z) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		
	}
	static int N;
	static boolean[] visited;
	static List<Planet> planets;
	static List<Planet> adjPlanets;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		planets = new ArrayList<>(N);
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int z = Integer.parseInt(input[2]);
			planets.add(new Planet(i, x, y, z));
		}
		
		for (int i = 0; i < N; i++) {
			int minDistance = Integer.MAX_VALUE;
			int closest = 0;
			for (int j = 0; j < adjPlanets.size(); j++) {
				if (j == i) {
					continue;
				}
				int distance = getDistance(planets.get(i), adjPlanets.get(j));
				if (minDistance > distance) {
					minDistance = distance;
					closest = j;
				}
			}
			adjPlanets
		}
	}
	
	public static int getDistance(Planet a, Planet b) {
		return Math.min(Math.min(Math.abs(a.x - b.x), Math.abs(a.y - b.y)), Math.abs(a.z - b.z));
	}
}
