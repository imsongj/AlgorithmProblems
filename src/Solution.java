import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(
			s.solution("BBAABB")
		);
	}
	class Node implements Comparable<Node> {
		StringBuilder name;
		int currentIndex;
		int count;

		public Node(String name, int count, int currentIndex) {
			this.name = new StringBuilder(name);
			this.count = count;
			this.currentIndex = currentIndex;
		}
		@Override
		public int compareTo(Node o) {
			return this.count - o.count;
		}
		@Override
		public String toString() {
			return "Node{" +
				"name=" + name +
				", currentIndex=" + currentIndex +
				", count=" + count +
				'}';
		}
	}
	static final char START = 'A';
	public int solution(String name) {
		int answer = 0;
		int length = name.length();
		String startName = "";
		for (int i = 0; i < length; i++) {
			startName += START;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startName, 0, 0));
		while (!queue.isEmpty()) {
			Node current = queue.poll();

			char target = name.charAt(current.currentIndex);
			StringBuilder newName = new StringBuilder(current.name);
			int newCount = current.count;
			if (target != current.name.charAt(current.currentIndex)) {
				newCount += Math.min(up(target), down(target));
				newName.setCharAt(current.currentIndex, target);
			}
			if (newName.toString().equals(name)) {
				answer = newCount;
				break;
			}
			queue.add(new Node(newName.toString(), newCount + 1, left(name.length(), current.currentIndex)));
			queue.add(new Node(newName.toString(), newCount + 1, right(name.length(), current.currentIndex)));
		}
		return answer;
	}
	public int up(char target) {
		int count = 0;
		char current = START;
		while (current != target) {
			current++;
			count++;
		}
		return count;
	}

	public int down(char target) {
		int count = 0;
		char current = START;
		while (current != target) {
			current--;
			if (current < 'A') {
				current = 'Z';
			}
			count++;
		}
		return count;
	}

	public int left(int length, int current) {
		current--;
		if (current < 0) {
			current = length - 1;
		}
		return current;
	}

	public int right(int length, int current) {
		current++;
		if (current >= length) {
			current = 0;
		}
		return current;
	}
}