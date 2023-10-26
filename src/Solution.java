import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(
			s.solution("4177252841", 4)
		);
	}
	public String solution(String number, int k) {
		String answer = number;
		int j = 0;
		Stack<Character> stack = new Stack<>();
		stack.push(number.charAt(0));
		for (int i = 1; i < number.length(); i++) {
			Character c = number.charAt(i);
			while (!stack.isEmpty() && stack.peek() < c) {
				if (j == k) {
					break;
				}
				stack.pop();
				j++;
			}
			stack.push(c);
		}
		while (j < k) {
			stack.pop();
			j++;
		}
		StringBuilder sb =  new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		answer = sb.reverse().toString();
		return answer;
	}
}