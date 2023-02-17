import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_15686_G5_치킨배달_전임송 {
	static final int MAX_CHIECKEN = 13;
	static final int MAX_DISTANCE = 100_000_000;
	
	static int[][] city;
	static int N;
	static int M;
	static int[] chickenR;
	static int[] chickenC;
	static List<Integer> houseR;
	static List<Integer> houseC;
	static int chickenCount;
	static int houseCount;
	static boolean[] notClosed;
	static int[][] chickenDistance;
	static int minTotal = MAX_DISTANCE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		city = new int[N][N];
		chickenR = new int[MAX_CHIECKEN];
		chickenC = new int[MAX_CHIECKEN];
		houseR = new ArrayList<Integer>(10);
		houseC = new ArrayList<Integer>(10);
		chickenCount = 0;
		houseCount = 0;
		notClosed = new boolean[MAX_CHIECKEN];
		
		
		for (int r = 0; r < N; r++) {
			input = br.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				city[r][c] = Integer.parseInt(input[c]);
				if (city[r][c] == 2) {
					chickenR[chickenCount] = r;
					chickenC[chickenCount] = c;
					chickenCount++;
				}
				if (city[r][c] == 1) {
					houseR.add(r);
					houseC.add(c);
					houseCount++;
				}
			}
		}
		chickenDistance = getChickenDistance();
		getCombination(0, 0);
		System.out.printf("%d", minTotal);
	}
	
	public static void getCombination(int count, int start) {
		if (count == M) {
			checkTotalDistance();
		}
		for (int i = start; i < chickenCount; i++) {
			notClosed[i] = true;
			getCombination(count + 1, i + 1);
			notClosed[i] = false;
		}
	}
	
	public static void checkTotalDistance() {
		int totalDistance = 0;
		for (int h = 0; h < houseCount; h++) {
			int minDistance = MAX_DISTANCE;
			for (int c = 0; c < chickenCount; c++) {
				if (notClosed[c]) {
					minDistance = Math.min(minDistance, chickenDistance[c][h]);
				}
			}
			totalDistance += minDistance;
		}
		minTotal = Math.min(minTotal, totalDistance);
	}
	
	public static int[][] getChickenDistance() {
		int[][] chickenDistance = new int[chickenCount][houseCount];
		for (int c = 0; c < chickenCount; c++) {
			for (int h = 0; h < houseCount; h++) {
				chickenDistance[c][h] = getDistance(chickenR[c], chickenC[c], houseR.get(h), houseC.get(h));
			}
		}
		return chickenDistance;
	}
	public static int getDistance(int ir, int ic, int jr, int jc) {
		return Math.abs(ir - jr) + Math.abs(ic - jc);
	}
}
