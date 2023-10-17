import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//2383 점심식사시간
/*
 * subset, 1번 계단 또는 2번 계단선택
 * 시뮬
 * 	도착시간으로 정렬, 
 * 
 * 	
 * 
 */

public class Solution {
	static final int NUMBER_OF_STAIRS = 2;
	static final int STAIR_CAPACITY = 3;
	static final int WAIT_TIME = 1;
	static final int PERSON = 1;
	static class Position {
		int r;
		int c;
		int value;
		public Position() {};
	}
	static class Person extends Position {
		int[] arriveTime;
		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.arriveTime = new int[NUMBER_OF_STAIRS];
		}
	}
	static class Stair extends Position {
		int height;
		Queue<Person> queue;
		public Stair(int r, int c, int height) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
			this.queue = new ArrayDeque<>();
		}
	}
	static int N;
	static int[][] map;
	static List<Stair> stairs;
	static List<Person> people;
	static int minTime = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= testCase; t++) {
			N = Integer.parseInt(br.readLine());
			String[] input;
			stairs = new ArrayList<>(NUMBER_OF_STAIRS);
			people = new ArrayList<>(10);
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				input = br.readLine().trim().split(" ");
				for (int c = 0; c < N; c++) {
					int number = Integer.parseInt(input[c]);
					map[r][c] = number;

					if (number == PERSON) {
						people.add(new Person(r, c));
						continue;
					}
					if (number > 0) {
						stairs.add(new Stair(r, c, number));
					}
				}
			}
			for (Person person : people) {
				person.arriveTime[0] = getDistance(person, stairs.get(0));
				person.arriveTime[1] = getDistance(person, stairs.get(1));
			}
			minTime = Integer.MAX_VALUE;
			subset();
			sb.append('#').append(t).append(' ').append(minTime).append('\n');
			
		}
		System.out.println(sb);
	}
	
	public static int getDistance(Position a, Position b) {
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}
	
	public static void subset() {
		int numberOfPeople = people.size();
		PriorityQueue<Person> firstQueue = new PriorityQueue<>(
				(p1, p2) -> p1.arriveTime[0] - p2.arriveTime[0]);
		PriorityQueue<Person> secondQueue = new PriorityQueue<>(
				(p1, p2) -> p1.arriveTime[1] - p2.arriveTime[1]);
		for (int i = 0; i < 1 << numberOfPeople; i++) {
			firstQueue.clear();
			secondQueue.clear();
			for (int j = 0; j < numberOfPeople; j++) {
				if ((i & 1 << j) == 0) {
					firstQueue.add(people.get(j));
					continue;
				}
				secondQueue.add(people.get(j));
			}
			minTime = Math.min(minTime, Math.max(calculateTime(firstQueue, 0), calculateTime(secondQueue, 1)));
		}
	}
	public static int calculateTime(PriorityQueue<Person> queue, int stairNum) { 
		int endTime = 0;
		int stairLength = stairs.get(stairNum).height;
		int[] prevTimes = new int[queue.size() + 1];
		int count = 0;
		while (!queue.isEmpty()) {
			Person current = queue.poll();
			int arriveTime = current.arriveTime[stairNum];
			count++;
			if (count > STAIR_CAPACITY && arriveTime < prevTimes[count - STAIR_CAPACITY]) {
				endTime = prevTimes[count - STAIR_CAPACITY] + stairLength;
				prevTimes[count] = endTime;
				continue;
			} 
			endTime = arriveTime + stairLength + WAIT_TIME;
			prevTimes[count] = endTime;
		}
		return endTime;
	}
	
}