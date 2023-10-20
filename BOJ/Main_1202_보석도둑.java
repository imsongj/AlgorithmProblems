import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Main_1202_보석도둑 {
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
			return Integer.compare(this.price, j.price);
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

		PriorityQueue<Jewel> jewels = new PriorityQueue<>(Collections.reverseOrder());
		TreeMap<Integer, Integer> bags = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int w = Integer.parseInt(input[0]);
			int p = Integer.parseInt(input[1]);
			jewels.add(new Jewel(w, p));
		}
		for (int i = 0; i < K; i++) {
			int c = Integer.parseInt(br.readLine());
			bags.put(c, bags.getOrDefault(c, 0) + 1);
		}

		long sum = 0;

		while (!jewels.isEmpty()) {
			Jewel jewel = jewels.poll();
			Integer weight = bags.ceilingKey(jewel.weight);
			if (weight == null) {
				continue;
			}
			sum += jewel.price;
			if (bags.get(weight) - 1 == 0) {
				bags.remove(weight);
				continue;
			}
 			bags.put(weight, bags.get(weight) - 1);

		}
		System.out.println(sum);
	}
}