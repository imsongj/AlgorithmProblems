import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main_푸는중 {
    /*
    1202 보석도둑
 	최대한 작은 가방부터 가장 비싼 보석을 넣는다
	1 10
	2 5
	3 30

	1
	3
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
			if (this.price == j.price) {
				return Integer.compare(j.weight, this.weight);
			}
			return Integer.compare(j.price, this.price);
		}

		@Override
		public String toString() {
			return "Jewel{" +
				"weight=" + weight +
				", price=" + price +
				'}';
		}
	}

	public static Jewel[] jewels;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

		jewels = new Jewel[N];
		Jewel[] inBag = new Jewel[K];

		PriorityQueue<Jewel> pqJewel = new PriorityQueue<>();
		PriorityQueue<Integer> pqBag = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int w = Integer.parseInt(input[0]);
			int p = Integer.parseInt(input[1]);
			pqJewel.add(new Jewel(w, p));
		}
		for (int i = 0; i < K; i++) {
			int c = Integer.parseInt(br.readLine());
			pqBag.add(c);
		}

		long sum = 0;
		// System.out.println(pqJewel);
		// System.out.println(pqBag);
		Jewel current;
		int capacity = pqBag.poll();
		while (!pqJewel.isEmpty()) {
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