import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Main {
	/*
	1202 보석도둑
	 최대한 작은 가방부터 가장 비싼 보석을 넣는다
	무게 적은 순으로 보석 정렬
	작은 가방부터
		작은 가방의 무게 이하의 보석들 pq에 넣어서 제일 위에거
	 */
	public static class Jewel implements Comparable<Jewel> {
		int weight;
		int price;

		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Jewel j) {
			//compare weight
			return Integer.compare(this.weight, j.weight);
		}

		@Override
		public String toString() {
			return "Jewel{" +
				"weight=" + weight +
				", price=" + price +
				'}';
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);

		Jewel[] inBag = new Jewel[K];

		List<Jewel> jewels = new ArrayList<>();
		PriorityQueue<Integer> bags = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int w = Integer.parseInt(input[0]);
			int p = Integer.parseInt(input[1]);
			jewels.add(new Jewel(w, p));
		}

		for (int i = 0; i < K; i++) {
			int c = Integer.parseInt(br.readLine());
			bags.add(c);
		}

		long sum = 0;

		int capacity;
		while (!bags.isEmpty()) {
			current = pqJewel.poll();
			if (current.weight <= capacity) {
				sum += current.price;
				if (!pqBag.isEmpty()) {
					capacity = pqBag.poll();
				}
			}
		}

		System.out.println(sum);
	}
}