package algorithm.swea;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1225_D3_암호생성기_백아현 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < 1; tc++) {
			Queue<Integer> queue = new LinkedList<>();
			int q = Integer.MAX_VALUE;
			int t = sc.nextInt();
			for (int i = 0; i < 8; i++) {
				int k = sc.nextInt();
				queue.offer(k);
				q = Math.min(q, k / 15);
			}
			for (int i = 0; i < 8; i++) {
				queue.offer(queue.poll() % (q - 1));
			}

			int flag = 0;
			while (flag != 1) {
				for (int i = 1; i <= 5; i++) {
					int k = queue.poll();
					if (k - i <= 0) {
						queue.offer(0);
						flag = 1;
						break;
					} else {
						queue.offer(k - i);
					}
				}
			}

			sb.append("#" + t + " ");
			while (queue.size() != 0) {
				sb.append(queue.poll() + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}
