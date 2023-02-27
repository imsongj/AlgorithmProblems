package practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//모든 봉우리를 구하고 안에있는지 확인, 높이는 최대 높이;
public class Algo2_서울_13반_전임송 {
	static final int GROUND = 0;
	
	static class Hill implements Comparable<Hill> {
		int height;
		int start;
		int end;
		boolean notInside = true;
		boolean hasInside = false;
		public Hill(int height, int start, int end) {
			super();
			this.height = height;
			this.start = Math.min(start, end);
			this.end = Math.max(start, end);;
		}
		
		@Override
		public String toString() {
			return "Hill [height=" + height + ", start=" + start + ", end=" + end + ", notInside=" + notInside
					+ ", hasInside=" + hasInside + "]";
		}

		@Override
		public int compareTo(Hill o) {
			return start - o.start;
		}
		
		
	}
	
	static int[] pointY;
	static int[] pointX;
	static List<Hill> hills;
	static int countNotInside = 0;
	static int countNoInside = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //스카프 개수 입력
		pointY = new int[N];
		pointX = new int[N];
		hills = new ArrayList<>(10);
		String[] input;
		for (int i  = 0; i < N; i++) {
			input = br.readLine().split(" ");
			pointX[i] = Integer.parseInt(input[0]); //스카프 위치 입력
			pointY[i] = Integer.parseInt(input[1]);
		}
		boolean begin = false;
		int start = pointX[0];
		int end; 
		int height = pointY[0];

		for (int i = 1; i < N; i++) {
			height = Math.max(height, pointY[i]);
			if (pointY[i - 1] < GROUND && pointY[i] > GROUND) { //봉우리의 시작
				begin = true;
				start = pointX[i];
			}
			if (pointY[i - 1] > GROUND && pointY[i] < GROUND) {// 봉우리의 끝
				hills.add(new Hill(height, start, pointX[i])); //봉우리 기록

				height = 0; //최고 높이 초기화
			}
		}
		int numberOfHills = hills.size();
		Collections.sort(hills);
		Stack<Hill> stack = new Stack<>();
		stack.add(hills.get(0));
		for (int i = 1; i < numberOfHills; i++) { //봉우리 관계 확인
			start = hills.get(i).start;
			System.out.println(start);
			if (start < stack.peek().end) { //전 봉우리 안에 있는경우
				stack.peek().hasInside = true;
				hills.get(i).notInside = false;
				stack.add(hills.get(i));
				continue;
			}
			while (!stack.isEmpty() && start > stack.peek().end) { // 전 봉우리안에 있지 않을때 
				stack.pop();
			}
			if (!stack.isEmpty()) { //봉우리 안에 있는 경우
				hills.get(i).notInside = false;
			}
			stack.add(hills.get(i));
		}
		
		for (int i = 0; i < numberOfHills; i++) {
			if (!hills.get(i).hasInside) {
				countNoInside++;
			}
			if (hills.get(i).notInside) {
				countNotInside++;
			}
		}
		System.out.printf("%d %d", countNotInside, countNoInside);
		
	}
	
}
