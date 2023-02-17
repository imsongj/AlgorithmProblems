package algorithm.boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 앞에 선부터 LIS 활용해서 안겹치는 최대 전깃줄 찾기 
	//a 에서 증가하고 b 에서도 더 커야함, 배열 두개
class Line implements Comparable<Line> {
	int start;
	int end;
	
	public Line(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Line o) {
		return start - o.start;
	}
}
public class Main_전깃줄 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		Line[] lines = new Line[n];
		int[] overlap = new int[n];
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			lines[i] = new Line(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			overlap[i] = 1;
		}
		Arrays.sort(lines);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (lines[j].start < lines[i].start  && lines[j].end < lines[i].end) {
					overlap[i] = Math.max(overlap[i], overlap[j] + 1);
				}
			}
		}
		int max = 0;
		for (int i : overlap) {
			max = Math.max(max, i);
		}
		sb.append(n - max);
		System.out.println(sb.toString());
		br.close();
	}	
}